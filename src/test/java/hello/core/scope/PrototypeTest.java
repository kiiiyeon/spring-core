package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {
    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class); //밑에 있는 클래스가 컴포넌트 스캔으로 자동으로 등록이 됨
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        /*
        find prototypeBean1
        PrototypeBean.init 빈이 조회될 때 생성
        find prototypeBean2
        PrototypeBean.init
        prototypeBean1 = hello.core.scope.PrototypeTest$PrototypeBean@1c93f6e1
        prototypeBean2 = hello.core.scope.PrototypeTest$PrototypeBean@1800a575
        18:38:42.659 [main] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext - Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@1de5f259, started on Tue Dec 28 18:38:42 KST 2021
        컨테이너를 클로즈하지만 빈이 destroy된다는 말은 나오지 않음! -> 생성 후 관리X
         */

        ac.close();
    }

    @Scope("prototype") //@Component 애노테이션이 없어도 자동으로 스캔됨
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
