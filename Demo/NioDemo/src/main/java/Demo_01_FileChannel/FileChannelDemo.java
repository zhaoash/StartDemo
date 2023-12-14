package Demo_01_FileChannel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {
    public static void main(String[] args) throws IOException {
        try (FileChannel fileChannel = new FileInputStream("C:\\Users\\01168\\IdeaProjects\\Demo\\NioDemo\\src\\main\\resources\\1.txt").getChannel()) {
            IntBuffer intBuffer = IntBuffer.allocate(1000);
            fileChannel..read(intBuffer);
            intBuffer.flip();
            while (intBuffer.hasRemaining()){

                System.out.println(intBuffer);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
