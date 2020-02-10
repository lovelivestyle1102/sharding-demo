package com.chenglong.dbs.config;

import com.chenglong.dbs.strategy.ScoreStrategy;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Configuration
public class ShardingDataSourceConfig {


    @Autowired
    private ShardingProperties shardingProperties;

    @Autowired
    private InspectionDataSourceConfig inspectionDataSourceConfig;

    /** 按日分表策略 */
    private static final String DAY_SEGMENTATION_STRATEGY = "DAY";

    /** 按月分表策略 */
    private static final String MONTH_SEGMENTATION_STRATEGY = "MONTH";

    /** 按年分表策略 */
    private static final String YEAR_SEGMENTATION_STRATEGY = "YEAR";


    /**
     *
     * sharding jdbc 数据库相关配置.
     *
     * @return
     * @throws SQLException
     */
    @Bean
    DataSource getShardingDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, new Properties());
    }

    /**
     *
     * 数据库配置
     *
     * @return
     */
    private Map<String, DataSource> createDataSourceMap() {
        Map<String,DataSource> sourceMap = new HashMap<>(2);
        sourceMap.put(inspectionDataSourceConfig.getDataBaseName(),inspectionDataSourceConfig.dataSource());
        return sourceMap;
    }


    /**
     *
     * 分表相关配置
     *
     * @return
     */
    TableRuleConfiguration getOrderTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("t_score", generateActualDataNodes("t_score",shardingProperties));
        if(DAY_SEGMENTATION_STRATEGY.equals(shardingProperties.getStrategyValue())&&MONTH_SEGMENTATION_STRATEGY.equals(shardingProperties.getStrategyValue())&&YEAR_SEGMENTATION_STRATEGY.equals(shardingProperties.getStrategyValue())){
            throw new RuntimeException("只支持按年月日分表！");
        }
        int strategyLength = DAY_SEGMENTATION_STRATEGY.equals(shardingProperties.getStrategyValue())?8:MONTH_SEGMENTATION_STRATEGY.equals(shardingProperties.getStrategyValue())?6:4;
        result.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("score_aid_id",new ScoreStrategy(strategyLength)));
        return result;
    }

    /**
     *
     * 根据配置分表策略计算真实节点，目前支持年月日三种。
     *
     *
     * @param prefix
     * @param shardingProperties
     * @return
     */
    private String generateActualDataNodes(String prefix,ShardingProperties shardingProperties) {
        StringBuilder actualDataNodes = new StringBuilder();

        actualDataNodes.append("inspection.").append("${[");

        if(StringUtils.isEmpty(shardingProperties.getBeginDate())){
            throw new RuntimeException("请配置分片开始时间");
        }

        if(StringUtils.isEmpty(shardingProperties.getEndDate())){
            throw new RuntimeException("请配置分片结束时间");
        }

        LocalDate beginDate = LocalDate.parse(shardingProperties.getBeginDate(), DateTimeFormatter.ofPattern("yyyyMMdd"));

        LocalDate endDate = LocalDate.parse(shardingProperties.getEndDate(),DateTimeFormatter.ofPattern("yyyyMMdd"));

        while (beginDate.isBefore(endDate)){
            actualDataNodes.append('\'').append(prefix).append("_");;

            if(DAY_SEGMENTATION_STRATEGY.equals(shardingProperties.getStrategyValue())){
                actualDataNodes.append(beginDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
                beginDate = beginDate.plusDays(1L);
            }

            if(MONTH_SEGMENTATION_STRATEGY.equals(shardingProperties.getStrategyValue())){
                actualDataNodes.append(beginDate.format(DateTimeFormatter.ofPattern("yyyyMM")));
                beginDate = beginDate.plusMonths(1L);
            }

            if(YEAR_SEGMENTATION_STRATEGY.equals(shardingProperties.getStrategyValue())){
                actualDataNodes.append(beginDate.format(DateTimeFormatter.ofPattern("yyyy")));
                beginDate = beginDate.plusYears(1L);
            }

            actualDataNodes.append('\'').append(",");
        }

        actualDataNodes.deleteCharAt(actualDataNodes.length()-1);

        actualDataNodes.append("]}");

        return actualDataNodes.toString();
    }


}
