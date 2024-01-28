package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memService;
    @Autowired
    MemberRepository memoryRepository;

    @Test
    void 회원가입() {
        // given - 주어진 상황
        Member mem = new Member();
        mem.setName("spring rollback");

        // when - 실행 했을 때
        Long saveId = memService.join(mem);

        // then - 나와야 할 결과
        Member findMem = memService.findOne(saveId).get();
        assertThat(mem.getName()).isEqualTo(findMem.getName());
    }

    @Test
    public void 중복_회원_예외(){
        // given - 주어진 상황
        Member mem1 = new Member();
        mem1.setName("spring abc");

        Member mem2 = new Member();
        mem2.setName("spring abc");

        // when - 실행 했을 때
        memService.join(mem1);

        // then - 나와야 할 결과
        assertThrows(IllegalArgumentException.class, () -> memService.join(mem2));
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}