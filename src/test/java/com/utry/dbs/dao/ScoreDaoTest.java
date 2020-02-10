package com.utry.dbs.dao;

import com.utry.dbs.ShardingDemoApplication;
import com.utry.dbs.config.ShardingProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ShardingDemoApplication.class})
public class ScoreDaoTest {

    @Autowired
    private ScoreDao scoreDao;

    @Autowired
    private ShardingProperties shardingProperties;

    @Test
    public void testAdd(){
        scoreDao.insert("osufs008df0sdfs8fssds02","osufs008df0sdfs8fssds0220200209","this is a funny test",new Date());
    }

    @Test
    public void testSelect(){
        scoreDao.selectByAidId("osufs008df0sdfs8fds020200204");
    }

    @Test
    public void testGenerateActualDataNodes(){
    }


}
