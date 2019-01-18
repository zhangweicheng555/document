package com.kuandeng.tags.export.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.kuandeng.tags.export.service.ExportService;
import com.kuandeng.tags.export.util.TargzUtil;
import com.kuandeng.tags.tag.model.Tag;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 导出
 * 
 * @author a-zhangweicheng
 *
 */
@RestController
@RequestMapping("/tag/export")
public class ExportController {
	@Autowired
	private ExportService exportService;

	@ApiOperation(value = "漏采轨迹标记导出", notes = "tag导出", httpMethod = "GET", produces = "application/octet-stream")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "adcode", value = "标签编码(多个以逗号分隔)", required = false, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "state", value = "状态(多个以逗号分割)", required = false, dataType = "String", paramType = "query") })
	@GetMapping(value = "/surveycomplementtag")
	public Object surveycomplementtag(String adcode, String state, HttpServletResponse response) throws Exception {

		String name = UUID.randomUUID().toString();
		File file = new File(name);
		OutputStream os = new FileOutputStream(file);
		Writer writer = new OutputStreamWriter(os, "utf-8");
		List<Tag> listData = exportService.surveycomplementtag(adcode, state);
		if (listData != null && listData.size() > 0) {
			for (Object obj : listData) {
				writer.append(JSONObject.toJSONString(obj));
				writer.append(System.lineSeparator());
			}
		}
		writer.flush();
		os.flush();
		os.close();
		TargzUtil.gzip(file, response);
		return null;
	}

}