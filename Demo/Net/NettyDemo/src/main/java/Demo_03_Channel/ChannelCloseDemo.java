package Demo_03_Channel;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class ChannelCloseDemo {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap()
                .group(nioEventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) {
                        nioSocketChannel.pipeline().addLast(new StringEncoder());
                        nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println(msg);
                            }
                        });
                    }
                });
        ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("localhost", 8080));

        channelFuture.sync();

        new Thread(()->{
            Channel channel = channelFuture.channel();
            Scanner sc = new Scanner(System.in);
            while (true){
                String line = sc.nextLine();
                if("q".equals(line)){
                    // channel.close();是一个异步方法，将close交给NIO线程去做了，如果想要在close后做一些善后工作，需要使用closeFuture()，其处理机制与ChannelFuture一致
                    channel.close();
                    ChannelFuture closeFuture = channel.closeFuture();
                    try {
                        closeFuture.sync();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    nioEventLoopGroup.shutdownGracefully();
                    break;
                }
                channel.writeAndFlush(line);
            }
        }).start();
    }
}
