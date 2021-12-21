package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //추상화에 의존, MemoryMemberRepository에 관한 코드는 찾을 수 없음
    private final MemberRepository memberRepository;

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
}
