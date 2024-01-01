package hellospringbasic.core.member;

public interface MemberRepository {
    void save(Member mem);
    Member findById(Long memId);
}
