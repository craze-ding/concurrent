package edu.learn._08并发包_ConcurrentHashMap;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: Concurrent
 * @description: 改进使用join()方法,保证线程执行完毕！
 * @author: leet-Craze
 * @create: 2022-01-24 23:00
 **/
public class ConcurrentHashMapDemo_onself {
    // public static Map<String,String> map = new HashMap<>();
    // public static Map<String,String> map = new Hashtable<>();
    public static Map<String,String> map = new ConcurrentHashMap<>();
    public static void main(String[] args) {
        MyRunable myRunable = new MyRunable();
        Thread t1 = new Thread(myRunable, "线程1");
        Thread t2 = new Thread(myRunable, "线程2");

        t1.start();
        t2.start();

        try {
            t1.join();//让线程1执行完毕主线程不能竞争cpu！
            t2.join();//让线程2执行完毕,主线程不能竞争cpu！
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印集合大小
        System.out.println("Map大小：" + map.size());
    }
}

class MyRunable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 500000; i++) {
            ConcurrentHashMapDemo_onself.map.put(Thread.currentThread().getName() + "键：" + i, "值" + i);
        }
    }
}
