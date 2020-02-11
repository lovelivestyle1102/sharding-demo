package com.chenglong.dbs.strategy;

import com.chenglong.dbs.utils.DataSourceUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ScoreStrategy implements PreciseShardingAlgorithm<String> {

    private int strategyLength;

    private DataSource dataSource;

    /** 分片键缓存 */
    private Map<String,String> suffixCache = new HashMap(500);

    public ScoreStrategy(int strategyLength,DataSource dataSource){
        this.strategyLength = strategyLength;
        this.dataSource = dataSource;
    }

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {

        String tableSuffix = shardingValue.getValue().substring(shardingValue.getValue().length()-strategyLength);

        String tableNameCache = suffixCache.get(tableSuffix);

        if(!StringUtils.isEmpty(tableNameCache)){
            return tableNameCache;
        }

        Optional<String> result = availableTargetNames.stream().filter(x -> x.contains(tableSuffix)).findFirst();

        if(result.isPresent()){

            suffixCache.put(tableSuffix,result.get());

            return result.get();
        }else{
            String tableName = DataSourceUtils.createScoreTable(dataSource,tableSuffix);

            if(!StringUtils.isEmpty(tableName)) {

                suffixCache.put(tableSuffix,tableName);

                return tableName;
            }else{
                throw new RuntimeException("计算实际节点出错！");
            }
        }
    }
}
