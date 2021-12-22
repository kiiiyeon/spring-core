package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();
    //static (클래스 레벨에 올라감)으로 자기 자신을 내부에 선언 -> 하나만 생성
    //static 공부하기..
    //자바가 실행될 때 생성됨

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() { //private 생성자

    }
    //private은 자기 자신 호출 가능

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
