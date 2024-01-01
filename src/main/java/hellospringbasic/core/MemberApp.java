package hellospringbasic.core;

import hellospringbasic.core.member.Grade;
import hellospringbasic.core.member.Member;
import hellospringbasic.core.member.MemberService;
import hellospringbasic.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member mem = new Member(1L, "memA", Grade.VIP);

        memberService.join(mem);

        Member findMember = memberService.findMember(1L);
        System.out.println("mem = " + mem.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
