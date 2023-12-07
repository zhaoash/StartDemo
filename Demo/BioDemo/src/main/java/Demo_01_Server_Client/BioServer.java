package Demo_01_Server_Client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {
    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8080));

        Socket socket = serverSocket.accept();

        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String msg;
        while ((msg = bufferedReader.readLine()) != null){
            System.out.println(msg);
        }

        bufferedReader.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
    }

}
