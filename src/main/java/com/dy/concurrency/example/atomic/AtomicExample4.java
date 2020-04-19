package com.dy.concurrency.example.atomic;

import com.dy.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
@ThreadSafe
public class AtomicExample4 {
   private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0, 1);
        count.compareAndSet(2, 3);
        count.compareAndSet(1, 4);
        count.compareAndSet(3, 5);
        count.compareAndSet(4, 6);
        Logger.getLogger(AtomicExample4.class).info(count);
    }
}
