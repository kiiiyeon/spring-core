package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService(); //MemberServiceImpl 할당 + MemoryMemberRepository 사용 -> 주입
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); //AppConfig안에 있는 객체들을 컨테이너에 올리고 관리
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);//이름(메소드 이름), 반환 타입
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
