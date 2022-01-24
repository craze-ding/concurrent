package edu.learn._13死锁;

/**
 * @program: Concurrent
 * @description: 自己可能实现死锁方式
 * @author: leet-Craze
 * @create: 2022-01-24 22:11
 **/
public class DeadLock_oneself {
    public static void main(String[] args) {
        Object resoues1 =new Object();
        Object resoues2 =new Object();
        new Thread(new MyRunableA(resoues1,resoues2)).start();
        new Thread(new MyRunableB(resoues1,resoues2)).start();
    }
}
class MyRunableA implements Runnable{
    private Object resoues1;
    private Object resoues2;
    public MyRunableA(Object resoues1,Object resoues2) {
        this.resoues1=resoues1;
        this.resoues2=resoues2;
    }

    @Override
    public void run() {
        synchronized (resoues1){
            System.out.println("占有资源1，请求资源2！");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (resoues2){
                System.out.println("成功请求到资源2！");
            }
        }

    }
}
class MyRunableB implements Runnable{
    private Object resoues1;
    private Object resoues2;
    public MyRunableB(Object resoues1,Object resoues2) {
        this.resoues1=resoues1;
        this.resoues2=resoues2;
    }

    @Override
    public void run() {
        synchronized (resoues2){
            System.out.println("占有资源2，请求资源1！");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (resoues1){
                System.out.println("成功请求到资源1！");
            }
        }

    }
}
