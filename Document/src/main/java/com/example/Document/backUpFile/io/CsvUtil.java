package com.kuandeng.tags.export.util;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;

import com.kuandeng.tags.export.model.Person;

public class CsvUtil {

	/**
	 * 不要删除 导出csv文件
	 *
	 * @param headers    内容标题 注意：headers类型是LinkedHashMap，保证遍历输出顺序和添加顺序一致。
	 *                   而HashMap的话不保证添加数据的顺序和遍历出来的数据顺序一致，这样就出现 数据的标题不搭的情况的
	 * @param exportData 要导出的数据集合
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static byte[] exportCSV(LinkedHashMap<String, String> headers,
			List<LinkedHashMap<String, Object>> exportData) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedWriter buffCvsWriter = null;

		try {
			// 编码gb2312，处理excel打开csv的时候会出现的标题中文乱码
			buffCvsWriter = new BufferedWriter(new OutputStreamWriter(baos, "gb2312"));
			// 写入cvs文件的头部
			Map.Entry propertyEntry = null;
			for (Iterator<Map.Entry<String, String>> propertyIterator = headers.entrySet().iterator(); propertyIterator
					.hasNext();) {
				propertyEntry = propertyIterator.next();
				buffCvsWriter.append("\"" + propertyEntry.getValue().toString() + "\"");
				if (propertyIterator.hasNext()) {
					buffCvsWriter.append(",");
				}
			}
			buffCvsWriter.newLine();
			// 写入文件内容
			LinkedHashMap row = null;
			for (Iterator<LinkedHashMap<String, Object>> iterator = exportData.iterator(); iterator.hasNext();) {
				row = iterator.next();
				for (Iterator<Map.Entry> propertyIterator = row.entrySet().iterator(); propertyIterator.hasNext();) {
					propertyEntry = propertyIterator.next();
					buffCvsWriter.append("\"" + propertyEntry.getValue().toString() + "\"");
					if (propertyIterator.hasNext()) {
						buffCvsWriter.append(",");
					}
				}
				if (iterator.hasNext()) {
					buffCvsWriter.newLine();
				}
			}
			// 记得刷新缓冲区，不然数可能会不全的，当然close的话也会flush的，不加也没问题
			buffCvsWriter.flush();
		} catch (IOException e) {

		} finally {
			// 释放资源
			if (buffCvsWriter != null) {
				try {
					buffCvsWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return baos.toByteArray();
	}

	/**
	 * 导出用户到csv文件
	 *
	 * @param users 导出的数据（用户）
	 * @return
	 * @return
	 * @throws IOException
	 */
	public byte[] exportTagToCsv(List<Person> users) throws IOException {
		List<LinkedHashMap<String, Object>> exportData = new ArrayList<>(users.size());
		// 行数据
		for (Person user : users) {
			LinkedHashMap<String, Object> rowData = new LinkedHashMap<>();
			rowData.put("1", user.getId());
			rowData.put("2", user.getName());
			rowData.put("3", user.getLevel());
			exportData.add(rowData);
		}
		// 标题
		LinkedHashMap<String, String> header = new LinkedHashMap<>();
		header.put("1", "用户账号");
		header.put("2", "用户昵称");
		header.put("3", "用户等级");
		return CsvUtil.exportCSV(header, exportData);
	}

	// @ApiOperation(value = "导出", notes = "tag", httpMethod = "GET", produces =
	// "application/octet-stream")
	// @GetMapping(value = "/csv")
	public void exportCsv(HttpServletResponse response) throws IOException {

		// 模拟数据
		List<Person> users = new ArrayList<>();
		users.add(new Person("13800138001", "圣诞老人1", "VIP1"));
		users.add(new Person("13800138002", "圣诞老人2", "VIP7"));
		users.add(new Person("13800138003", "圣诞老人3", "VIP8"));

		String fileName = UUID.randomUUID().toString().replace("-", "");
		// 防止乱码
		fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());

		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".csv;filename*=utf-8");
		//FileCopyUtils.copy(exportService.exportTagToCsv(users), response.getOutputStream());
	}
}
