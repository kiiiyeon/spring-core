package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    //생성자
    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    //서비스 도중에 부를 수 있음
    public void call(String message) {
        System.out.println("call: " + url + " message: " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }


    @PostConstruct
    public void init() {
        //의존관계 주입 끝난 후 호출
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        //빈 생명주기 끝날 때 호출
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
