package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
/*
@ComponentScan -> @Component 붙은 것들을 자동으로 컨테이너에 등록
basePackages -> 탐색 패키지 지정, default는 @ComponentScan을 붙인 곳 부터 시작 -> hello.core 패키지
basePackagesClasses -> 탐색 클래스 지정, 클래스의 맨 위에 있는 package부터 시작
excludeFilters -> AppConfig Configuration 배제
 */
public class AutoAppConfig {
    /*
    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
     */
}
