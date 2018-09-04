package org.sulei.maven.plugins.i18n;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.sulei.maven.plugins.i18n.utils.FileUtil;

/**
 * 静态文件国际化Mojo
 * 
 * @author Larry
 */
@Mojo(name = "webroot")
public class I18nMojo extends AbstractMojo {

	@Parameter(property = "encoding", defaultValue = "UTF-8")
	private String encoding;

	@Parameter(property = "inputDirectory", defaultValue = "webroot")
	private String inputDirectory;

	@Parameter(property = "suffixes", defaultValue = ".js,.css,.html,.htm")
	private String suffixes;

	@Parameter(property = "cpSuffixes", defaultValue = ".png,.jpg")
	private String cpSuffixes;

	@Parameter(property = "outputDirectory", defaultValue = "webroot")
	private String outputDirectory;

	private List<File> pathList = new ArrayList<File>();

	private List<File> cpPathList = new ArrayList<File>();

	@Parameter(property = "propDirectory", defaultValue = "i18n")
	private String propDirectory;

	@Parameter(property = "propSuffixes", defaultValue = ".properties")
	private String propSuffixes;

	private List<File> propPathList = new ArrayList<File>();

	private Map<String, Map<String, String>> propMap = new HashMap<String, Map<String, String>>();

	@Parameter(property = "firstSign", defaultValue = "${")
	private String firstSign;

	@Parameter(property = "finalSign", defaultValue = "}")
	private String finalSign;

	public void execute() throws MojoExecutionException, MojoFailureException {
		try {
			getLog().info("Start execute i18n.");
			FileUtil.readFilePath(propDirectory + File.separator, propSuffixes, propPathList);
			for (File file : propPathList)
				propMap.put(file.getName().split(propSuffixes)[0], FileUtil.readPropFile(file, encoding));
			List<String> keyList = getKeyList(propMap);
			for (String key : keyList) {
				File outputDir = new File(outputDirectory + File.separator + key);
				if (outputDir.exists()) {
					getLog().warn("Deleting old i18n directory '" + outputDir + "'.");
					FileUtil.deleteDir(outputDir);
				}
			}
			FileUtil.readFilePath(inputDirectory + File.separator, suffixes, pathList);
			FileUtil.readFilePath(inputDirectory + File.separator, cpSuffixes, cpPathList);
			getLog().debug("Create i18n directory.");
			for (String key : keyList) {
				File outputDir = new File(outputDirectory + File.separator + key);
				outputDir.mkdir();
				Map<String, String> langMap = propMap.get(key);
				List<String> langKeyList = getKeyList(langMap);
				for (File file : pathList) {
					File newFile = new File(file.toString().replace(inputDirectory + File.separator,
							outputDir.toString() + File.separator));
					getLog().debug("Copy file '" + file.toString() + "' to '" + newFile.toString() + "'.");
					FileUtil.copyFile(file, newFile);
					getLog().debug("Replace file " + file.toString() + " langKey.");
					for (String langKey : langKeyList)
						FileUtil.copyAndReplaceFile(newFile.toString(), newFile.toString(),
								firstSign + langKey + finalSign, langMap.get(langKey), encoding);
				}
				getLog().debug("Replace files finish.");
				for (File file : cpPathList)
					FileUtil.copyFile(file, new File(file.toString().replace(inputDirectory + File.separator,
							outputDir.toString() + File.separator)));
				getLog().debug("Copy files finish.");
			}
		} catch (Exception e) {
			getLog().error(e.getMessage());
			e.printStackTrace();
		}
	}

	private List<String> getKeyList(Map<String, ?> propMap) {
		List<String> keyList = new ArrayList<String>();
		Iterator<?> i = propMap.entrySet().iterator();
		while (i.hasNext()) {
			@SuppressWarnings("unchecked")
			Entry<String, ?> entry = (Entry<String, ?>) i.next();
			keyList.add(entry.getKey());
		}
		return keyList;
	}

}
