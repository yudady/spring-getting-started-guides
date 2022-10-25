package io.github.yudady.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模拟了100米赛跑，10名选手已经准备就绪，只等裁判一声令下。当所有人都到达终点时，比赛结束。
 *
 * @author liuchao
 */
public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        //10名运动员
        final CountDownLatch count = new CountDownLatch(10);

        //java的线程池
        final ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int index = 1; index <= 10; index++) {
            final int number = index;
            executorService.submit(() -> {
                try {

                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println(number + ": arrived");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //运动员到达终点,count数减一
                    count.countDown();
                }
            });
        }
        System.out.println("Game Started");
        //等待count数变为0,否则会一直处于等待状态,游戏就没法结束了
        count.await();
        System.out.println("Game Over");
        //关掉线程池
        executorService.shutdown();
    }
}