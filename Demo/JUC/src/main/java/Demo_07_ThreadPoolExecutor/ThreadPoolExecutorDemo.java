package Demo_07_ThreadPoolExecutor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        /**
         * ThreadPoolExecutor(int corePoolSize,核心线程数
         *                    int maximumPoolSize,最大线程数
         *                    long keepAliveTime,救急线程的生存时间
         *                    TimeUnit unit,救急线程生存时间的单位
         *                    BlockingQueue<Runnable> workQueue,阻塞队列
         *                    ThreadFactory threadFactory,线程工厂，用来创建线程
         *                    RejectedExecutionHandler handler) 拒绝策略
         * 当阻塞队列满了(无界队列是不会满的)的时候会使用救急线程来执行新的任务，救急线程数量=最大线程数-核心线程数
         *
         * 由于这些参数太复杂啦，jdk提供了一个工厂Executors，下面介绍工厂方法
         *
         */
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

        //线程调度
        //以一定时间间隔执行，当任务执行时间大于间隔时间时，会延后执行
        scheduledExecutorService.scheduleAtFixedRate(()->{
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
            System.out.println(dateTimeFormatter.format(LocalDateTime.now()));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },1,1, TimeUnit.SECONDS);
    }
}
