package org.example.design;

import org.example.design.adpater.*;

import org.example.design.aop.AopBrowser;
import org.example.design.decorator.*;
import org.example.design.facade.Ftp;
import org.example.design.facade.Reader;
import org.example.design.facade.SftpClient;
import org.example.design.facade.Writer;
import org.example.design.observer.Button;
import org.example.design.observer.IButtonListener;
import org.example.design.proxy.Browser;
import org.example.design.proxy.BrowserProxy;
import org.example.design.proxy.IBrowser;
import org.example.design.singleton.Aclazz;
import org.example.design.singleton.Bclazz;
import org.example.design.singleton.SocketClient;

import java.util.concurrent.atomic.AtomicLong;

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

        //*********************************************************
        // Proxy Pattern
        //*********************************************************
        /*
        // Proxy 사용 전
        Browser browser = new Browser("www.naver.com");
        browser.show();
        browser.show();
        browser.show();
         */

        // Proxy 사용
        IBrowser browser = new BrowserProxy("www.naver.com");
        browser.show();
        browser.show();
        browser.show();
        browser.show();
        browser.show();

        //*********************************************************
        // AOP ( Proxy 패턴으로 구현)
        //*********************************************************
        AtomicLong start = new AtomicLong();
        AtomicLong end = new AtomicLong();
        IBrowser aopBrowser = new AopBrowser("www.naver.com",
                ()->{
                    System.out.println("before");
                    start.set(System.currentTimeMillis());
                },
                ()->{
                    long now = System.currentTimeMillis();
                    end.set(now - start.get());
                });
        aopBrowser.show();
        System.out.println("loading time : " +end.get());

        aopBrowser.show();
        System.out.println("loading time : " +end.get());

        //*********************************************************
        // Decorate Pattern
        //*********************************************************
        ICar audi = new Audi(1000);
        audi.showPrice();

        // a3
        ICar audi3 = new A3(audi, "A3");
        audi3.showPrice();
        // a4
        ICar audi4 = new A4(audi, "A4");
        audi4.showPrice();
        // a5
        ICar audi5 = new A5(audi, "A5");
        audi5.showPrice();

        //*********************************************************
        // Observer Pattern
        //*********************************************************
        /*
        Button button = new Button("버튼");
        button.addListener(new IButtonListener() {
            @Override
            public void clickEvent(String event) {
                System.out.println(event);
            }
        });

        button.click("메세지 전달 : click1");
        button.click("메세지 전달 : click2");
        button.click("메세지 전달 : click3");
        button.click("메세지 전달 : click4");

         */

        //*********************************************************
        // Facade Pattern
        //*********************************************************
        // Facade Pattern 사용 X
        Ftp ftpClient = new Ftp("www.foo.co.kr", 22, "/home/etc");

        Writer writer = new Writer("text.tmp");
        writer.fileConnect();
        writer.write();

        Reader reader = new Reader("text.tmp");
        reader.fileConnect();
        reader.fileRead();

        reader.fileDisconnect();
        writer.fileDisconnect();
        ftpClient.disConnect();


        SftpClient sftpClient = new SftpClient("www.foo.co.kr", 22, "/home/etc", "text.tmp");
        sftpClient.connect();
        sftpClient.write();
        sftpClient.read();
        sftpClient.disConnect();
    }

    public static void connect(Electronic110V electronic110V){
        electronic110V.powerOn();
    }
}