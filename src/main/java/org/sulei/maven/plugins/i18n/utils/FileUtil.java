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
		List<String> suffixList = new ArrayList<String>();
		if (suffixes != null) {
			for (String suffix : suffixes.trim().split("[,]")) {
				suffixList.add(suffix);
			}
		}
		File file = new File(path);
		if (!file.exists()) {
			throw new Exception("Directory '" + path + "' is not exists !");
		}
		File[] tempList = file.listFiles();
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				if (suffixList.size() > 0) {
					for (String suffix : suffixList) {
						String filePath = tempList[i] + "";
						if (filePath.endsWith(suffix)) {
							pathList.add(tempList[i]);
						} else {
							continue;
						}
					}
				} else {
					pathList.add(tempList[i]);
				}
			}
			if (tempList[i].isDirectory()) {
				readFilePath(tempList[i] + "", suffixes, pathList);
			}
		}
	}

	public static Map<String, String> readPropFile(File file, String encoding) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		BufferedReader br = null;
		FileInputStream fis = null;
		InputStreamReader isr = null;
		try {
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis, encoding);
			br = new BufferedReader(isr);
			String s = null;
			while ((s = br.readLine()) != null) {
				if (s != null && s.indexOf("=") > 0 && s.split("=").length == 2) {
					map.put(s.split("=")[0], s.split("=")[1]);
				}
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
				}
			}
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
				}
			}
		}
		return map;
	}

	public static void copyFile(File f1, File f2) throws Exception {
		if (!f2.getParentFile().exists()) {
			if (!f2.getParentFile().mkdirs()) {
				throw new Exception("Create file '" + f2.getName() + "' directory error !");
			}
		}
		try {
			int length = 2097152;
			FileInputStream in = new FileInputStream(f1);
			FileOutputStream out = new FileOutputStream(f2);
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
		StringBuffer sb = new StringBuffer("");
		FileInputStream fis = null;
		InputStreamReader bsr = null;
		BufferedReader br = null;
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {

			fis = new FileInputStream(oldFilePath);
			bsr = new InputStreamReader(fis, encoding);
			br = new BufferedReader(bsr);
			String str = null;
			while ((str = br.readLine()) != null) {
				sb.append(str + "\r\n");
			}

			fos = new FileOutputStream(newFilePath);
			osw = new OutputStreamWriter(fos, encoding);
			bw = new BufferedWriter(osw);
			
			bw.write(sb.toString().replace(oldStr, newStr));

		} catch (FileNotFoundException e) {
			throw new Exception(e.getMessage());
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
				}
			}
			if (bsr != null) {
				try {
					bsr.close();
				} catch (IOException e) {
				}
			}
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
			if (osw != null) {
				try {
					osw.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}

}
