package com.chenglong.dbs.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

public class DataSourceUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceUtils.class);

    /**
     *
     * 根据规则创建表，这里的connection不需要释放。因为这里是从连接池中获取的，所以不用手动释放。
     *
     * @param dataSource
     * @param tableName
     * @return
     */
    public static String createScoreTable(DataSource dataSource,String tableName) {
        String realName = "t_score_"+tableName;

        String createSql = String.format("CREATE TABLE `%s` "+
        "("+
        "                score_id varchar(32) PRIMARY KEY NOT NULL,"+
        "                score_aid_id varchar(32) NOT NULL,"+
        "                score_content text NOT NULL,"+
        "                create_time timestamp NOT NULL"+
        ");",realName);

        String indexSql = String.format("CREATE UNIQUE INDEX t_score_aid_id_uindex ON `%s` (score_aid_id);",realName,realName);


        try{
            Connection conn = dataSource.getConnection();

            Statement stmt = conn.createStatement();

            stmt.executeUpdate(createSql);

            stmt.executeUpdate(indexSql);

            return realName;
        }catch (Exception e){
            LOGGER.warn("the {} is already exit",realName);

            return realName;
        }
    }
}
