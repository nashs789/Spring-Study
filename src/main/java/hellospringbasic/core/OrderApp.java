package hellospringbasic.core;

import hellospringbasic.core.member.Grade;
import hellospringbasic.core.member.Member;
import hellospringbasic.core.member.MemberService;
import hellospringbasic.core.member.MemberServiceImpl;
import hellospringbasic.core.order.Order;
import hellospringbasic.core.order.OrderService;
import hellospringbasic.core.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memId = 1L;
        Member mem = new Member(memId, "memA", Grade.VIP);
        memberService.join(mem);

        Order order = orderService.createOrder(memId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}
