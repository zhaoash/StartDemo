package Demo_02_SockerChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        //创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //设置ssc.accept()为非阻塞，默认是阻塞的
        ssc.configureBlocking(false);
        //绑定端口
        ssc.bind(new InetSocketAddress(8080));
        //阻塞时：等待连接；非阻塞时：没有链接则返回为null
        SocketChannel sc = ssc.accept();
        //设置sc.read(byteBuffer)为非阻塞
        sc.configureBlocking(false);
        //阻塞：等待读数据；非阻塞：没有读到数据时返回为0
        sc.read(byteBuffer);


        byteBuffer.flip();
        while (byteBuffer.hasRemaining()){
            System.out.print((char)byteBuffer.get());
        }
        byteBuffer.clear();
        ssc.close();
    }
}
