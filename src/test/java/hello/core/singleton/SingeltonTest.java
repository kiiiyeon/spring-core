package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingeltonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        /*
        memberService1 = hello.core.member.MemberServiceImpl@55b0dcab
        memberService2 = hello.core.member.MemberServiceImpl@38afe297
         */

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
        //멤버 서비스 뿐만 아니라 레포지토리까지 생성해서 객체가 총 4개 생성됨(2개씩)
    }


    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        //new SingletonService();
        //java: SingletonService() has private access in hello.core.singleton.SingletonService
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);

        /*
        instance1 = hello.core.singleton.SingletonService@77f1baf5
        instance2 = hello.core.singleton.SingletonService@77f1baf5
         */

        Assertions.assertThat(instance1).isSameAs(instance2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        /*
        memberService1 = hello.core.member.MemberServiceImpl@5cdd09b1
        memberService2 = hello.core.member.MemberServiceImpl@5cdd09b1
         */

        Assertions.assertThat(memberService1).isSameAs(memberService2);

    }
}
