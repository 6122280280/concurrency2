package com.dy.concurrency.example.count;

import com.dy.concurrency.annotation.NotThreadSafe;
import org.apache.log4j.Logger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


@NotThreadSafe
public class CountExample5 {
    private static Logger log = Logger.getLogger(CountExample5.class);
    //请求总数
    public static int clientTotal = 5000;
    //同时并发执行线程总数
    public static int threadLocal = 200;
    //
    public static int count = 0;
    public  static boolean flag = false;
    private static void test(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{

            for(int j=0;j<100;j++){

                count++;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            flag = true;
        });
        for(int i=0;i<100;i++){
            executorService.execute(()->{
                if(flag){
                    log.info("volatile{} flag = true " + count);
                }
            });
        }
    }
    private static void flagTest(){

    }
    private static void add(){
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        test();
    }
}
