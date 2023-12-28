package Demo_05_Handle_Pipline;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.embedded.EmbeddedChannel;

public class TestHandleDemo {
    public static void main(String[] args) {
        ChannelInboundHandlerAdapter cih = new ChannelInboundHandlerAdapter(){
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                System.out.println("O1"+msg);
                super.channelRead(ctx, msg);
            }
        };
        ChannelOutboundHandlerAdapter coh = new ChannelOutboundHandlerAdapter(){
            @Override
            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                System.out.println("O1"+msg);
                super.write(ctx, msg, promise);
            }
        };
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(cih,coh);
        //模拟入站
        embeddedChannel.writeInbound("123");
        //模拟出站
        embeddedChannel.writeOneInbound("456");
    }


}
