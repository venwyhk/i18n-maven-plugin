package org.sulei.maven.plugins.i18n.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件操作工具类
 * 
 * @author Larry
 */
public class FileUtil {

	public static void readFilePath(String path, String suffixes, List<File> pathList) throws Exception {
		List<String> suffixList = new ArrayList<>();
		if (suffixes != null)
			for (String suffix : suffixes.trim().split("[,]"))
				suffixList.add(suffix);
		File file = new File(path);
		if (!file.exists())
			return;
		File[] tempList = file.listFiles();
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile())
				if (suffixList.size() > 0)
					for (String suffix : suffixList) {
						if (!(tempList[i] + "").endsWith(suffix))
							continue;
						pathList.add(tempList[i]);
					}
				else
					pathList.add(tempList[i]);
			if (tempList[i].isDirectory())
				readFilePath(tempList[i] + "", suffixes, pathList);
		}
	}

	public static Map<String, String> readPropFile(File file, String encoding) throws Exception {
		Map<String, String> map = new HashMap<>();
		try (FileInputStream fis = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(fis, encoding);
				BufferedReader br = new BufferedReader(isr)) {
			String s = null;
			while ((s = br.readLine()) != null)
				if (s != null && s.indexOf("=") > 0 && s.split("=").length == 2)
					map.put(s.split("=")[0], s.split("=")[1]);
		} catch (Exception e) {
			throw e;
		}
		return map;
	}

	public static void copyFile(File f1, File f2) throws Exception {
		if (!f2.getParentFile().exists() && !f2.getParentFile().mkdirs())
			throw new Exception("Create file '" + f2.getName() + "' directory error !");
		try (FileInputStream in = new FileInputStream(f1); FileOutputStream out = new FileOutputStream(f2)) {
			int length = 2097152;
			byte[] buffer = new byte[length];
			while (true) {
				int ins = in.read(buffer);
				if (ins == -1) {
					in.close();
					out.flush();
					out.close();
				} else
					out.write(buffer, 0, ins);
			}
		} catch (Exception e) {
		}
	}

	public static void copyAndReplaceFile(String oldFilePath, String newFilePath, String oldStr, String newStr,
			String encoding) throws Exception {
		try (FileInputStream fis = new FileInputStream(oldFilePath);
				InputStreamReader bsr = new InputStreamReader(fis, encoding);
				BufferedReader br = new BufferedReader(bsr);
				FileOutputStream fos = new FileOutputStream(newFilePath);
				OutputStreamWriter osw = new OutputStreamWriter(fos, encoding);
				BufferedWriter bw = new BufferedWriter(osw)) {
			String str = null;
			StringBuffer sb = new StringBuffer("");
			while ((str = br.readLine()) != null)
				sb.append(str + "\r\n");
			bw.write(sb.toString().replace(oldStr, newStr));
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}

	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++)
				if (!deleteDir(new File(dir, children[i])))
					return false;
		}
		return dir.delete();
	}

}
