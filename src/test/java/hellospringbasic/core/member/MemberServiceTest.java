package hellospringbasic.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        // given
        Member mem = new Member(1L, "memA", Grade.VIP);

        // when
        memberService.join(mem);
        Member findMem = memberService.findMember(1L);

        // then
        assertThat(mem).isEqualTo(findMem);
    }
}
