package study.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberDto;
import study.querydsl.dto.QMemberDto;
import study.querydsl.dto.UserDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.QTeam;
import study.querydsl.entity.Team;

import java.util.List;

import static com.querydsl.jpa.JPAExpressions.*;
import static org.assertj.core.api.Assertions.*;
import static study.querydsl.entity.QMember.member;
import static study.querydsl.entity.QTeam.team;

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

    @Test
    public void paging1() {
        List<Member> result = queryFactory.selectFrom(member)
                                          .orderBy(member.username.desc())
                                          .offset(1)
                                          .limit(2)
                                          .fetch();

        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void paging2() {
        QueryResults<Member> result = queryFactory.selectFrom(member)
                                                  .orderBy(member.username.desc())
                                                  .offset(1)
                                                  .limit(2)
                                                  .fetchResults();

        assertThat(result.getTotal()).isEqualTo(4);
        assertThat(result.getLimit()).isEqualTo(2);
        assertThat(result.getOffset()).isEqualTo(1);
        assertThat(result.getResults().size()).isEqualTo(2);
    }

    @Test
    public void aggregation() {
        List<Tuple> result = queryFactory.select(
                                            member.count(),
                                            member.age.sum(),
                                            member.age.avg(),
                                            member.age.max(),
                                            member.age.min()
                                         )
                                         .from(member)
                                         .fetch();

        Tuple tuple = result.get(0);

        assertThat(tuple.get(member.count())).isEqualTo(4);
        assertThat(tuple.get(member.age.sum())).isEqualTo(100);
        assertThat(tuple.get(member.age.avg())).isEqualTo(25);
        assertThat(tuple.get(member.age.max())).isEqualTo(40);
        assertThat(tuple.get(member.age.min())).isEqualTo(10);
    }

    /**
     * 팀의 이름과 각 팀의 평균 연령을 구해라.
     */
    @Test
    public void group() {
        List<Tuple> fetch = queryFactory.select(
                                            team.name,
                                            member.age.avg()
                                        )
                                        .from(member)
                                        .join(member.team, team)
                                        .groupBy(team.name)
                                        .fetch();

        Tuple teamA = fetch.get(0);
        Tuple teamB = fetch.get(1);

        assertThat(teamA.get(team.name)).isEqualTo("teamA");
        assertThat(teamA.get(member.age.avg())).isEqualTo(15);
        assertThat(teamB.get(team.name)).isEqualTo("teamB");
        assertThat(teamB.get(member.age.avg())).isEqualTo(35);
    }

    /**
     * 팀 A에 소속된 모든 회원
     */
    @Test
    public void join() {
        List<Member> res = queryFactory.selectFrom(member)
                                       .leftJoin(member.team, team)
                                       .where(team.name.eq("teamA"))
                                       .fetch();

        assertThat(res).extracting("username")
                       .containsExactly("member1", "member2");
    }

    /**
     * 세타 조인 (연관 관계가 없어도 조인하는 방법)
     * 회원의 이름이 팀 이름과 같은 회원을 조회
     */
    @Test
    public void theta_join() {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        em.persist(new Member("teamC"));

        List<Member> res = queryFactory.select(member)
                                       .from(member, team)
                                       .where(member.username.eq(team.name))
                                       .fetch();

        assertThat(res).extracting("username")
                       .containsExactly("teamA", "teamB");
    }

    /**
     * 예) 회원과 팀을 조인하면서, 팀 이름이 teamA인 팀만 조인, 회원은 모두 조회
     * JQPL: select m, t from Member m left join m.team on t.name = 'teamA'
     */
    @Test
    public void join_on_filtering() {
        List<Tuple> teamA = queryFactory.select(member, team)
                                        .from(member)
                                        .leftJoin(member.team, team)
                                        .on(team.name.eq("teamA"))
                                        .fetch();

        for(Tuple tuple : teamA) {
            System.out.println("tuple = " + tuple);
        }
    }

    /**
     * 연관관계 없는 엔티티 외부 조인
     * 회원의 이름이 팀 이름과 같은 회원 조회
     */
    @Test
    public void join_on_no_relation() {
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        em.persist(new Member("teamC"));

        List<Tuple> res = queryFactory.select(member, team)
                                      .from(member)
                                      .leftJoin(team)
                                      .on(member.username.eq(team.name))
                                      .fetch();

        for(Tuple tuple : res) {
            System.out.println("tuple = " + tuple);
        }
    }

    @PersistenceUnit
    EntityManagerFactory emf;

    @Test
    public void fetchJoinNo() {
        em.flush();
        em.clear();

        Member mem = queryFactory.selectFrom(member)
                                 .where(member.username.eq("member1"))
                                 .fetchOne();

        // Member 에서 Team 엔티티는 Lazy 로 설정되어 있기 때문에 현재 데이터가 존재하면 안됨
        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(mem.getTeam());

        assertThat(loaded).as("페치 조인 미적용").isFalse();
    }

    @Test
    public void fetchJoinUse() {
        em.flush();
        em.clear();

        Member mem = queryFactory.selectFrom(member)
                                 .join(member.team, team)
                                 .fetchJoin()
                                 .where(member.username.eq("member1"))
                                 .fetchOne();

        // Member 에서 Team 엔티티는 Lazy 로 설정되어 있기 때문에 현재 데이터가 존재하면 안됨
        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(mem.getTeam());

        assertThat(loaded).as("페치 조인 미적용").isTrue();
    }

    /**
     * 나이가 가장 많은 회원 조회
     */
    @Test
    public void sbuQuery() {

        QMember memberSub = new QMember("memberSub");

        List<Member> res = queryFactory.selectFrom(member)
                                       .where(member.age.eq(
                                               select(memberSub.age.max())
                                                             .from(memberSub)
                                       ))
                                       .fetch();

        assertThat(res).extracting("age")
                       .containsExactly(40);
    }

    /**
     * 나이가 평균 이상인 회원
     */
    @Test
    public void sbuQueryGoe() {

        QMember memberSub = new QMember("memberSub");

        List<Member> res = queryFactory.selectFrom(member)
                                       .where(member.age.goe(
                                               select(memberSub.age.avg()).from(memberSub)
                                       ))
                                       .fetch();

        assertThat(res).extracting("age")
                .containsExactly(30, 40);
    }

    /**
     * 나이가 평균 이상인 회원
     */
    @Test
    public void sbuQueryIn() {

        QMember memberSub = new QMember("memberSub");

        List<Member> res = queryFactory.selectFrom(member)
                                       .where(member.age.in(
                                               select(memberSub.age).from(memberSub)
                                                                    .where(member.age.gt(10))
                                       ))
                                       .fetch();

        assertThat(res).extracting("age")
                .containsExactly(20, 30, 40);
    }

    @Test
    public void selectSubquery() {
        QMember memberSub = new QMember("memberSub");

        List<Tuple> mem = queryFactory.select(
                                          member.username,
                                          select(memberSub.age.avg()).from(memberSub)
                                      )
                                      .from(member)
                                      .fetch();
    }

    @Test
    public void basicCase(){
        List<String> fetch = queryFactory.select(member.age.when(10).then("열살")
                                                           .when(20).then("스무살")
                                                           .otherwise("기타")
                                         )
                                         .from(member)
                                         .fetch();

        for(String s : fetch) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void complexCase() {
        List<String> fetch = queryFactory.select(new CaseBuilder()
                                                 .when(member.age.between(0, 20)).then("0 ~ 20살")
                                                 .when(member.age.between(21, 30)).then("21 ~ 30살")
                                                 .otherwise("기타")
                                         )
                                         .from(member)
                                         .fetch();

        for(String s : fetch) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void constant() {
        List<Tuple> a = queryFactory.select(member.username, Expressions.constant("A"))
                                    .from(member)
                                    .fetch();

        for(Tuple tuple : a) {
            System.out.println("tuple = " + tuple);
        }
    }

    @Test
    public void concat() {
        // {username}_{age}
        List<String> fetch = queryFactory.select(member.username.concat("_").concat(member.age.stringValue()))
                                         .from(member)
                                         .where(member.username.eq("member1"))
                                         .fetch();

        for(String s : fetch) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void simpleProjection() {
        List<String> result = queryFactory.select(member.username)
                                          .from(member)
                                          .fetch();

        for(String s : result) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void tupleProjection() {
        List<Tuple> result = queryFactory.select(
                                             member.username,
                                             member.age
                                         )
                                         .from(member)
                                         .fetch();

        for(Tuple tuple : result) {
            String username = tuple.get(member.username);
            Integer age = tuple.get(member.age);

            System.out.println("username = " + username);
            System.out.println("age = " + age);
        }
    }

    @Test
    public void findDtoJPQL() {
        // 생성자가 있는 DTO 가 있어야함
        // 패키지명까지 적어줘야함
        List<MemberDto> resultList = em.createQuery("select new study.querydsl.dto.MemberDto(m.username, m.age) from Member m", MemberDto.class)
                                       .getResultList();

        for(MemberDto memberDto : resultList) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    public void findDtoBySetter() {
        List<MemberDto> result = queryFactory.select(Projections.bean(MemberDto.class,
                                                member.username,
                                                member.age)
                                             )
                                             .from(member)
                                             .fetch();

        for(MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    public void findDtoByField() {
        List<MemberDto> result = queryFactory.select(Projections.fields(MemberDto.class,
                                                 member.username,
                                                 member.age)
                                             )
                                             .from(member)
                                             .fetch();

        for(MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    public void findDtoByConstructor() {
        List<MemberDto> result = queryFactory.select(Projections.constructor(MemberDto.class,
                                                 member.username,
                                                 member.age)
                                             )
                                             .from(member)
                                             .fetch();

        for(MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    public void findUserDto() {
        QMember memSub = new QMember("memSub");

        List<UserDto> result = queryFactory.select(Projections.fields(UserDto.class,
                                               member.username.as("name"),
                                                ExpressionUtils.as(JPAExpressions.select(memSub.age.max())
                                                                                 .from(memSub), "age")
                                                                  )
                                           )
                                           .from(member)
                                           .fetch();

        for(UserDto userDto : result) {
            System.out.println("userDto = " + userDto);
        }
    }

    @Test
    public void findDtoByQueryProjection() {
        List<MemberDto> result = queryFactory.select(new QMemberDto(member.username, member.age))
                                             .from(member)
                                             .fetch();

        for(MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    public void dynamicquery_BooleanBuilder() {
        String usernameParam = "member1";
        Integer ageParam = 10;

        List<Member> result = searchMember1(usernameParam, ageParam);

        assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember1(String usernameCond, Integer ageCond) {
        BooleanBuilder builder = new BooleanBuilder();

        if(usernameCond != null) {
            builder.and(member.username.eq(usernameCond));
        }

        if(ageCond != null) {
            builder.and(member.age.eq(ageCond));
        }

        return queryFactory.selectFrom(member)
                           .where(builder)
                           .fetch();
    }

    @Test
    public void dynamicQuery_WhereParam() {
        String usernameParam = "member1";
        Integer ageParam = 10;

        List<Member> result = searchMember2(usernameParam, ageParam);

        assertThat(result.size()).isEqualTo(1);
    }

    private List<Member> searchMember2(String usernameCond, Integer ageCond) {
        return queryFactory.selectFrom(member)
                           //.where(usernameEq(usernameCond), ageEq(ageCond))
                           .where(allEq(usernameCond, ageCond))
                           .fetch();
    }

    private BooleanExpression ageEq(Integer ageCond) {
        return ageCond != null ? member.age.eq(ageCond) : null;
    }

    private BooleanExpression usernameEq(String usernameCond) {
        return usernameCond != null ? member.username.eq(usernameCond) : null;
    }

    private BooleanExpression allEq(String usernameCond, Integer ageCond) {
        return usernameEq(usernameCond).and(ageEq(ageCond));
    }
}
