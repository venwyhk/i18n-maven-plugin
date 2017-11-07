...
<!-- 静态文件国际化工具插件 -->
<plugin>
  <groupId>org.sulei.maven.plugins</groupId>
  <artifactId>i18n-maven-plugin</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <!-- <configuration> -->
    <!-- 指定字符集 -->
    <!-- <encoding>UTF-8</encoding> -->
    <!-- 原始静态文件目录(目录中需要国际化的字端以'${KEY}'表示) -->
    <!-- <inputDirectory>webroot</inputDirectory> -->
    <!-- 国际化后生成的文件目录(会按照国际化文件名生成相应的子目录) -->
    <!-- <outputDirectory>webroot</outputDirectory> -->
    <!-- 国际化配置文件目录(配置文件内容格式以'KEY=VALUE'表示,可多行) -->
    <!-- <propDirectory>i18n</propDirectory> -->
    <!-- 需要进行国际化的文件后缀 -->
    <!-- <suffixes>.js,.css,.html,.htm</suffixes> -->
    <!-- 仅拷贝，不需要国际化的文件后缀 -->
    <!-- <cpSuffixes>.png,.jpg</cpSuffixes> -->
    <!-- 国际化配置文件后缀 -->
    <!-- <propSuffixes>.properties</propSuffixes> -->
    <!-- 变量前标识 -->
    <!-- <firstSign>${</firstSign> -->
    <!-- 变量后标识 -->
    <!-- <finalSign>}</finalSign> -->
  <!-- </configuration> -->
  <executions>
    <execution>
      <phase>compile</phase>
      <goals>
        <goal>webroot</goal>
      </goals>
    </execution>
  </executions>
</plugin>
...
