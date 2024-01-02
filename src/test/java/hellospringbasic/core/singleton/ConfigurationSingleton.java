package hellospringbasic.core.singleton;

import hellospringbasic.core.AppConfig;
import hellospringbasic.core.member.MemberRepository;
import hellospringbasic.core.member.MemberServiceImpl;
import hellospringbasic.core.member.MemoryMemberRepository;
import hellospringbasic.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ConfigurationSingleton {

    @Test
    void configurationTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl bean1 = ac.getBean(MemberServiceImpl.class);
        OrderServiceImpl bean2 = ac.getBean(OrderServiceImpl.class);
        MemoryMemberRepository bean3 = ac.getBean(MemoryMemberRepository.class);

        MemberRepository memberRepository1 = bean1.getMemberRepository();
        MemberRepository memberRepository2 = bean2.getMemberRepository();

        System.out.println("member -> memberRepository1 = " + memberRepository1);
        System.out.println("order -> memberRepository2 = " + memberRepository2);
        System.out.println("memory = " + bean3);

        assertThat(bean1.getMemberRepository()).isSameAs(bean3);
        assertThat(bean2.getMemberRepository()).isSameAs(bean3);
    }
    
    @Test
    void configurationDeep() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean.getClass() = " + bean.getClass());
    }
}
