package edu.learn.oneself.drawdoney;

/**
 * @program: Concurrent
 * @description:
 * @author: leet-Craze
 * @create: 2022-01-26 16:24
 **/
public class MyThread implements Runnable{
    private Acconut acconut;
    private int money=1000;
    public MyThread(Acconut acconut) {
        this.acconut=acconut;
    }

    @Override
    public void run() {
        acconut.TakeMoney(money);
    }
}
