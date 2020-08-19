package com.ffw.mybatis.generator.mybatis;

import com.ffw.common.StringUtils.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * JAVA代码控制mybatis-generator
 * 参考官方文档:http://mybatis.org/generator/running/runningWithJava.html
 * @Author fufengwen
 * @Time 2020/8/6 11:07
 */
public class MybatisGeneratorUtil {

    public static void yashaHrGenerate(String dataSourceName,String packageName,String moduleName,String tableName){
        try{
            String root = System.getProperty("user.dir");
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            Configuration config = new Configuration();
            //   ...此处填写合适的config对象,为适应亚厦原先的xml方式，在此将原先generatorConfig.xml文件里面的bean都在此转换
            //模型定为flat，表示生成的model类所有字段都生成，其他类型感兴趣可以自己查一下
            Context context = new Context(ModelType.FLAT);
            //设置id和mybatis版本类型
            context.setId("context");
            context.setTargetRuntime("MyBatis3");
            //设置数据源
            switch(dataSourceName){
                case "hr":
                    context.addProperty("jdbc.dataSourceName","hr");
                    context.addProperty("jdbc.driverClass","org.postgresql.Driver");
                    context.addProperty("jdbc.connectionURL","jdbc:postgresql://10.10.20.132:5531/yasha_hr");
                    context.addProperty("jdbc.userId","tpg_admin");
                    context.addProperty("jdbc.password","pG4tk8Rfd");
                    break;
                case "erp":
                    context.addProperty("jdbc.dataSourceName","erp");
                    context.addProperty("jdbc.driverClass","org.postgresql.Driver");
                    context.addProperty("jdbc.connectionURL","");
                    context.addProperty("jdbc.userId","");
                    context.addProperty("jdbc.password","");
                    break;
                default:throw new RuntimeException("数据源不存在或未配置!");
            }

            //设置增删改查插件
            PluginConfiguration BatchInsertPlugin = new PluginConfiguration();
            BatchInsertPlugin.setConfigurationType("com.imtech.jpa.mybaties.batch.BatchInsertPlugin");
            context.addPluginConfiguration(BatchInsertPlugin);
            PluginConfiguration BatchDeleteByPrimaryKeyUUIDTPlugin = new PluginConfiguration();
            BatchDeleteByPrimaryKeyUUIDTPlugin.setConfigurationType("com.imtech.jpa.mybaties.batch.BatchDeleteByPrimaryKeyUUIDTPlugin");
            context.addPluginConfiguration(BatchDeleteByPrimaryKeyUUIDTPlugin);
            PluginConfiguration BatchUpdateByPrimaryKeyUUIDTPlugin = new PluginConfiguration();
            BatchUpdateByPrimaryKeyUUIDTPlugin.setConfigurationType("com.imtech.jpa.mybaties.batch.BatchUpdateByPrimaryKeyUUIDTPlugin");
            context.addPluginConfiguration(BatchUpdateByPrimaryKeyUUIDTPlugin);
            PluginConfiguration BatchSelectByPrimaryKeyPlugin = new PluginConfiguration();
            BatchSelectByPrimaryKeyPlugin.setConfigurationType("com.imtech.jpa.mybaties.batch.BatchSelectByPrimaryKeyPlugin");
            context.addPluginConfiguration(BatchSelectByPrimaryKeyPlugin);
            //设置序列化插件
            PluginConfiguration SerializablePlugin = new PluginConfiguration();
            SerializablePlugin.setConfigurationType("org.mybatis.generator.plugins.SerializablePlugin");
            context.addPluginConfiguration(SerializablePlugin);
            //设置xml文件覆盖自定义的插件
            PluginConfiguration OverwriteXmlPlugin = new PluginConfiguration();
            OverwriteXmlPlugin.setConfigurationType("com.imtech.yasha.hr.common.utils.mybatis.OverwriteXmlPlugin");
            context.addPluginConfiguration(OverwriteXmlPlugin);

            /*注释生成器配置*/
            CommentGeneratorConfiguration commentGeneratorConfig = new CommentGeneratorConfiguration();
            commentGeneratorConfig.setConfigurationType("com.imtech.jpa.utils.MybatiesCommentGenerator");
            context.setCommentGeneratorConfiguration(commentGeneratorConfig);


            /*JDBC连接信息配置*/
            JDBCConnectionConfiguration jdbcConnectionConfig = new JDBCConnectionConfiguration();
            //注意代码配置中JDBC连接字符串中的参数分隔符不需要再像xml配置文件中那样使用转义符
            jdbcConnectionConfig.setDriverClass(context.getProperty("jdbc.driverClass"));
            jdbcConnectionConfig.setConnectionURL(context.getProperty("jdbc.connectionURL"));
            jdbcConnectionConfig.setUserId(context.getProperty("jdbc.userId"));
            jdbcConnectionConfig.setPassword(context.getProperty("jdbc.password"));

            //mysql需单独再加属性
            if(context.getProperty("jdbc.driverClass").contains("mysql")){
                jdbcConnectionConfig.addProperty("nullCatalogMeansCurrent", "true");//MySQL无法识别table标签中schema类的配置，所以在URL上指明目标数据库，并追加nullCatalogMeansCurrent属性为true
                jdbcConnectionConfig.addProperty("remarksReporting", "true");//针对oracle数据库无法读取表和字段备注
                jdbcConnectionConfig.addProperty("useInformationSchema", "true");//针对mysql数据库无法读取表和字段备注
            }
            //jdbc配置完成,注入容器
            context.setJdbcConnectionConfiguration(jdbcConnectionConfig);

            //javaTypeResolver配置
            JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
            javaTypeResolverConfiguration.addProperty("forceBigDecimals","false");
            context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);

            /*实体类Model生成器配置*/
            JavaModelGeneratorConfiguration javaModelGeneratorConfig = new JavaModelGeneratorConfiguration();
            javaModelGeneratorConfig.setTargetProject(root+"/"+packageName+"/src/main/java");//目标项目(源码主路径)
            //细分多级子模块
            if(StringUtils.isNotBlank(moduleName)){
                javaModelGeneratorConfig.setTargetPackage("com.imtech.yasha."+dataSourceName+".common.mapper.dto."+dataSourceName+"."+moduleName);//目标包(Model类文件存放包)
            }else{
                javaModelGeneratorConfig.setTargetPackage("com.imtech.yasha."+dataSourceName+".common.mapper.dto."+dataSourceName);//目标包(Model类文件存放包)
            }
            javaModelGeneratorConfig.addProperty("enableSubPackages","false");
            javaModelGeneratorConfig.addProperty("trimStrings","true");
            context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfig);

            /*SqlMapper生成器配置(*Mapper.xml类文件)，要javaClient生成器类型配合*/
            SqlMapGeneratorConfiguration sqlMapGeneratorConfig = new SqlMapGeneratorConfiguration();
            sqlMapGeneratorConfig.setTargetProject(root+"/"+packageName+"/src/main/resources");//目标项目(源码主路径)
            //细分多级子模块
            if(StringUtils.isNotBlank(moduleName)){
                sqlMapGeneratorConfig.setTargetPackage("mapper."+dataSourceName+"."+moduleName);//目标包(*Mapper.xml类文件存放包)
            }else{
                sqlMapGeneratorConfig.setTargetPackage("mapper."+dataSourceName);//目标包(*Mapper.xml类文件存放包)
            }
            context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfig);

            /*JavaClient生成器配置(*Mapper.java类文件)*/
            JavaClientGeneratorConfiguration javaClientGeneratorConfig = new JavaClientGeneratorConfiguration();
            javaClientGeneratorConfig.setConfigurationType("XMLMAPPER");//JavaClient生成器类型(主要有ANNOTATEDMAPPER、MIXEDMAPPER、XMLMAPPER，要Context的TargetRuntime配合)
            javaClientGeneratorConfig.setTargetProject(root+"/"+packageName+"/src/main/java");//目标项目(源码主路径)
            //细分多级子模块
            if(StringUtils.isNotBlank(moduleName)){
                javaClientGeneratorConfig.setTargetPackage("com.imtech.yasha."+dataSourceName+".common.mapper.dao."+dataSourceName+"."+moduleName);//目标包(*Mapper.java类文件存放包)
            }else{
                javaClientGeneratorConfig.setTargetPackage("com.imtech.yasha."+dataSourceName+".common.mapper.dao."+dataSourceName);//目标包(*Mapper.java类文件存放包)
            }
            javaClientGeneratorConfig.addProperty("enableSubPackages","false");
            context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfig);

            /*表生成配置*/
            TableConfiguration tableConfig = new TableConfiguration(context);
            tableConfig.setTableName(tableName);
            tableConfig.setSelectByExampleStatementEnabled(true);
            tableConfig.setDeleteByExampleStatementEnabled(true);
            tableConfig.setUpdateByPrimaryKeyStatementEnabled(true);
            tableConfig.setCountByExampleStatementEnabled(true);
            //GeneratedKey generatedKey = new GeneratedKey("id", "JDBC", true, null);//设置主键列和生成方式
            //tableConfig.setGeneratedKey(generatedKey);
            context.addTableConfiguration(tableConfig);

            //配置完成，添加，开始生成文件
            config.addContext(context);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        }catch(Exception e){
            throw new RuntimeException("生成异常！"+e.getMessage());
        }
    }

}
