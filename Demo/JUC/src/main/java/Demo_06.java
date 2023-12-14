import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Thread.sleep;

//内存可见性问题
public class Demo_06 {
    //volatile修饰成员变量，避免线程从自己的工作缓存中查找变量的值，synchronized也可以保证可见性，但是效率太低了
    volatile static boolean isRun = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            System.out.println("run");
            while (isRun){
               //由于多次去取isRun，编译器会优化，将isRun优化至高速缓存中，main线程修改isRun的值后同步到了静态变量表中，而线程是从高速缓存中取的，永远是旧值，解决：加volatile
            }
            System.out.println("out");
        }).start();

//        new Thread(()->{
//            System.out.println("run"+isRun);
//            System.out.println("run");
//            try {
//                sleep(3000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println("out");
//            System.out.println("run"+isRun);
//        }).start();
        sleep(2000);
        isRun = false;
    }
}
