package hellospringbasic.core.discount;

import hellospringbasic.core.member.Member;

public interface DiscountPolicy {

    /**
     * @return 할인 대상 금액
     */
    int discount(Member mem, int price);
}
