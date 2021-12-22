package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatelessServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(StatelessServiceTest.TestConfig.class);
        StatelessService statelessService1 = ac.getBean(StatelessService.class);
        StatelessService statelessService2 = ac.getBean(StatelessService.class);

        //ThreadA: A사용자 10000원 주문
        int priceA = statelessService1.order("userA", 10000);
        //ThreadB: B사용자 10000원 주문
        int priceB = statelessService2.order("userB", 20000);

        System.out.println("priceA = " + priceA); //10000
        System.out.println("priceB = " + priceB); //20000

        org.assertj.core.api.Assertions.assertThat(priceA).isEqualTo(10000);
        org.assertj.core.api.Assertions.assertThat(priceB).isEqualTo(20000);

        //참조하는 객체는 같지만 다른 지역변수(priceA, priceB)를 사용하여 Stateless로 구현(문제 해결)

    }

    static class TestConfig {
        @Bean
        public StatelessService statefulService() {
            return new StatelessService();
        }
    }
}
