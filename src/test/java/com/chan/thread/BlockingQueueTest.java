package com.chan.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;


@Slf4j
public class BlockingQueueTest {

    public static void main(String[] args) {
        int count = 1000;
//        for (int i = 0; i < count; i++) {
        //测试在多线程添加1000条数据时 两者性能较近 list稍快一丝
        BlockingQueueTest test = new BlockingQueueTest();
        List list = test.list1(count);
        log.info("list.size:{}", list.size());

        LinkedBlockingQueue queue1 = test.queue1(count);
        log.info("queue1.size:{}", queue1.size());

        log.info("{}", "\n");
//        }
//        testCountDownLatch();
//        list.forEach(item -> log.info("var:{}", item));
    }

    /**
     * 多线程并发的情况下使用LinkedBlockingQueue不会少数据 因为LinkedBlockingQueue内部在做操作的时候有使用代码锁
     */
    public LinkedBlockingQueue queue1(int count) {

        LinkedBlockingQueue queue = new LinkedBlockingQueue(count);
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= count; i++) {
            int finalI1 = i;
            new Thread(new Runnable() {
                public void run() {
//                    log.info("curId:{}", Thread.currentThread().getId());
                    queue.add("queue" + finalI1);
                    countDownLatch.countDown();
                }
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        log.info("消耗时间:{}", endTime - startTime);
        return queue;
    }

    /**
     * 多线程并发的情况下使用List不加锁会少数据 例如这里循环一千次 有可能只有999个数据 也有可能是1000个
     */
    public List list1(int count) {
        List list = new ArrayList(count);
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= count; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                public void run() {
//                    log.info("curId:{}", Thread.currentThread().getId());
                    list.add("list_" + finalI + "_" + Thread.currentThread().getId());
                    //递减锁存器的计数，如果计数到达零，则释放所有等待的线程
                    countDownLatch.countDown();
                }
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        log.info("消耗时间:{}", endTime - startTime);
        return list;
    }

}

