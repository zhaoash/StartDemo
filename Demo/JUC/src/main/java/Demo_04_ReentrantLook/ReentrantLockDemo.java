package Demo_04_ReentrantLook;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        for (int i = 0; i < 5; i++){
            new Thread(()->{
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName()+" waiting");
                    condition.await();
                    System.out.println(Thread.currentThread().getName()+" awake");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    lock.unlock();
                }

            },"thread-"+i).start();
        }

        Thread.sleep(3000);
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
