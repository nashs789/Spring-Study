package hellospringbasic.core.order;

import hellospringbasic.core.discount.DiscountPolicy;
import hellospringbasic.core.discount.FixDiscountPolicy;
import hellospringbasic.core.discount.RateDiscountPolicy;
import hellospringbasic.core.member.Member;
import hellospringbasic.core.member.MemberRepository;
import hellospringbasic.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memId, String itemName, int itemPrice) {
        Member mem = memberRepository.findById(memId);
        int discountPrice = discountPolicy.discount(mem, itemPrice);

        return new Order(memId, itemName, itemPrice, discountPrice);
    }
}
