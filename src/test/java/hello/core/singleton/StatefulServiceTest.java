package hello.core.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A사용자 10000원 주문
        statefulService1.order("userA", 10000);
        //ThreadB: B사용자 10000원 주문
        statefulService2.order("userB", 20000);

        //ThreadA: A사용자 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price); //20000
        //statuefulService1,2 이름은 달라도 참조하는 객체는 같기 때문에 B사용자가 들어오면서 가격이 20000으로 바뀜

        org.assertj.core.api.Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}