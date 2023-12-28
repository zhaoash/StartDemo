package Demo_06_ByteBuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class ByteBufPoolDemo {
    public static void main(String[] args) {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();

        //池化+直接内存：PooledUnsafeDirectByteBuf(ridx: 0, widx: 0, cap: 256)
        System.out.println(byteBuf);
    }
}
