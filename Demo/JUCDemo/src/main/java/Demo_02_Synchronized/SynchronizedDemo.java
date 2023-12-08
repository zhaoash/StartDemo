package Demo_02_Synchronized;

import static java.lang.Thread.sleep;

//synchronized，俗称对象锁，必须配合对象使用，锁住的是对象，用对象锁保证了临界区内代码的原子性
//java中的同步和互斥都可以通过synchronized来实现
/*
语法:
代码块
synchronized(对象){
    代码
}
方法上：
成员方法上：
public synchronized void test(){
    代码块;
}
等价于
public void test(){
    synchronized(this){
        代码块;
    }
}

静态方法上：
class Test{
    public synchronized static void test(){
        代码块;
    }
}
等价于
class Test{
    public static void test(){
        synchronized(Test.class){
            代码块;
        }
    }
}
 */
public class SynchronizedDemo {

    public static void main(String[] args) throws InterruptedException {
/*        new Thread(()->{
            synchronized (SynchronizedDemo.class){
                while (true){
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("thread-01");
                }
            }
        }).start();

        sleep(3000);
        new Thread(()->{
            synchronized (String.class){
                while (true){
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("thread-02");
                }
            }
        }).start();*/
        SynchronizedDemo synchronizedDemo1 = new SynchronizedDemo();
        SynchronizedDemo synchronizedDemo2 = new SynchronizedDemo();
        new Thread(()->synchronizedDemo1.testSynchronizedThis("01")).start();
        new Thread(()->synchronizedDemo2.testSynchronizedThis("02")).start();

    }

    public void testSynchronizedThis(String name){
        synchronized (this){
            while (true) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(name +" : "+this);
            }
        }
    }
}
