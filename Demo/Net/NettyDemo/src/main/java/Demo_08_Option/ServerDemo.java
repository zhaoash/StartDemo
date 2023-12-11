package Demo_08_Option;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ServerDemo {
    public static void main(String[] args) {
        ServerBootstrap serverBootStrap = new ServerBootstrap();
        serverBootStrap.channel(NioServerSocketChannel.class);
        serverBootStrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {

            }
        });
        //给NioServerSocketChannel配置参数
        serverBootStrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS,10);
        //给NioSocketChannel配置参数
        serverBootStrap.childOption(ChannelOption.SO_TIMEOUT,10);
        serverBootStrap.childOption(ChannelOption.ALLOCATOR,new PooledByteBufAllocator());
    }
}
