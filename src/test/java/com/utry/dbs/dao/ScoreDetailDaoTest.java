//package com.utry.dbs.dao;
//
//import com.utry.dbs.ShardingDemoApplication;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Arrays;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {ShardingDemoApplication.class})
//public class ScoreDetailDaoTest {
//
//    @Autowired
//    private ScoreDetailDao scoreDetailDao;
//
//    @Test
//    public void testInsert(){
//        scoreDetailDao.insert("this is the detail ");
//    }
//
//    @Test
//    public void testSelect(){
//        String name = scoreDetailDao.selectById(1);
//        System.out.println(name);
//    }
//
//}
