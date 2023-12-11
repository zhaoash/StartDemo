package Demo_04_Future_Promise;

import java.util.concurrent.*;

public class JdkFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> f = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 50;
            }
        });
        Integer i = f.get();
        System.out.println(i);
    }


}
