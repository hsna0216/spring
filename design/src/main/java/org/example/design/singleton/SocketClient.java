package org.example.design.singleton;

public class SocketClient {
    // Singleton은 자기 자신을 객체로 가지고 있어야 함.
    private static SocketClient socketClient = null;

    // Singleton은 기본 생성자 private --> 기본 생성자로 객체를 생성할 수 없도록.
    private SocketClient() {

    }

    // Singleton은 getInstance Method를 제공해야함.
    // static method 이기 때문에 어디에서나 객체 생성 없이 호출이 가능하다.
    public static SocketClient getInstance() {
        if(socketClient == null) {
            socketClient = new SocketClient();
        }

        return socketClient;
    }

    public void connect() {
        System.out.println("connect");
    }
}
