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
    @Bean
    public MemberService memberService() { //역할
        return new MemberServiceImpl(memberRepository()); //구현
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

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
