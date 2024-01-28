### 강의명 : 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술
### 출처  : 인프런
### 강사: 김영한
</br>

## 🗂️ 목차
### 👉 Section 0
- 강의 소개
- 강의 자료
### 👉 Section 1
- 프로젝트 생성
- 라이브러리 살펴보기
- View 환경설정
- 빌드하고 실행하기
### 👉 Section 2
- 정적 컨텐츠
- MVC와 템플릿 엔진
    - @RequestParam 
    - 스프링 부트에서 서버 핸들러를 통해서 엔진으로 렌더링 되는 과정 설명
- API
    - @ResponseBody
    - View Resolver
    - HttpMessageConverter
        - JsonConverter -> StringHttpMessageConverter 
        - StringConverter -> MappingJackson2HttpMessageConverter
    - HTTP Accept
        - 요청에 따라서 달라질 MessageConverter가 달라질 수 있음을 언급 
### 👉 Section 3
- 비지니스 요구사항 정리
- 회원 도메인과 리포지토리 만들기
    - Repository 
    - ConcurrentHashMap
    - Optional
- 회원 리포지토리 테스트 케이스 작성
    - Test Code 작성 
- 회원 서비스 개발
- 회원 서비스 테스트
    - Dependency Injection 
### 👉 Section 4
- 컴포넌트 스캔과 자동 의존관계 설정
    - @Component과 @ComponentScan
        - Scan Range 
        - @Controller
        - @Service
        - @Repository
    - Spring Container
    - Singleton Object
- 자바 코드로 직접 스프링 빈 등록하기
    - DI
        - Field DI
        - Setter DI
        - Construction DI
### 👉 Section 5
- 회원 웹 기능 - 홈 화면 추가
- 회원 웹 기능 - 등록
    - @PostMapping
    - @GetMapping
    - HTML Form tag
- 회원 웹 기능 - 조회
### 👉 Section 6
- H2 데이터베이스 설치
    - Install & Settings
- 순수 JDBC
    - OCP(One of SOLID principles)
    - JDBC
    - DataSource
- 스프링 통합 테스트
    - @SpringBootTest
        - 스프링 컨테이너와 함께 실행하게 해줌 
    - @Transactional
        - 테스트 시 DB 작업 후 Rollback 해줌 -> 다음 테스트에 영향을 주지 않게 하기 위해서
    - @Commit
        - Rollback 하지 않기 위해서 사용 
    - Integration 테스트 할 때 test DB 혹은 local DB
    - 단위 테스트: 스프링 컨테이너 없이 -> 스프링 실행 후 컨테이너에 빈 등록하고 하면 시간이 오래 걸려서 단순 java 코드로 테스트
    - 통합 테스트: 스프링 컨테이너와 함께
- 스프링 JdbcTemplate
    - JdbcTemplate과 MyBatis는 JDBC API에서 반복 코드를 제거 해줌
        - ex) Connection 열고 쿼리 작성하고, 실행하고, 결과 받고 등 등
    - 쿼리는 직접 작성 해야한다.
- JPA
    - query 작성을 하지 않아도 되는 이유도 있지만, 데이터와 SQL 중심이 아닌 객체 중심의 설계로 패러다임 전환이 가능
    - JPA (Java Persistence API)
    - Hibernate
    - ORM (Object Relational Mapping)
    - JPA 관련 Annotation
        - @Entity
        - @Id
        - @GeneratedValue (IDENTITY 전략)
        - @Column
    - EntityManager
        - Spring Boot 가 Injection 해줌
- 스프링 데이터 JPA
    - SpringDataJpa가 JpaRepository를 상속할 시 구현체 생성해줌
    - Interface 만으로 공통적인 많은 부분 해결 가능 (Reflection으로 정보 얻어서 공통 처리 하는듯)
    - 동적 쿼리는 Querydls 라이브러리를 사용
    - 어려운 쿼리는 JPA도 네이티브 쿼리도 사용 가능함 or JdbcTemplate 사용 or Mybatis 사용
### 👉 Section 7
- AOP가 필요한 상황
    - 공통 관심 사항(cross-cutting concern) vs 핵심 관심 사항(code concern)
- AOP 적용(Aspect Oriented Programming)
    - 관심 사항의 분리
    - @Aspect
    - @Around
    - Proxy
        - EnhanceBySpringCGLIB 에 의해서 복사된 프록시로 요청이 먼저 들어감
