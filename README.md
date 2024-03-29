[![](https://jitpack.io/v/venwyhk/i18n-maven-plugin.svg)](https://jitpack.io/#venwyhk/i18n-maven-plugin)

# i18n-maven-plugin #

##### Maven #####

pom.xml

```xml
    ......
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
    ......
    <!-- 静态文件国际化工具插件 -->
    <plugin>
        <groupId>com.github.venwyhk</groupId>
        <artifactId>i18n-maven-plugin</artifactId>
        <version>0.0.1</version>
        <!-- <configuration> -->
            <!-- 指定字符集 -->
            <!-- <encoding>UTF-8</encoding> -->
            <!-- 原始静态文件目录(目录中需要国际化的字端以'@{KEY}@'表示) -->
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
            <!-- <firstSign>@{</firstSign> -->
            <!-- 变量后标识 -->
            <!-- <finalSign>}@</finalSign> -->
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
    ......
```

***

[![](https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png)](https://creativecommons.org/licenses/by-nc-sa/4.0/)&nbsp;&nbsp;[![](https://www.gnu.org/graphics/lgplv3-88x31.png)](https://www.gnu.org/licenses/lgpl-3.0.en.html)&nbsp;&nbsp;[![](https://maven.apache.org/images/logos/maven-feather.png)](https://maven.apache.org/)
