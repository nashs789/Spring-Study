package hellospringbasic.core.discount;

import hellospringbasic.core.annotation.MainDiscountPolicy;
import hellospringbasic.core.member.Grade;
import hellospringbasic.core.member.Member;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member mem, int price) {
        return mem.getGrade() == Grade.VIP ? price * discountPercent / 100 : 0;
    }
}
