package hellospringbasic.core.member;

import hellospringbasic.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

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
