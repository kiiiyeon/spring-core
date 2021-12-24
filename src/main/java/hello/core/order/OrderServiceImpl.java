package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor //final 붙은 것들(필수값) 생성자 만들어줌 (required)
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository; //final -> 한 번 설정하면 변경X, 값이 무조건 있어야함, 값 -> 초기 설정 / 생성자 주입 생성자 주입을 쓰면 final을 사용할 수 있다!
    private final DiscountPolicy discountPolicy; //어떤 할인 정책이 들어올지 모름
    //이제 인터페이스에만 의존, DIP 완성~

    /*
    수정자(세터) 주입
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
     */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
