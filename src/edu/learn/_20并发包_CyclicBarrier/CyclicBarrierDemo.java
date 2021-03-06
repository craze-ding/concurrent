package edu.learn._20并发包_CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
    目标： CyclicBarrier

    CyclicBarrier作用：
        某个线程任务必须等待其他线程执行完毕以后才能最终触发自己执行。
    例如：公司召集5名员工开会，等5名员工都到了，会议开始。
        我们创建5个员工线程，1个开会任务，几乎同时启动
        使用CyclicBarrier保证5名员工线程全部执行后，再执行开会线程。
    构造器：
         public CyclicBarrier(int parties, Runnable barrierAction)
         // 用于在线程到达屏障5时，优先执行barrierAction，方便处理更复杂的业务场景
    方法：
         public int await()
         // 每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被回收！
    小结：
        可以实现多线程中，某个任务在等待其他线程执行完毕以后触发。
        循环屏障可以实现达到一组屏障就触发一个任务执行！
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        // 1.创建一个任务循环屏障对象。
        /**
         * 参数一：代表多少个线程的执行。
         * 参数二：到达执行屏障就开始触发的线程任务。
         */
        CyclicBarrier cb = new CyclicBarrier(5 , new MeetingRunnable());
        /*new PeopleThread(cb).start();
        new PeopleThread(cb).start();
        new PeopleThread(cb).start();
        new PeopleThread(cb).start();
        new PeopleThread(cb).start();

        new PeopleThread(cb).start();
        new PeopleThread(cb).start();
        new PeopleThread(cb).start();
        new PeopleThread(cb).start();
        new PeopleThread(cb).start();*/
        /*for (int i = 0; i < 5; i++) {
            new PeopleThread(cb,"员工"+i).start();
        }*/
        //扩展
        for (int i = 0; i <10; i++) {
            new PeopleThread(cb,"员工"+i).start();
        }
       /*控制台输出： 员工8进入会议室
                员工7进入会议室
        员工3进入会议室
                员工0进入会议室
        员工6进入会议室
                员工4进入会议室
        员工1进入会议室
                员工5进入会议室
        员工9进入会议室
        人员到齐了开始由员工6主持会议！
        员工2进入会议室
        人员到齐了开始由员工9主持会议！*/
    }
}

// 任务类：开始开会的任务
class MeetingRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("人员到齐了开始由"+Thread.currentThread().getName()+"主持会议！");
    }
}

// 员工类
class PeopleThread extends Thread{
    private CyclicBarrier cb ;
    public PeopleThread(CyclicBarrier cb,String name) {
        super(name);
        this.cb = cb;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"进入会议室");
            cb.await(); // 自己做完了，告诉循环屏障我到达了，当前线程被回收！
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
