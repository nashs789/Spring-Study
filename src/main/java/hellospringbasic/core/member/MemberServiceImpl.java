package hellospringbasic.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member mem) {
        memberRepository.save(mem);
    }

    @Override
    public Member findMember(Long memId) {
        return memberRepository.findById(memId);
    }

    // 테스트용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
