package hellospringbasic.core;

import hellospringbasic.core.member.Grade;
import hellospringbasic.core.member.Member;
import hellospringbasic.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member mem = new Member(1L, "memA", Grade.VIP);
        memberService.join(mem);

        Member findMember = memberService.findMember(1L);
        System.out.println("mem = " + mem.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
