package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy(); //option + command + B import된 함수로 이동

    @Test
    @DisplayName("VIP는 10% 할인")
    void vip_success() {
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP); //option + command + v
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(1000); //Assertions 에서 option + enter on-demand static으로 변경
    }

    //실패 테스트를 만드는 것도 중요함!
    @Test
    @DisplayName("VIP가 아니면 할인 적용X")
    void vip_fail() {
        Member member = new Member(2L, "memberBasic", Grade.BASIC); //option + command + v
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(1000);  //0
    }
}