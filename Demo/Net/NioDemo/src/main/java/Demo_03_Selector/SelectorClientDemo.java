package Demo_03_Selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class SelectorClientDemo {
    public static void main(String[] args) throws IOException {
        while (true) {
            Scanner sc = new Scanner(System.in);
            String in = sc.nextLine();
            if ("q".equals(in))
                break;
            new Thread(() -> {
                try( SocketChannel socketChannel = SocketChannel.open()) {
                    socketChannel.connect(new InetSocketAddress("localhost", 8080));
                    socketChannel.write(Charset.defaultCharset().encode(in));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).start();

        }
    }
}
