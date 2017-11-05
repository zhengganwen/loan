package com.study.loan.util;


import com.study.loan.core.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
	private static Logger log = LoggerFactory.getLogger(ZipUtil.class);

	public static void zip(OutputStream out) {
		ZipOutputStream zos = null;
		FileInputStream fis = null;
		File dir = null;
		try {
			byte[] buf = new byte[1024];
			dir = new File(Config.EXP_DIR);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			zos = new ZipOutputStream(out);
			List<File> files = getFiles(dir);
			String rootPath = dir.getAbsolutePath();
			for (File file : files) {
				fis = new FileInputStream(file);
				String filePath = file.getAbsolutePath().replace(rootPath + File.separator, "");
				zos.putNextEntry(new ZipEntry(filePath));
				int len;
				while ((len = fis.read(buf)) > 0) {
					zos.write(buf, 0, len);
				}
				fis.close();
			}
		} catch (IOException e) {
			log.error("ZIP打包出错", e);
		} finally {
			if (zos != null) {
				try {
					zos.close();
				} catch (IOException e) {
					log.warn("关闭IO异常");
				}
			}
			deleteFiles(dir);
		}
	}

	private static List<File> getFiles(File dir) {
		List<File> fileList = new ArrayList<File>();
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				fileList.addAll(getFiles(file));
			} else {
				fileList.add(file);
			}
		}
		return fileList;
	}

	private static void deleteFiles(File dir) {
		if (!dir.exists()) {
			return;
		}
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				deleteFiles(file);
			} else {
				file.delete();
			}
		}
		dir.delete();
	}
}
