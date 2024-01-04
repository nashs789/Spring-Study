package hellospringbasic.core.autowired.allBean;

import hellospringbasic.core.AutoAppConfig;
import hellospringbasic.core.discount.DiscountPolicy;
import hellospringbasic.core.member.Grade;
import hellospringbasic.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    @Test
    void findAllBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService bean = ac.getBean(DiscountService.class);
        Member mem = new Member(1L, "userA", Grade.VIP);

        int fixDiscountPolicy = bean.discount(mem, 10000, "fixDiscountPolicy");

        assertThat(bean).isInstanceOf(DiscountService.class);
        assertThat(fixDiscountPolicy).isEqualTo(1000);

        int rateDiscountPolicy = bean.discount(mem, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPolicy).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;

            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member mem, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);

            return discountPolicy.discount(mem, price);
        }
    }
}
