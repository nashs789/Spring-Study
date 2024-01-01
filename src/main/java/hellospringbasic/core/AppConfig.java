package hellospringbasic.core;

import hellospringbasic.core.discount.DiscountPolicy;
import hellospringbasic.core.discount.FixDiscountPolicy;
import hellospringbasic.core.discount.RateDiscountPolicy;
import hellospringbasic.core.member.MemberService;
import hellospringbasic.core.member.MemberServiceImpl;
import hellospringbasic.core.member.MemoryMemberRepository;
import hellospringbasic.core.order.OrderService;
import hellospringbasic.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(getMemberRepository());
    }

    private MemoryMemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(getMemberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
        //return new FixDiscountPolicy();
    }
}