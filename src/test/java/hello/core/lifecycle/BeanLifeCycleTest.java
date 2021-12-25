package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        //ApplicationContext -> ConfigurableApplicationContext -> AnnotationConfigApplicationContext
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); //close를 잘 사용하지 않아서 ApplicationContext에서는 제공해주지 않음 하위 인터페이스로 내려가기 ~

    }

    static class LifeCycleConfig {
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
