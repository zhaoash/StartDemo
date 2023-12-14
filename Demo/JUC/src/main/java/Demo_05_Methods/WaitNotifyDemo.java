package Demo_05_Methods;

import static java.lang.Thread.sleep;

//wait和notify都需要配合锁来使用，正确的姿势是：锁住的对象.wait();锁住的对象.notify();
public class WaitNotifyDemo {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                String tName = Thread.currentThread().getName();
                synchronized (WaitNotifyDemo.class) {
                    try {
                        System.out.println(tName + " waiting");
                        WaitNotifyDemo.class.wait();
                        System.out.println(tName + " awake");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            },"thread-"+i).start();
        }

        sleep(2000);
        synchronized (WaitNotifyDemo.class){
            WaitNotifyDemo.class.notify();
        }

    }
}
