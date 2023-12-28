package Demo_03_Channel;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

public class ChannelConnectDemo {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) {
                        nioSocketChannel.pipeline().addLast(new StringEncoder());
                    }
                });

        //connect是一个异步非阻塞的，main发起调用，真正执行链接的是NioEventLoopGroup中的一个NIO线程
        //带有Future,Promise的类型都是和异步方法配套使用，用来处理结果
        ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("localhost", 8080));

        //使用sync方法同步处理结果
        channelFuture.sync();
        Channel channel = channelFuture.channel();
        channel.writeAndFlush("hello world");

        //使用addListener方法异步处理结果
        channelFuture.addListener(new ChannelFutureListener() {
            @Override//在NIO线程完成建立链接后，会调用该方法
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                Channel channel = channelFuture.channel();
                channel.writeAndFlush("hello world");
            }
        });
    }
}
