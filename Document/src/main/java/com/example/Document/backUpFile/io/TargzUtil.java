package com.kuandeng.tags.export.util;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipParameters;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarOutputStream;

public class TargzUtil {

	/**
	 *
	 * @Title: pack @Description: 将一组文件打成tar包 @param sources 要打包的原文件数组 @param target
	 *         打包后的文件 @return File 返回打包后的文件 @throws
	 */
	public static File pack(List<File> sources, File target) throws IOException {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(target);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		TarArchiveOutputStream os = new TarArchiveOutputStream(out);
		for (File file : sources) {
			InputStream inputStream = null;
			try {
				os.putArchiveEntry(new TarArchiveEntry(file, file.getName()));// 打包的时候只是包含文件，不带路径
				// os.putArchiveEntry(new TarArchiveEntry(file)); //打包的时候带路径
				inputStream = new FileInputStream(file);
				IOUtils.copy(inputStream, os);
				os.closeArchiveEntry();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}
		if (os != null) {
			try {
				os.flush();
				os.close();
				System.out.println("打包后文件为：" + target);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return target;
	}

	/**
	 * tar打包压缩
	 * 
	 * @param source   需要压缩的文件
	 * @param FilePath 压缩后的文件全文件名(.tar)
	 * @return 返回压缩后的文件
	 */
	public static File tarFile(File source, String FilePath) {
		File target = new File(FilePath);
		FileInputStream in = null;
		GZIPOutputStream out = null;
		try {
			in = new FileInputStream(source);
			out = new GZIPOutputStream(new FileOutputStream(target));
			byte[] array = new byte[1024];
			int number;
			while ((number = in.read(array, 0, array.length)) != -1) {
				out.write(array, 0, number);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (in != null) {
				try {
					in.close();
					source.delete();
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
			if (out != null) {
				try {
					out.close();
					System.out.println("打包后文件为：" + target);
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return target;
	}

	public static File compressTarFile(List<File> listFiles, String targzipFilePath, String targzipFileName) {
		byte[] buf = new byte[1024]; // 设定读入缓冲区尺寸
		try {

			// 建立压缩文件输出流
			FileOutputStream fout = new FileOutputStream(targzipFilePath);
			// 建立tar压缩输出流
			TarOutputStream tout = new TarOutputStream(fout);
			for (int i = 0; i < listFiles.size(); i++) {
				String filename = listFiles.get(i).getName();
				// 打开需压缩文件作为文件输入流
				FileInputStream fin = new FileInputStream(filename); // filename是文件全路径
				TarEntry tarEn = new TarEntry(listFiles.get(i)); // 此处必须使用new TarEntry(File file);
				tarEn.setName(listFiles.get(i).getName()); // 此处需重置名称，默认是带全路径的，否则打包后会带全路径
				tout.putNextEntry(tarEn);
				int num;
				while ((num = fin.read(buf)) != -1) {
					tout.write(buf, 0, num);
				}
				tout.closeEntry();
				fin.close();
			}
			tout.close();
			fout.close();

			// 建立压缩文件输出流
			FileOutputStream gzFile = new FileOutputStream(targzipFilePath + ".gz");
			// 建立gzip压缩输出流
			GZIPOutputStream gzout = new GZIPOutputStream(gzFile);
			// 打开需压缩文件作为文件输入流
			FileInputStream tarin = new FileInputStream(targzipFilePath); // targzipFilePath是文件全路径
			int len;
			while ((len = tarin.read(buf)) != -1) {
				gzout.write(buf, 0, len);
			}
			gzout.close();
			gzFile.close();
			tarin.close();
			return new File(targzipFilePath + targzipFileName);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		return null;
	}

	
	/**
	 * 
	 * @param inputFile   要压缩的文件
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
