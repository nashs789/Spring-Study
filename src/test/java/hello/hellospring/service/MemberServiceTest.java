package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memService;
    MemoryMemberRepository memoryRepository;

    @BeforeEach
    public void beforeEach(){
        memoryRepository = new MemoryMemberRepository();
        memService = new MemberService(memoryRepository);
    }

    @AfterEach
    public void afterEach(){
        memoryRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given - 주어진 상황
        Member mem = new Member();
        mem.setName("spring");

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
        mem1.setName("spring");

        Member mem2 = new Member();
        mem2.setName("spring");

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