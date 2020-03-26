package cn.ucmed.common.dbplus;

import cn.ucmed.common.utils.DateUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.sun.javafx.PlatformUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.baomidou.mybatisplus.generator.config.rules.DateType.ONLY_DATE;

public class MbpCodeGenerator {
    private static final String SRC_MAIN_JAVA = "/src/main/java/"; // 基本路径
    public static final String MODULAR_NAME = "zsyy-commom"; // modular 名字
    private static final String PARENT_NAME = "cn.ucmed.common";  // 代码生成包
    private static final String AUTHOR = "zhch"; // 作者

    private static final boolean REST_CONTROLLER_STYLE = false; // 是否rest接口
    private static final boolean CONTROLLER_STYLE = false; // 是否rest接口

    private static final String JDBC_MYSQL_URL = "jdbc:mysql://127.0.0.1:3306/bootdo-demo?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
    private static final String JDBC_DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "123456";

    public static void main(String[] args) {
        String moduleName = "dbplus";
        String tableName = "sys_file";
        String tablePrefix = "";

        atuoGenerator(moduleName,tableName,tablePrefix);
    }

    private static void atuoGenerator(String moduleName,String tableName,String tablePrefix) {
        new AutoGenerator()
                .setGlobalConfig(getGlobalConfig())
                .setDataSource(getDataSourceConfig())
                .setPackageInfo(getPackageConfig(moduleName))
                .setStrategy(getStrategyConfig(tableName,tablePrefix))
                .setCfg(getInjectionConfig())
                .setTemplate(getTemplateConfig())
                .execute();
    }

    private static InjectionConfig getInjectionConfig() {
        return new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String,Object> map = new HashMap<>();
                String dateTime = DateUtil.dateTimeToString(new Date());
                map.put("dateTime",dateTime);
                setMap(map);
            }
        };
    }

    /**
     * 4.策略配置
     */
    private static StrategyConfig getStrategyConfig(String tableName, String tablePrefix) {
        return new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)  // 下划线转驼峰命名
                .setColumnNaming(NamingStrategy.underline_to_camel) // 下划线转驼峰命名
                .setInclude(tableName)
                .setRestControllerStyle(REST_CONTROLLER_STYLE)
                .setControllerMappingHyphenStyle(CONTROLLER_STYLE)
                .entityTableFieldAnnotationEnable(true)
                .setTablePrefix(tablePrefix + "_");
    }

    /**
     * 3.包名策略配置
     */
    private static PackageConfig getPackageConfig(String moduleName) {
        return new PackageConfig()
                .setModuleName(moduleName)
                .setParent(PARENT_NAME)
                .setMapper("mapper")//dao
                .setService("service")//servcie
                .setServiceImpl("service.impl") // impl
//                .setController("controller")//controller
                .setEntity("entity")
                .setXml("mapper.mapping");//mapper.xml
    }

    /**
     * 2.数据源配置
     */
    private static DataSourceConfig getDataSourceConfig() {
        return new DataSourceConfig()
                .setUrl(JDBC_MYSQL_URL)
                .setDriverName(JDBC_DRIVER_NAME)
                .setUsername(JDBC_USERNAME)
                .setPassword(JDBC_PASSWORD);
    }

    /**
     * 1.全局配置
     */
    private static GlobalConfig getGlobalConfig() {
        String projectPath = System.getProperty("user.dir");
        //封装模板数据
        String filePath = projectPath + "/" + MODULAR_NAME + SRC_MAIN_JAVA;
        if (PlatformUtil.isWindows()) {
            filePath = filePath.replaceAll("/+|\\\\+", "\\\\");
        } else {
            filePath = filePath.replaceAll("/+|\\\\+", "/");
        }
        return new GlobalConfig()
                .setOutputDir(filePath)
                .setDateType(ONLY_DATE)
                .setIdType(IdType.UUID)
                .setAuthor(AUTHOR)
                .setBaseColumnList(true)
                .setSwagger2(true)
                .setEnableCache(false)
                .setBaseResultMap(true)
                .setOpen(false);
    }

    /**
     * 5.模板位置
     */
    private static TemplateConfig getTemplateConfig(){
        return new TemplateConfig()
                .setXml(null)//暂时不使用自定义配置
                ;
    }

}
