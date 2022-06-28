package org.example.design;

import org.example.design.adpater.*;
import org.example.design.singleton.Aclazz;
import org.example.design.singleton.Bclazz;
import org.example.design.singleton.SocketClient;

public class Main {
    public static void main(String[] args) {

        //*********************************************************
        // Singletone Pattern
        //*********************************************************
        Aclazz aclazz = new Aclazz();
        Bclazz bclazz = new Bclazz();

        SocketClient aClient = aclazz.getSocketClient();
        SocketClient bClient = bclazz.getSocketClient();

        // 객체가 동일한지 비교
        System.out.println("두 개의 객체가 동일한가?");
        System.out.println(aClient.equals(bClient));        // true

        //*********************************************************
        // Adapter Pattern
        //*********************************************************
        HairDryer hairDryer = new HairDryer();
        connect(hairDryer);

        Cleaner cleaner = new Cleaner();
        AirConditioner airConditioner = new AirConditioner();

        Electronic110V _cleaner = new SocketAdapter(cleaner);
        Electronic110V _airConditioner = new SocketAdapter(airConditioner);

        connect(_cleaner);
        connect(_airConditioner);
    }

    public static void connect(Electronic110V electronic110V){
        electronic110V.powerOn();
    }
}