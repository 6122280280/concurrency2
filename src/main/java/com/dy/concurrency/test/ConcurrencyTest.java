package com.dy.concurrency.test;

import com.dy.concurrency.annotation.NotThreadSafe;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;

import java.util.concurrent.*;

@Slf4j
@NotThreadSafe
public class ConcurrencyTest {
    private static Logger log = Logger.getLogger(ConcurrencyTest.class);
    //请求总数
    public static int clientTotal = 5000;
    //同时并发执行线程总数
    public static int threadLocal = 200;
    //
    public static int count = 0;

    private static void add(){
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
