package Demo_08_Option;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;

public class ClientDemo {
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS,10);
    }
}
