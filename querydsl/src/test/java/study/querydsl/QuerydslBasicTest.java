package study.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.Team;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static study.querydsl.entity.QMember.member;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    public void startJPQL() {
        // member1 찾기
        Member findMember = em.createQuery("select m " +
                                                  "from Member m " +
                                                  "where m.username = :username"
                                                  , Member.class)
                              .setParameter("username", "member1")
                              .getSingleResult();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void startQuerydsl() {
        Member member1 = queryFactory.select(member)
                                     .from(member)
                                     .where(member.username.eq("member1"))
                                     .fetchOne();

        assertThat(member1.getUsername()).isEqualTo("member1");
    }

    @Test
    public void search() {
        Member findMember = queryFactory.selectFrom(member)
                                        .where(member.username.eq("member1")
                                              .and(member.age.eq(10)))
                                        .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void searchAndParam() {
        Member findMember = queryFactory.selectFrom(member)
                .where(  // ',' 로 구분하는게 and 조건과 같음
                        member.username.eq("member1"), (member.age.between(10, 30))
                )
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void resultFetch() {
//        List<Member> fetch = queryFactory.selectFrom(member)
//                .fetch();
//
//        Member member = queryFactory.selectFrom(QMember.member)
//                .fetchOne();
//
//        Member member1 = queryFactory.selectFrom(QMember.member)
//                .fetchFirst();

        QueryResults<Member> results = queryFactory.selectFrom(QMember.member)
                .fetchResults();

        results.getTotal();
        List<Member> content = results.getResults();

        long total = queryFactory.selectFrom(member)
                                 .fetchCount();
    }

    /**
     * 회원 정렬 순서
     * 1. 회원 나이 내림차순(DESC)
     * 2. 회원 이름 오름차순(ASC)
     * 단 2에서 회원 이름이 없으면 마지막에 출력(nulls last)
     */
    @Test
    public void sort() {
        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 100));
        em.persist(new Member("member6", 100));

        List<Member> result = queryFactory.selectFrom(member)
                                          .where(member.age.eq(100))
                                          .orderBy(member.age.desc(), member.username.asc().nullsLast())
                                          .fetch();

        Member member5 = result.get(0);
        Member member6 = result.get(1);
        Member memberNull = result.get(2);

        assertThat(member5.getUsername()).isEqualTo("member5");
        assertThat(member6.getUsername()).isEqualTo("member6");
        assertThat(memberNull.getUsername()).isNull();
    }
}
