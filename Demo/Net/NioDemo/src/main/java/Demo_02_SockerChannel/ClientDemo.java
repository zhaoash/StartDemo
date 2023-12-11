package Demo_02_SockerChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost",8080));

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        byteBuffer.put("NioClient".getBytes(Charset.defaultCharset()));
        byteBuffer.flip();
        sc.write(byteBuffer);

        sc.close();
    }
}
