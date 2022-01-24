package edu.learn._19并发包_CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @program: Concurrent
 * @description: 交换线程A, B顺序
 * @author: leet-Craze
 * @create: 2022-01-24 23:41
 **/
public class CountDownLatchDemo_plus {
    public static void main(String[] args) {
        //创建1个计数器：用来控制 A , B线程的执行流程的。
        CountDownLatch down = new CountDownLatch(1);
        //交换
        new ThreadB(down).start();
        new ThreadA(down).start();
      /*  结果：B
                A
        C
*/
    }

}



class Thread_A extends Thread {
    private CountDownLatch down;

    public Thread_A(CountDownLatch down) {
        this.down = down;
    }

    @Override
    public void run() {
        System.out.println("A");
        try {
            down.await(); // A线程你进入等待，让B线程执行自己！
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("C");
    }
}
class Thread_B extends Thread {
    private CountDownLatch down;

    public Thread_B(CountDownLatch down) {
        this.down = down;
    }

    @Override
    public void run() {
        System.out.println("B");
        down.countDown(); // 这里相当于是-1，代表自己执行完毕了。A线程被唤醒！！
    }
}
