package org.example.design;

import org.example.design.singleton.Aclazz;
import org.example.design.singleton.Bclazz;
import org.example.design.singleton.SocketClient;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello world!");
        Aclazz aclazz = new Aclazz();
        Bclazz bclazz = new Bclazz();

        SocketClient aClient = aclazz.getSocketClient();
        SocketClient bClient = bclazz.getSocketClient();

        // 객체가 동일한지 비교
        System.out.println("두 개의 객체가 동일한가?");
        System.out.println(aClient.equals(bClient));        // true
    }
}