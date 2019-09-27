package com.wei.order.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: weizz
 * @Date: 2019/9/27 14:26
 * @Description:
 * @Version:1.0
 */
@Service
public class TestServiceImpl {

    //单独调用methodB方法时，因为当前上下文不存在事务，所以会开启一个新的事务。
    //调用methodA方法时，因为当前上下文不存在事务，所以会开启一个新的事务。
    // 当执行到methodB时，methodB发现当前上下文有事务，因此就加入到当前事务中来。
    @Transactional(propagation= Propagation.REQUIRED)
    public void  methodA(){
        //do something
        methodB();
    }
    @Transactional(propagation= Propagation.REQUIRED)
    public void  methodB(){
        //do something
    }
    //单纯的调用methodD时，methodD方法是非事务的执行的。
    // 当调用methdC时,methodD则加入了methodC的事务中,事务地执行。
    @Transactional(propagation= Propagation.REQUIRED)
    public void  methodC(){
        //do something
        methodD();
    }
    @Transactional(propagation= Propagation.SUPPORTS)
    public void  methodD(){
        //do something
    }
    //如果已经存在一个事务，支持当前事务。如果没有一个活动的事务，则抛出异常。
    @Transactional(propagation= Propagation.REQUIRED)
    public void  methodE(){
        //do something
        methodF();
    }
    @Transactional(propagation= Propagation.MANDATORY)
    public void  methodF(){
        //do something
    }

    //使用PROPAGATION_REQUIRES_NEW,需要使用 JtaTransactionManager作为事务管理器。
    //它会开启一个新的事务。如果一个事务已经存在，则先将这个存在的事务挂起。
    //在这里，我把ts1称为外层事务，ts2称为内层事务。从代码可以看出，ts2与ts1是两个独立的事务，互不相干。
    // Ts2是否成功并不依赖于 ts1。如果methodA方法在调用methodB方法后的doSomeThingB方法失败了，而methodB方法所做的结果依然被提交。
    // 而除了methodB之外的其它代码导致的结果却被回滚了
    @Transactional(propagation= Propagation.REQUIRED)
    public void  methodG(){
        //do something
        methodH();
    }
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public void  methodH(){
        //do something
    }
    //总是非事务地执行，如果存在一个活动事务，则抛出异常。
    @Transactional(propagation= Propagation.NEVER)
    public void  methodI(){
        //do something
    }
    //如果一个活动的事务存在，则运行在一个嵌套的事务中。
    // 如果没有活动事务, 则按TransactionDefinition.PROPAGATION_REQUIRED 属性执行。
    //嵌套事务一个非常重要的概念就是内层事务依赖于外层事务。
    // 外层事务失败时，会回滚内层事务所做的动作。而内层事务操作失败并不会引起外层事务的回滚。
    @Transactional(propagation= Propagation.REQUIRED)
    public void  methodJ(){
        //do something
        methodK();
    }
    @Transactional(propagation= Propagation.NESTED)
    public void  methodK(){
        //do something
    }
}
