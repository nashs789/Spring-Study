package hellospringbasic.core.autowired;

import hellospringbasic.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        @Autowired(required = false)
        public void setNoBean1(Member mem){
            System.out.println("mem1 = " + mem);
        }

        @Autowired
        public void setNoBean2(@Nullable Member mem){
            System.out.println("mem2 = " + mem);
        }

        @Autowired
        public void setNoBean3(Optional<Member> mem){
            System.out.println("mem3 = " + mem);
        }
    }
}
