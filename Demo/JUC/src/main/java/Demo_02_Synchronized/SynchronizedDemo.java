package Demo_02_Synchronized;

import static java.lang.Thread.sleep;
/*
synchronized，俗称对象锁，必须配合对象使用，锁住的是对象，用对象锁保证了临界区内代码的原子性
java中的同步和互斥都可以通过synchronized来实现

语法:
    代码块上：
        synchronized(对象){
            代码块;
        }

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

    public static void main(String[] args) {

        SynchronizedDemo obj1 = new SynchronizedDemo();
        SynchronizedDemo obj2 = new SynchronizedDemo();

        //说明锁的是对象obj01_02被obj01_01阻塞了，obj02不受影响
        new Thread(()->obj1.testSynchronizedThis("obj01_01")).start();
        new Thread(()->obj1.testSynchronizedThis("obj01_02")).start();
        new Thread(()->obj2.testSynchronizedThis("obj02")).start();

    }


    public void testSynchronizedThis(String name){
        synchronized (this){
            while (true) {
                try {
                    //由于sleep涉及到线程上下文的切换，又在循环里面，极其消耗性能，所以不建议这么做，建议使用scheduledExecutorService
                    sleep(1000);
                    System.out.println(name +" : "+this);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}
