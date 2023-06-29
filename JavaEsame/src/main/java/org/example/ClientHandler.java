package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientHandler implements Runnable {
    Socket cSocket;
    private PrintWriter out = null;

    public ClientHandler(Socket cSocket) {
        this.cSocket = cSocket;

        InetAddress address = this.cSocket.getInetAddress();
        int port = this.cSocket.getPort();
        System.out.println(
                "Client connected! \n" +
                        "Address: " + address + "; \n" +
                        "Port: " + port
        );
    }

    public void handle() {
        try {
            out = new PrintWriter(cSocket.getOutputStream(), true);
            this.write(CmdHandler.getInstance().getCmd());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(cSocket.getInputStream()));
            readLoop(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Boolean readLoop(BufferedReader in) {
        String s = "";
        try {
            // READS REQUEST AND RESPONDS
            while ((s = in.readLine()) != null) {
                System.out.println(s);

                out.println(CmdHandler.getInstance().getAction(s));
            }
            return true;

        } catch (IOException e) {
            InetAddress address = cSocket.getInetAddress();
            ClientManager.getInstance().remove(this);
            System.out.println("Disconnected " + address + ". Now we have " + ClientManager.getInstance().getNumClient() + " clients.");
        }

        return false;
    }

    public void write(String s) {
        out.println(s);
        out.flush();
    }

    @Override
    public void run() {
        this.handle();
    }
}