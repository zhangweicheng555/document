package com.kuandeng.tags.export.util;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipParameters;

/**
 * tar.gz压缩工具类
 * 
 * @author a-zhangweicheng
 *
 */
public class TargzUtil {

	/**
	 * 
	 * @param inputFile 要压缩的文件
	 * @param response
	 * @throws Exception
	 */
	public static void gzip(File inputFile, HttpServletResponse response) throws Exception {

		String fileTarGzName = UUID.randomUUID().toString().replace("-", "") + ".tar.gz";
		URLEncoder.encode(fileTarGzName, StandardCharsets.UTF_8.toString());

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileTarGzName + ";filename*=utf-8");

		String compressName = UUID.randomUUID().toString().replace("-", "") + ".dat";
		byte[] buffer = new byte[1024];
		try {
			GzipParameters gp = new GzipParameters();
			gp.setFilename(compressName);
//			GzipCompressorOutputStream gcos = new GzipCompressorOutputStream(new FileOutputStream(outputFileName), gp);
			GzipCompressorOutputStream gcos = new GzipCompressorOutputStream(response.getOutputStream(), gp);
			FileInputStream fis = new FileInputStream(inputFile.getName());
			int length;
			while ((length = fis.read(buffer)) > 0) {
				gcos.write(buffer, 0, length);
			}
			fis.close();
			gcos.finish();
			gcos.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (inputFile != null && inputFile.exists()) {
				inputFile.delete();
			}
		}
	}
}
