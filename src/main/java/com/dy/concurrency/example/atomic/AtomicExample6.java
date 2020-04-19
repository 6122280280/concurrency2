package com.dy.concurrency.example.atomic;

import com.dy.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicExample6 {
    private static Logger log = Logger.getLogger(AtomicExample6.class);

    private static AtomicBoolean isHappened = new AtomicBoolean(false);
    private static boolean flag = false;
    //请求总数
    public static int clientTotal = 5000;
    //同时并发执行线程总数
    public static int threadLocal = 200;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadLocal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.info(e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("isHappened:{} "+isHappened.get());
    }

    private static void test() {

//        if(!flag){
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//            flag = true;
//            if(flag){
//                log.info("execute");
//            }
//        }


        if(isHappened.compareAndSet(false, true)){

            log.info("execute");
        }

    }
}
