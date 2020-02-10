package com.chenglong.dbs.strategy;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class WorkOrderStrategy implements PreciseShardingAlgorithm<Date> {


    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Date> preciseShardingValue) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        String preciseValue = simpleDateFormat.format(preciseShardingValue.getValue());

        String tableValue = collection.stream().filter(x -> x.contains(preciseValue)).findFirst().get();

        return tableValue;
    }
}
