package com.wei.order;

import com.wei.model.XcTask;
import com.wei.order.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    public void testTask() {
        List<XcTask> xcTasks = orderService.selectTaskBeforeTime();
        System.out.println(xcTasks.size());
    }

}
