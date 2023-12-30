package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save() {
        Member mem = new Member();

        mem.setName("spring");
        repository.save(mem);

        Member res = repository.findById(mem.getId()).get();
        assertThat(mem).isEqualTo(res);
    }

    @Test
    public void findByName(){
        Member mem1 = new Member();
        mem1.setName("spring1");
        repository.save(mem1);

        Member mem2 = new Member();
        mem2.setName("spring2");
        repository.save(mem2);

        Member res = repository.findByName("spring1").get();

        assertThat(res).isEqualTo(mem1);
    }

    @Test
    public void findAll(){
        Member mem1 = new Member();
        mem1.setName("spring1");
        repository.save(mem1);

        Member mem2 = new Member();
        mem2.setName("spring2");
        repository.save(mem2);

        List<Member> res = repository.findAll();

        assertThat(res.size()).isEqualTo(2);
    }
}
