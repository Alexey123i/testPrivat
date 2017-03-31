package client;

import java.io.IOException;
import java.net.InetAddress;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        InetAddress addr = InetAddress.getByName(null);
                new ClientThread(addr);
    }
}