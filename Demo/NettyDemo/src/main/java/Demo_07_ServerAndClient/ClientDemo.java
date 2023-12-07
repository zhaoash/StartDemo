package Demo_07_ServerAndClient;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;


import java.nio.charset.Charset;
import java.util.Scanner;

public class ClientDemo {
    public static void main(String[] args) {
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        new Bootstrap()
                .group(nioEventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                if (msg instanceof ByteBuf){
                                    System.out.println(((ByteBuf) msg).toString(Charset.defaultCharset()));
                                    ((ByteBuf) msg).release();
                                }
                            }
                        });
                        nioSocketChannel.pipeline().addLast(new ChannelOutboundHandlerAdapter(){
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                System.out.println("out handle "+msg);
                                super.write(ctx,msg,promise);
                            }
                        });
                    }
                })
                .connect("localhost", 8080)
                .addListener((ChannelFutureListener) channelFuture -> new Thread(() -> {
                    Scanner sc = new Scanner(System.in);
                    Channel channel = channelFuture.channel();
                    while (true) {
                        String line = sc.nextLine();
                        if ("q".equals(line)) {
                            channel.close();
                            ChannelFuture closeFuture = channel.closeFuture();
                            closeFuture.addListener(future -> {
                                nioEventLoopGroup.shutdownGracefully();
                                System.out.println("close");
                            });
                            break;
                        }
                        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
                        byteBuf.writeBytes(line.getBytes());
                        channel.writeAndFlush(byteBuf);
/*                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(byteBuf);
                        byteBuf.release();*/
                    }
                }).start());
    }
}
