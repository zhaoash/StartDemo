package Demo_06_ByteBuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class ByteBufDemo {
    public static void main(String[] args) {
        //可以指定容量，默认是256
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        System.out.println(byteBuf);

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 300; i++){
            stringBuffer.append("a");
        }

        //ByteBuf是可以扩容的
        byteBuf.writeBytes(stringBuffer.toString().getBytes());
        System.out.println(byteBuf);
    }
}
