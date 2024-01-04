package hellospringbasic.core.discount;

import hellospringbasic.core.member.Grade;
import hellospringbasic.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;  // 1,000원 할인

    @Override
    public int discount(Member mem, int price) {
        return mem.getGrade() == Grade.VIP ? discountFixAmount : 0;
    }
}
