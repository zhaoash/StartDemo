package Demo_04_Future_Promise;

import io.netty.channel.DefaultEventLoop;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;

public class NettyPromiseDemo {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventLoopGroup = new DefaultEventLoop();
        EventLoop eventLoop = eventLoopGroup.next();
        //eventLoop是Promise的执行线程
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventLoop);


        new Thread(()->{
           try {
               Thread.sleep(5000);
               promise.setSuccess(500);
           } catch (InterruptedException e) {
               promise.setFailure(e);
               throw new RuntimeException(e);
           }

        }).start();
        while (true){
            promise.addListener(future->{
                System.out.println(future);
                System.out.println(future);
            });
            //同步等代结果
            promise.sync();
            //不管有没有结果，我就硬拿，没结果就拿null
            promise.getNow();
            System.out.println("--"+promise.getNow());
            Thread.sleep(1000);
        }


    }
}
