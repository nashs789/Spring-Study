package hellospringbasic.core.member;

public interface MemberService {
    void join(Member mem);
    Member findMember(Long memId);
}
