package com.young.spider.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yangyong3 on 2017/7/11.
 */
public class ThreadABC {

    private boolean threadA = true;

    private boolean threadB = false;

    private boolean threadC = false;

    private int index = 0;

    private ReentrantLock lock = new ReentrantLock();

    public class ThreadA implements Runnable{

        @Override
        public void run() {
            while(index<10) {
                lock.lock();
                if (threadA) {
                    System.out.println("A");
                    threadA = false;
                    threadB = true;
                    threadC = false;
                }
                lock.unlock();
            }
        }
    }
    public class ThreadB implements Runnable{

        @Override
        public void run() {
            while(index<10) {
                lock.lock();
                if (threadB) {
                    System.out.println("B");
                    threadA = false;
                    threadB = false;
                    threadC = true;
                }
                lock.unlock();
            }
        }
    }
    public class ThreadC implements Runnable{

        @Override
        public void run() {
            while(index<=10) {
                lock.lock();
                if (threadC) {
                    System.out.println("C");
                    threadA = true;
                    threadB = false;
                    threadC = false;
                    index++;
                }
                lock.unlock();
            }
        }
    }
    public static void main(String[] args){
        ThreadABC abc = new ThreadABC();
        Thread threadA = new Thread(abc.new ThreadA());
        Thread threadB = new Thread(abc.new ThreadB());
        Thread threadC = new Thread(abc.new ThreadC());
        threadB.start();
        threadC.start();
        threadA.start();

    }
}
