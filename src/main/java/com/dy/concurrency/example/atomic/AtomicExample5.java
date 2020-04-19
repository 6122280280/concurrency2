package com.dy.concurrency.example.atomic;

import com.dy.concurrency.annotation.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicExample5 {
    private static Logger log = Logger.getLogger(AtomicExample5.class);
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");
    public volatile int count = 100;

    public int getCount() {
        return count;
    }
    public static void main(String[] args) {
        AtomicExample5 example5 = new AtomicExample5();
        if(updater.compareAndSet(example5, 100, 120)){
            log.info("example5 success1{}" + example5.getCount());
        }
        if(updater.compareAndSet(example5, 100, 120)){
            log.info("example5 success2{}" + example5.getCount());
        }else {
            log.info("example5 fail2{}" + example5.getCount());
        }
    }
}
