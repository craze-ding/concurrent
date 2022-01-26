package edu.learn.oneself.drawdoney;

/**
 * @program: Concurrent
 * @description: 复现取钱
 * @author: leet-Craze
 * @create: 2022-01-26 16:18
 **/
public class DrawMoney {
    public static void main(String[] args) {
        Acconut acconut = new Acconut("ICBC-112", 1000);
        new Thread(new MyThread(acconut), "小明").start();
        new Thread(new MyThread(acconut), "小红").start();
    }
}
