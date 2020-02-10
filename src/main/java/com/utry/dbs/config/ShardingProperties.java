package com.utry.dbs.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "shardingsphere.datasource.inspection")
@Data
public class ShardingProperties {

    private String beginDate;

    private String endDate;

    private String strategyValue;
}
