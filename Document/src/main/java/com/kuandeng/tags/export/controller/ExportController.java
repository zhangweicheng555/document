package com.kuandeng.tags.export.controller;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kuandeng.tags.export.model.Person;
import com.kuandeng.tags.export.service.ExportService;
import io.swagger.annotations.ApiOperation;

/**
 * 模拟导出
 * 
 * @author a-zhangweicheng
 *
 */
@RestController
@RequestMapping("/export")
public class ExportController {
	@Autowired
	private ExportService exportService;

	@ApiOperation(value = "导出", notes = "tag", httpMethod = "GET")
	@GetMapping(value = "/")
	public void exportCsv(HttpServletResponse response) throws IOException {

		// 模拟数据
		List<Person> users = new ArrayList<>();
		users.add(new Person("13800138001", "圣诞老人1", "VIP1"));
		users.add(new Person("13800138002", "圣诞老人2", "VIP7"));
		users.add(new Person("13800138003", "圣诞老人3", "VIP8"));

		String fileName = "张三.csv";
		//防止乱码
		fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
		response.setHeader("Content-Disposition",
				"attachment; filename=\"" + fileName + "\"; filename*=utf-8''" + fileName);
		FileCopyUtils.copy(exportService.exportTagToCsv(users), response.getOutputStream());
	}

}