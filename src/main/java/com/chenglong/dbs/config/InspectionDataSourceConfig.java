package com.chenglong.dbs.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Data
@ConfigurationProperties("shardingsphere.datasource.inspection")
@Component
public class InspectionDataSourceConfig {


    private String url;

    private String username;

    private String password;

    private String driverClassName;

    private String dataBaseName;


    /**
     * 配置数据源1，数据源的名称最好要有一定的规则，方便配置分库的计算规则
     * @return
     */

    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

}
