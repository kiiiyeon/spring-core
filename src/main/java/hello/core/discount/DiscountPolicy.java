package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {
    /**
     * @return 할인 정책 대상
     */
    int discount(Member member, int price);
}
