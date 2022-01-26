package edu.learn.oneself.drawdoney;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: Concurrent
 * @description:
 * @author: leet-Craze
 * @create: 2022-01-26 16:19
 **/
public class Acconut {
    private  String account;
    private int money;
    //方式三：加显式锁lock
    private final Lock lock=new ReentrantLock();

    public Acconut() {
    }

    public Acconut(String account, int money) {
        this.account=account;
        this.money=money;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    //方式二:实例方法加synchronized
    public /*synchronized*/ void TakeMoney(int money) {
        String name = Thread.currentThread().getName();
        System.out.println(name+"来取钱！");
        //方式一：同步代码块
       /* synchronized (this){
            if(this.money>=1000){
                System.out.println(name +"来取钱，余额足够，吐出"+money);
                this.money-=money;
                System.out.println(name +"取了"+money+",余额是"+this.money);
            }else{
                System.out.println(name+"来了，账户没有钱了！");
            }
        }*/
       lock.lock();//拿锁
        try {
            if(this.money>=1000){
                System.out.println(name +"来取钱，余额足够，吐出"+money);
                this.money-=money;
                System.out.println(name +"取了"+money+",余额是"+this.money);
            }else{
                System.out.println(name+"来了，账户没有钱了！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();//释放锁
        }


    }
}

