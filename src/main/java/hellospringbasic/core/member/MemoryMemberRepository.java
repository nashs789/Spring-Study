package hellospringbasic.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member mem) {
        store.put(mem.getId(), mem);
    }

    @Override
    public Member findById(Long memId) {
        return store.get(memId);
    }
}