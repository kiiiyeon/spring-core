package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    //순수한 자바 코드로 OrderServiceImpl 로직 테스트 코드 작성
    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000); //수정자 주입을 사용할 경우 NullPointException
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}