### 강의명 : 자바 ORM 표준 JPA 프로그래밍 - 기본편
### 출처  : 인프런
### 강사: 김영한
</br>

## 📑 수강 목록(Spring Boot)
- [스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술](https://github.com/nashs789/spring-intro)
- [스프링 핵심 원리 - 기본편](https://github.com/nashs789/spring-basic)
</br>

## 📑 수강 목록(JPA)
- 자바 ORM 표준 JPA 프로그래밍 - 기본편
</br>

## 🗂️ 목차
### 👉 Section 1
- SQL 중심적인 개발의 문제점
  - 객체 지향과 관계형 데이터베이스의 패러다임 (상속 vs 연관관계)
  - 자바 콜렉션에서 데이터를 관리 한다면?
    - 객체 그래프 탐색
    - 엔티티 신뢰성 -> null 값에 대해
    - 비교 연산
      - SQL 실행 후 인스턴스 생성시 필드 정보는 매번 달라짐 하지만 콜렉션으로 관리시 같음
- JPA 소개 (Java Persistence API)
  - ORM 기술 표준 (Obeject-relational mapping: 객체 관계 매핑)
  - 객체는 객체, RDB는 RDB로 설계하고 ORM 프레임워크가 중간에 매핑
    - Java - JAP - JDBC API - DB
  - JPA를 왜 사용?
    - SQL 중심적인 개발에서 객체 중심 개발로
      - 생산성 ↑
      - 유지보수 ↑
  - 성능 최적화
    - 트랜잭션내의 1차 캐시로 동일성 보장
    - 트랜잭션을 지원하는 쓰기 지연(transactional write-behind)
    - 지연 로딩(Lazy Loading)

### 👉 Section 2
- Hello JPA - 프로젝트 생성
  - Spring boot + maven 프로젝트 생성
  - H2 데이터베이스 설정 세팅
- Hello JPA - 애플리케이션 개발
  - createEntityManagerFactory
    - persistence.xml의 persistence-unit의 name속성 값이 인자로
  - EntityManager
    - 쓰레드간 공유 x
  - @Entity
  - @Id
  - SQL 
    - Select
      - find
    - Insert
      - persist
    - Update
      - Java 인스턴스 필드를 수정하면 update 수행
    - Delete
      - remove
  - 모든 데이터 변경은 트랜잭션 내에서 실행
  - JPQL
    - SQL이 아닌 객체 중심으로 쿼리를 생성해서 종속적이지 않도록 검색 조건을 갖도록함

### 👉 Section 3
- 영속성 컨텍스트
  - EntityManager와 PersistenceContext가 1:1로 매핑되어 있음 -> PersitenceContext는 여러개의 EntityManager를 갖음(아마 스프링 Bean 말하는 듯)
  - 생명주기
    - 비영속(new/transient): 영속성 컨텍스트와 무관한 새로운 상태
        - JPA랑 관계 없이 인스턴스만 생성한 상태(EntityManager랑 관계 없을 때)
    - 영속(managed): 영속성 컨텍스트에 관리되는 상태
    - 준영속(detached): 영속성 컨텍스트에 저장되어 있다가 분리된 상태
      - detached 메소드 호출시 영속성 컨텍스트에서 제거
    - 삭제(removed): 삭제된 상태
  - 장점
    - 1차 캐시
      - Entity에서 설정한 DB PK 값이 key 값으로, Entity는 value로 매핑
      - DB에서 조회 하기 전에 캐시 존재한다면 불필요한 I/O 작업 제거
      - ❖ 한 트랜잭션에서만 존재하는 캐시이기 때문에 성능의 이점을 크게 기대하기는 힘들다
    - 동일성(Identity) 보장
      - DB의 트랜잭션 격리레벨인 Repeatable Read를 어플리케이션 레이어에서 제공
    - 트랜잭션 쓰기 지연(transactional write-behind)
      - 1차 캐시에 저장하면서 쓰기 지연 SQL 저장소(Write Behind SQL Storage)에 Insert 쿼리를 생성해서 저장한다.
      - commit 시점에 쿼리 실행 -> flush가 진행됨
      - batch_size를 통해서 쿼리 갯수 조절 가능
    - 뵨굥 검자(Dirty Checking)
      - JPA의 목적이 java 콜렉션 처럼 사용하는 것 -> update시 조회된 인스턴스 값 변경 후 추가 조치 없어도 DB에 반영
        - 어떻게? -> 1차 캐시에 스냅샷을 가지고 있다가 flush 호출 시(commit) 엔티티와 스냅샷을 비교해서 Dirty check 후 update 쿼리 생성 후 반영
    - 지연 로딩(Lazy Loading)
- 플러시
  - 영속성 컨텍스트 변경사항 DB에 반영
  - Dirty Checking -> write-behind SQL storage 저장 -> write-behind
  - 플러쉬 방법 -> 왜 필요?: 쿼리를 먼저 보기 위해서(테스트)
    - flsuh() 명시적 호출
    - commit
    - JPQL 실행
      - raw 쿼리를 사용하기 때문에 persist로 캐시에 저장해도 DB에 없기 떄문에 조회 불가능해서
  - 캐시를 지우는게 아님(쓰기 지연 SQL 저장소의 쿼리를 DB에 반영)
- 준영속 상태
  - persist 호출 없이 find를 하게 된다면 캐시에 존재하지 않기 때문에 DB 조회 후 캐시에 저장
  - 준영속이란? -> 영속성 컨텍스트에서 분리한다 -> 더 이상 영속성 컨텍스트가 제공하는 컨텐츠 사용 불가능
  - EntityManager.detach()
  - EntityManager.clear()
  - EntityManager.close()
- 정리
