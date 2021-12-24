package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        @Autowired(required = false)
        //(required = true일 경우 디폴트이기 때문에 true가 인텔리제이에서 회색 글자로 보임)
        public void setNoBean1(Member noBean1) { //스프링 컨테이너에서 관리해주는 것이 없음 member는 빈이 아님 -> 주입할 수 있는 대상이 아님
            System.out.println("noBean1 = " + noBean1);
        }


        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }

}
