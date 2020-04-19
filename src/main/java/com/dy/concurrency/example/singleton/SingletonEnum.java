package com.dy.concurrency.example.singleton;

import com.dy.concurrency.annotation.Recommend;
import com.dy.concurrency.annotation.ThreadSafe;

/**
 *枚举模式：最安全
 */
@ThreadSafe
@Recommend
public class SingletonEnum {
    private SingletonEnum(){

    }
    public static SingletonEnum getInstance(){
        return Singleton.INSTANCE.getInstance();
    }
    private enum Singleton{
        INSTANCE;
        private SingletonEnum singleton;
        //JVM 保证这个方法绝对只执行一次
        Singleton(){
            singleton = new SingletonEnum();
        }
        public SingletonEnum getInstance(){
            return singleton;
        }
    }


}
class test{

    public static void main(String[] args) {
        SingletonEnum singleton = SingletonEnum.getInstance();
        SingletonEnum singleton2 = SingletonEnum.getInstance();
        System.out.println(singleton==singleton2);
    }
}