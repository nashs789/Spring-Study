package hellospringbasic.core;

import hellospringbasic.core.member.Grade;
import hellospringbasic.core.member.Member;
import hellospringbasic.core.member.MemberService;
import hellospringbasic.core.order.Order;
import hellospringbasic.core.order.OrderService;

public class OrderApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memId = 1L;
        Member mem = new Member(memId, "memA", Grade.VIP);
        memberService.join(mem);

        Order order = orderService.createOrder(memId, "itemA", 20000);

        System.out.println("order = " + order);
    }
}
