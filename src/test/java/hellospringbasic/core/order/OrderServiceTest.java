package hellospringbasic.core.order;

import hellospringbasic.core.AppConfig;
import hellospringbasic.core.member.Grade;
import hellospringbasic.core.member.Member;
import hellospringbasic.core.member.MemberService;
import hellospringbasic.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memId = 1L;
        Member mem = new Member(memId, "memA", Grade.VIP);
        memberService.join(mem);

        Order order = orderService.createOrder(memId, "itemA", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}
