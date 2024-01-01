package hellospringbasic.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

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
}
