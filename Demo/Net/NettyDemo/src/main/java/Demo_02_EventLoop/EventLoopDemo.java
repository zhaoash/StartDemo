package Demo_02_EventLoop;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

import java.util.concurrent.TimeUnit;

public class EventLoopDemo {
    public static void main(String[] args) {
        // NioEventLoopGroup可以处理io事件、普通任务、定时任务
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        //处理普通任务
        eventLoopGroup.next().submit(
                ()->System.out.println("普通任务")
        );

        //处理定时任务
        eventLoopGroup.next().scheduleAtFixedRate(
                ()-> System.out.println("定时任务"),
                0,
                5,
                TimeUnit.SECONDS
        );

        //处理IO事件
        new ServerBootstrap()
                // 细分：group(new NioEventLoopGroup(),new NioEventLoopGroup()); parentGroup只负责accept事件， childGroup只负责读写事件
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel ch) {
                                ch.pipeline().addLast(new StringDecoder());
                                //指定一个环节使用另一个EventLoop
                                ch.pipeline().addLast(new DefaultEventLoop(),new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                        System.out.println(msg);
                                    }
                                });
                            }
                        })
                .bind(8080);
    }
}
