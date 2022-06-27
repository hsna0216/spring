package org.example.design.singleton;

public class Bclazz {
    private SocketClient socketClient;

    public Bclazz() {
        this.socketClient = SocketClient.getInstance();
    }

    public SocketClient getSocketClient() {
        return this.socketClient;
    }
}
