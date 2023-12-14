package Demo_01_Channel.FileChannel;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class UseFileChannelReadFileDemo {
    public static void main(String[] args) {
        String url = UseFileChannelReadFileDemo.class.getClassLoader().getResource("test.txt").getFile();

        try (
                FileInputStream fileInputStream = new FileInputStream(url);
                FileChannel fileChannel = fileInputStream.getChannel()
        ) {

            ByteBuffer buffer = ByteBuffer.allocate(100);

            fileChannel.read(buffer);

            buffer.flip();
            while (buffer.hasRemaining()){
                System.out.println((char)buffer.get());
            }

        }catch (Exception ignore){}
    }
}
