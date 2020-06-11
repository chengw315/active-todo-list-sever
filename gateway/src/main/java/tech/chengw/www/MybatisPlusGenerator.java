package tech.chengw.www;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chengwj
 * @date 2020/4/17
 * @version 1.0
 **/
public class MybatisPlusGenerator {

    //region 需要配置的项：数据库url、用户名、密码、表名集合（要生成哪些表的代码）
    private static String DATA_URL;
    private static String DATA_NAME;
    private static String DATA_PWD;
    private static String[] TABLE_NAMES;
    //endregion
    //region 无需配置（可采取默认配置）的项：包名、路径
    private static final String MAPPER_PACKAGE = "dao";
    private static final String SERVICE_PACKAGE = "service";
    private static final String SERVICE_IMPL_PACKAGE = "service.impl";
    private static final String POJO_PACKAGE = "entity";
    private static final String CONTROLLER_PACKAGE = "web";
    private static final String MAPPER_XML_PACKAGE = "mapper";
    private static final String AUTHOR = "MybatisPlusGenerator";
    private static final String PARENT_PACKAGE = MybatisPlusGenerator.class.getPackage().getName();
    private static final String MODULE_PATH;
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    static  {
        String path = MybatisPlusGenerator.class.getResource("").getPath();
        MODULE_PATH = path.substring(0,path.indexOf("target"));
    }
    private static final String OUT_PUT_DIR = MODULE_PATH +"src/main/java";
    private static final String XML_OUT_PUT_DIR = MODULE_PATH+"src/main/resources/mapper/";
    //endregion

    public static void main(String[] args) {

        DATA_URL = "jdbc:mysql://106.15.251.107:3306/springcloudtest?useUnicode=true&characterEncoding=utf8";
        DATA_NAME = "root";
        DATA_PWD = "Chengw315";
        TABLE_NAMES = new String[]{"service1"};

        // 代码生成器
        new AutoGenerator()
                // 全局配置
                .setGlobalConfig(getGlobalConfig())
                // 数据源配置
                .setDataSource(getDataSourceConfig())
                // 包配置
                .setPackageInfo(getPackageConfig())
                .setCfg(getInjectionConfig())
                // 策略配置
                .setStrategy(getStrategyConfig())
                //生成代码
                .execute();
    }


    /**
     * @return 全局配置:生成代码的路径等
     */
    public static GlobalConfig getGlobalConfig() {

        return new GlobalConfig()
                .setOutputDir(OUT_PUT_DIR)
                .setDateType(DateType.ONLY_DATE)
                .setAuthor(AUTHOR)
                .setOpen(false)
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                // 覆盖生成的文件
                .setFileOverride(true)
                .setServiceName("%sService");
    }

    /**
     * @return 数据源配置
     */
    public static DataSourceConfig getDataSourceConfig() {
        return new DataSourceConfig()
                .setUrl(DATA_URL)
                .setDriverName(DRIVER_NAME)
                .setUsername(DATA_NAME)
                .setPassword(DATA_PWD);
    }

    /**
     * @return 包配置：控制生成代码所在的包
     */
    public static PackageConfig getPackageConfig() {
        return new PackageConfig()
                .setParent(PARENT_PACKAGE)
                .setMapper(MAPPER_PACKAGE)
                .setEntity(POJO_PACKAGE)
                .setService(SERVICE_PACKAGE)
                .setController(CONTROLLER_PACKAGE)
                .setServiceImpl(SERVICE_IMPL_PACKAGE)
                .setXml(MAPPER_XML_PACKAGE);
    }

    /**
     * @return 策略配置
     */
    public static StrategyConfig getStrategyConfig() {
        return new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityTableFieldAnnotationEnable(true)
                .setEntityLombokModel(true)
                .setInclude(TABLE_NAMES)
                //默认生成全部
                //.setExclude(null)
                .setTablePrefix("表前缀_")
                .setControllerMappingHyphenStyle(true);
    }

    /**
     * @return 额外的自定义配置：配置了生成XML的路径
     */
    public static InjectionConfig getInjectionConfig() {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        String templatePath = "/templates/mapper.xml.vm";
        // 自定义mapper.XML文件输出配置
        List<FileOutConfig> configs = new ArrayList<>();
        configs.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return XML_OUT_PUT_DIR + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(configs);
        return cfg;
    }

}
