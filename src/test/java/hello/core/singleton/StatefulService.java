package hello.core.singleton;

public class StatefulService {
    //command shift t 새로운 테스트 만들기

    private int price; //상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; //문제
    }

    public int getPrice() {
        return price;
    }

}
