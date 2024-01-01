package hellospringbasic.core;

import hellospringbasic.core.discount.FixDiscountPolicy;
import hellospringbasic.core.member.MemberService;
import hellospringbasic.core.member.MemberServiceImpl;
import hellospringbasic.core.member.MemoryMemberRepository;
import hellospringbasic.core.order.OrderService;
import hellospringbasic.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}