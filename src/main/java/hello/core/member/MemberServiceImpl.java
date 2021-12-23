package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    //추상화에 의존, MemoryMemberRepository에 관한 코드는 찾을 수 없음
    private final MemberRepository memberRepository;

    @Autowired //생성자에 붙여주면 자동으로 의존관계 주입
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
