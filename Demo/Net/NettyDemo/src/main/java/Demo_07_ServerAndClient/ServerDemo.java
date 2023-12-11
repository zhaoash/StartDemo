package Demo_07_ServerAndClient;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.Charset;

public class ServerDemo {
    public static void main(String[] args) {
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new MyChannelInboundHandlerAdapter(nioSocketChannel));
                    }
                }).bind(8080);
    }

    private static class MyChannelInboundHandlerAdapter extends ChannelInboundHandlerAdapter {
        private final NioSocketChannel nioSocketChannel;

        public MyChannelInboundHandlerAdapter(NioSocketChannel nioSocketChannel) {
            this.nioSocketChannel = nioSocketChannel;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof ByteBuf){
                System.out.println(msg);
                System.out.println(((ByteBuf) msg).toString(Charset.defaultCharset()));
                ((ByteBuf) msg).release();
            }
            ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
            byteBuf.writeBytes("server-123".getBytes());
            nioSocketChannel.writeAndFlush(byteBuf);
            //byteBuf.release();
        }
    }
}
