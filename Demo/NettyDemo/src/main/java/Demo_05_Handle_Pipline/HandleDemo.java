package Demo_05_Handle_Pipline;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

public class HandleDemo {
    public static void main(String[] args) {
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel ch) throws Exception {
                                //添加handle： head -> I1 -> I2 -> I3 -> O1 -> O2 -> O3 -> I4 -> O4 -> tail
                                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        System.out.println("I1"+msg);
                                        super.channelRead(ctx, msg);
                                    }
                                });
                                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        System.out.println("I2"+msg);
                                        ctx.channel().writeAndFlush("i am i2");
                                        System.out.println("ctx channel = "+ctx.channel());
                                        System.out.println("ch = "+ch);
                                        super.channelRead(ctx, msg);
                                    }
                                });
                                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        System.out.println("I3"+msg);
                                        ctx.channel().writeAndFlush("i am i3");
                                        super.channelRead(ctx, msg);
                                    }
                                });
                                ch.pipeline().addLast(new ChannelOutboundHandlerAdapter(){
                                    @Override
                                    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                        System.out.println("O1"+msg);
                                        super.write(ctx, msg, promise);
                                    }
                                });
                                ch.pipeline().addLast(new ChannelOutboundHandlerAdapter(){
                                    @Override
                                    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                        System.out.println("O2"+msg);
                                        super.write(ctx, msg, promise);
                                    }
                                });
                                ch.pipeline().addLast(new ChannelOutboundHandlerAdapter(){
                                    @Override
                                    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                        System.out.println("O3"+msg);
                                        super.write(ctx, msg, promise);
                                    }
                                });
                                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        System.out.println("I4"+msg);
                                        ctx.writeAndFlush("i am i4");
                                        super.channelRead(ctx, msg);
                                    }
                                });
                                ch.pipeline().addLast(new ChannelOutboundHandlerAdapter(){
                                    @Override
                                    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                        System.out.println("O4"+msg);
                                        super.write(ctx, msg, promise);
                                    }
                                });
                            }
                        })
                .bind(8080);
    }
}
