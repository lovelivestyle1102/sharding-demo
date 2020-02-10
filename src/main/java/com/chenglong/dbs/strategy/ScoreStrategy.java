package com.chenglong.dbs.strategy;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

public class ScoreStrategy implements PreciseShardingAlgorithm<String> {

    private int strategyLength;

    public ScoreStrategy(int strategyLength){
        this.strategyLength = strategyLength;
    }

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {

        String tableValue = availableTargetNames.stream().filter(x -> x.contains(shardingValue.getValue().substring(shardingValue.getValue().length()-strategyLength))).findFirst().get();

        return tableValue;
    }
}
