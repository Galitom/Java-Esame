package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Hello world!
 *
 */
public class Server
{
    static final int portNumber = 1234;
    public static void main( String[] args )
    {
        System.out.println("Server started!");

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ClientHandler clientHandler = new ClientHandler(clientSocket);
            ClientManager.getInstance().add(clientHandler);

            Thread t1 = new Thread(clientHandler);
            t1.start();
        }
    }
}
