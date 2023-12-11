package Demo_03_Selector;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class SelectorServerDemo {
    public static ByteBuffer byteBuffer = ByteBuffer.allocate(10);

    public static void main(String[] args) throws Exception {
        //创建Selector,可以管理多个Channel
        Selector selector = Selector.open();
        SelectionKey selectionKey = ServerSocketChannel.open()
                .bind(new InetSocketAddress(8080))
                //注册selector的channel需要设置成非阻塞
                .configureBlocking(false)
                //建立Channel与Selector之间的联系，并设置监听事件为SelectionKey.OP_ACCEPT，设置为0表示不监听任何事件，后续可通过interestOps(SelectionKey.OP_ACCEPT)来设置
                .register(selector, SelectionKey.OP_ACCEPT, null);

        while (true) {
            System.out.println("-----------------");
            //selector.select()没有事件则阻塞，有事件则恢复执行，事件必须要被处理，也就是Read的时候后面必须有对应的channel.read来处理，否则将视为有事件，该处无法被阻塞
            //断开链接会触发read事件返回值为-1，异常断开报异常
            selector.select();
            //selectionKeys内部包含了所有发生的关心事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    System.out.println("accept: "+ ssc);
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);


                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 3000000; i++) {
                        sb.append("a");
                    }
                    ByteBuffer buffer = Charset.defaultCharset().encode(sb.toString());

                    /* 这样会卡在这里，直至发送完成，改进->可写事件
                    while (buffer.hasRemaining()) {
                        //写入时并不能一次全部写入，返回值为写入的字节数
                        int write = sc.write(buffer);
                    }*/

                    sc.write(buffer);
                    //通过看buffer中是否还有剩余字节来判断是否发送完毕
                    if (buffer.hasRemaining()){
                        //没发完毕，则需要关注可写事件，当缓冲区可写时会触发可写事件，需要继续写入的内容放到附件中，以下次使用
                        sc.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, buffer);
                    }else {
                        sc.register(selector, SelectionKey.OP_READ, null);
                    }
                } else if (key.isReadable()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    int read = sc.read(byteBuffer);
                    if (read == -1)
                        key.cancel();
                    byteBuffer.flip();
                    System.out.println(Charset.defaultCharset().decode(byteBuffer));
                    byteBuffer.clear();
                    System.out.println("read pass::::"+key);
                } else if(key.isWritable()){
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    SocketChannel sc = (SocketChannel) key.channel();
                    sc.write(buffer);
                    //判断，如果写完了，则做一些清理
                    if (buffer.hasRemaining()){
                        key.attach(null);
                        key.interestOps(key.interestOps() - SelectionKey.OP_WRITE);
                    }
                }
                iterator.remove();
            }
        }
    }
}
