package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        // given
        Member mem = new Member("hello", 20);

        // when
        Member saved = memberRepository.save(mem);

        // then
        Member found = memberRepository.findById(saved.getId());

        assertThat(found).isEqualTo(saved);
    }

    @Test
    void findAll() {
        // given
        Member mem1 = new Member("mem1", 20);
        Member mem2 = new Member("mem2", 30);

        memberRepository.save(mem1);
        memberRepository.save(mem2);

        // when
        List<Member> res = memberRepository.findAll();

        assertThat(res.size()).isEqualTo(2);
        assertThat(res).contains(mem1, mem2);
    }
}