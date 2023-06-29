package org.example;

import java.util.ArrayList;
import java.util.List;

public class ClientManager {
    private static ClientManager INSTANCE;
    private List<ClientHandler> clientList = new ArrayList<>();

    private ClientManager() {}

    public static ClientManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClientManager();
        }
        return INSTANCE;
    }

    public void add(ClientHandler cHandle) {
        clientList.add(cHandle);
    }
    public void remove(ClientHandler cHandle) {
        clientList.remove(cHandle);
    }
    public int getNumClient() {
        return clientList.size();
    }
}