package Demo_01_Server_Client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;


public class BioClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost",8080));

        OutputStream outputStream = socket.getOutputStream();

        PrintStream printStream = new PrintStream(outputStream);
        printStream.println("hello server");
        printStream.flush();


        printStream.close();
        outputStream.close();
        socket.close();
    }
}
