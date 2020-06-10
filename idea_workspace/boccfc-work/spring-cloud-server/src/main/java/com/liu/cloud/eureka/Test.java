package com.liu.cloud.eureka;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Liuweian
 * @version 1.0.0
 * @desc
 * @createTime 2020/6/1 11:05
 */
public class Test {

    public static void main(String[] args) throws Exception {
        // testThird();
        // testFourthly();
        testFifth();
    }

    public static void testFirst() throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10,
                50L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        System.out.println(executor.allowsCoreThreadTimeOut());
    }

    public static void testSecond()  throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void testThird() throws Exception {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        condition.await();
    }

    public static void testFourthly() throws Exception {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, ()-> {
            System.out.println("###########   【结束】百米跑步比赛  ##############");
        });

        System.out.println("###########   【开始】百米跑步比赛  ##############");
        for (int i = 1; i <= 5; i++) {
            new Thread(()-> {
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "  到达终点！！");

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, "运动员：" + i).start();
        }

        System.out.println("============ 不影响主进程往下执行  ===================");
    }

    public static void testFifth() throws Exception {
        Semaphore semaphore = new Semaphore(10);

        for (int i = 1; i <= 20; i++) {
            new Thread(()-> {
                try {
                    semaphore.acquire();

                    // 下面同一时刻只有10个现场能够进入。只有线程通过semaphore.release()释放许可证，其他线程才能继续进入
                    System.out.println(Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));


                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "运动员：" + i).start();
        }

    }

}
