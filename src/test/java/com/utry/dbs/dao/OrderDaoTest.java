//package com.utry.dbs.dao;
//
//import com.utry.dbs.ShardingDemoApplication;
//import com.utry.dbs.models.WorkOrder;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Calendar;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {ShardingDemoApplication.class})
//public class OrderDaoTest {
//
//    @Autowired
//    private OrderDao orderDao;
//
//
//    @Autowired
//    private WorkOrderDao  workOrderDao;
////    @Test
////    public void testInsertOrder(){
////        orderDao.insert(new BigDecimal(11),1L,"Success");
////    }
////
////    @Test
////    public void testBatchInsert(){
////        for(int i = 0;i < 20; i++){
////            orderDao.insert(new BigDecimal(i),2L,"Success");
////        }
////    }
//
//    @Test
//    public void testAdd(){
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_MONTH,-2);
//        for(int i=0;i<1000;i++) {
//            workOrderDao.insert("this is first work order 2"+i, calendar.getTime());
//        }
//    }
//
//
//    @Test
//    public void testSelectWorkOrderById(){
//        long currentTime = System.currentTimeMillis();
//        WorkOrder workOrder = workOrderDao.selectByID("431864260323180544");
//        long endTime = System.currentTimeMillis();
//        Assert.assertNotNull(workOrder);
//        System.out.println("the cost is "+(endTime - currentTime));
//    }
//}
