package Demo_01_HelloWorld;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

public class ServerDemo {
    public static void main(String[] args) {
        //启动器，负责组装netty组件，启动服务器
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        // parentGroup只负责accept事件， childGroup只负责读写事件
        serverBootstrap.group(new NioEventLoopGroup(),new DefaultEventLoop());


        //添加配置项，设置NioServerSocketChannel接收缓冲区为10个字节大小（滑动窗口）
        serverBootstrap.option(ChannelOption.SO_RCVBUF,10);

        //选择一个ServerSocketChannel的实现
        serverBootstrap.channel(NioServerSocketChannel.class);

        //boss负责链接的，child负责读写的，决定child能执行哪些操作
        serverBootstrap.childHandler(
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new StringDecoder());
                                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        System.out.println(msg);
                                    }
                                });
                            }
                        });

        serverBootstrap.bind(8080);
    }
}
