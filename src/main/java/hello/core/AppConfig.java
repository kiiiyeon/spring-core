package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /*
    @Bean memberService -> new MemoryMemberRepository()
    @Bean orderService -> new MemoryMemberRepository(), new FixDiscountPolicy()
    서비스 객체 생성할 때 레포지토리, 할인 정책 객체가 새로 생김 -> 각각 다른 MemoryMemberRepository()가 생성되는 것 같음 -> 싱글톤을 지킬 수 있을까?
     */

    @Bean
    public MemberService memberService() { //역할
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); //구현
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    /*
    예상
    call AppConfig.memberService (memberService)
    call AppConfig.memberRepository (memberService)
    call AppConfig.memberRepository (memberRepository)
    call AppConfig.orderService (orderService)
    call AppConfig.memberRepository (orderService)
     */

    /*
    결과
    call AppConfig.memberService
    call AppConfig.memberRepository
    call AppConfig.orderService
     */

    @Bean //스프링 컨테이너에 등록
    public DiscountPolicy discountPolicy() { //할인 정책으로
        //return new FixDiscountPolicy(); //고정할인을 사용-> 전체적으로 어떤걸 쓰는지 한 눈에 볼 수 있음? 자료 보기! 정률으로 바꿀거면 AppConfig만 고치면 됨
        return new RateDiscountPolicy();
    }
    /*
    //생성자 주입
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
    */
}
