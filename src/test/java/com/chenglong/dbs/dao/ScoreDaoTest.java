package com.chenglong.dbs.dao;

import com.chenglong.dbs.config.ShardingProperties;
import com.chenglong.dbs.ShardingDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
