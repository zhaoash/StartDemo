package Demo_01_CreateThread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTask_CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //futureTask实现了Runnable接口，本质上执行的还是Runnable的run方法，FutureTask实现了Runnable的run方法，在run方法中调用了Callable的call方法
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            System.out.println("thread-01");
            return 1;
        });

        new Thread(futureTask, "thread-01").start();
        //futureTask.get()获取返回结果，是阻塞方法
        futureTask.get();

    }
}
