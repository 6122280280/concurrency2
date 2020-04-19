package com.dy.concurrency.example.count;

import com.dy.concurrency.annotation.NotThreadSafe;
import com.dy.concurrency.annotation.ThreadSafe;
import org.apache.log4j.Logger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


@ThreadSafe
public class CountExample3 {
    private static Logger log = Logger.getLogger(CountExample3.class);
    //请求总数
    public static int clientTotal = 5000;
    //同时并发执行线程总数
    public static int threadLocal = 200;
    //
    public static int count = 0;

    private synchronized static void add(){
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadLocal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.info(e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count{}"+count);
    }
}
