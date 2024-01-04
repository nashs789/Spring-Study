package hellospringbasic.core.order;

import hellospringbasic.core.discount.DiscountPolicy;
import hellospringbasic.core.discount.FixDiscountPolicy;
import hellospringbasic.core.discount.RateDiscountPolicy;
import hellospringbasic.core.member.Member;
import hellospringbasic.core.member.MemberRepository;
import hellospringbasic.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memId, String itemName, int itemPrice) {
        Member mem = memberRepository.findById(memId);
        int discountPrice = discountPolicy.discount(mem, itemPrice);

        return new Order(memId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
