### 강의명 : 자바 ORM 표준 JPA 프로그래밍 - 기본편
### 출처  : 인프런
### 강사: 김영한
</br>

## 🗂️ 목차
### 👉 Section 1
- ⚙︎ ︎SQL 중심적인 개발의 문제점
  - 객체 지향과 관계형 데이터베이스의 패러다임 (상속 vs 연관관계)
  - 자바 콜렉션에서 데이터를 관리 한다면?
    - 객체 그래프 탐색
    - 엔티티 신뢰성 -> null 값에 대해
    - 비교 연산
      - SQL 실행 후 인스턴스 생성시 필드 정보는 매번 달라짐 하지만 콜렉션으로 관리시 같음
- ⚙︎ JPA 소개 (Java Persistence API)
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

<hr/>
 
### 👉 Section 2
- ⚙︎ Hello JPA - 프로젝트 생성
  - Spring boot + maven 프로젝트 생성
  - H2 데이터베이스 설정 세팅
- ⚙︎ Hello JPA - 애플리케이션 개발
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

<hr/>
 
### 👉 Section 3
- ⚙︎ 영속성 컨텍스트
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
- ⚙︎ 플러시
  - 영속성 컨텍스트 변경사항 DB에 반영
  - Dirty Checking -> write-behind SQL storage 저장 -> write-behind
  - 플러쉬 방법 -> 왜 필요?: 쿼리를 먼저 보기 위해서(테스트)
    - flush() 명시적 호출
    - commit
    - JPQL 실행
      - raw 쿼리를 사용하기 때문에 persist로 캐시에 저장해도 DB에 없기 떄문에 조회 불가능해서
  - 캐시를 지우는게 아님(쓰기 지연 SQL 저장소의 쿼리를 DB에 반영)
- ⚙︎ 준영속 상태
  - persist 호출 없이 find를 하게 된다면 캐시에 존재하지 않기 때문에 DB 조회 후 캐시에 저장
  - 준영속이란? -> 영속성 컨텍스트에서 분리한다 -> 더 이상 영속성 컨텍스트가 제공하는 컨텐츠 사용 불가능
  - EntityManager.detach()
  - EntityManager.clear()
  - EntityManager.close()
- ⚙︎ 정리

<hr/>

### 👉 Section 4
- ⚙︎ 객체와 테이블 매핑
  - 매핑
    - 갹체와 태아불: @Entity, @Table
      - @Entity: JPA가 관리하는 클래스
        - 기본 생성자 필수
        - final, enum, interfacem, inner 클래스 x
        - 저장할 필드 final 예약어 x
      - @Table: 엔티티와 매핑할 테이블 지정
        - name 속성을 통해서 지정 가능
    - 필드와 컬럼: @Column
    - 기본 키: @Id
    - 연관관계: @ManyToOne, @JoinColumn
- ⚙︎ 데이터베이스 스키마 자동 생성
  - 어플리케이션에서 맺은 관계에 따라사 DDL을 자동 생성
  - hibernate.hbm2ddl.auto -> dialect 설정에 따른 문법을 따른다
    - create: drop -> create
    - create-drop: create -> drop
    - update: 변경점만 반영 ex) alter를 통해서 변경점 ddl 작성 (지우는건 지원x)
    - validate: 엔티티와 테이블 매핑 상태 확인 (정상적으로 되었는지)
    - none: 기능 없음(사용x)
- ⚙︎ 필드와 컬럼 매핑
  - @Column
    - name: 필드와 매핑할 테이블의 컬럼 이름
    - insertable: 등록 가능 여부 (default: true)
    - updatable: 수정 가능 여부 (default: true)
    - length: 컬럼 길이
    - nullable: null 제약 조건
    - unique: unique constraints 제약 조건
    - columnDefinition: ddl 직접 작성
    - precison, sacle: BigDecimal, BigInterger 타입에서 정밀한 소수 다룰 때
  - @Enumerated 
    - EnumType.String: enum 클래스의 문자열 default(EnumType.ORDINAL - enum 클래스에서 선언된 순서로 저장)
  - @Temporal
    - 최신 버전이면 LocalDateTime 클래스로 만들면 자동으로 Timestamp로 지정된다
  - @Lob
    - 문자: CLOB
    - 바이트: BLOB
  - @Transient
    - 컬럼 매핑에서 제외
- ⚙︎ 기본 키 매핑
  - 어떻게?
    - @Id: 개발자가 직접 세팅
    - @GeneratedValue:자동 채번
      - 필드 strategy?
        - IDENTITY: 기본키 생성을 데이터베이스에 위임
          - DB까지 넘어가야지만 PK 값을 알 수 있기 때문에 영속성 컨텍스트 내에서는 PK여부를 모름
            - em.persist를 호출 해서 DB에 먼저 저장해서 PK 값을 영속성 컨텍스트에서 갖도록 해줌 
        - SEQUENCE
          - @SequenceGenerate로 sequence 이름 설정 가능
          - Sequence Number는 DB가 관리하기 때문에 영속성 컨텍스트에서는 초기에 정보가 없어서 DB에서 받아와야함(DB sequence조회 -> 영속성 컨텍스트 저장)
            - Identity는 Insert 후 영속성 컨텍스트에 저장하는데 비해서 Sequence는 Sequence Number를 얻어온 후 영속성 컨텍스트에 저장 했다가 persist를 호출하기 때문에 통신에 더 많은 비용이 들어간다는 고민이 있음
              - 네트워크 통신 최적화를 위해서 미리 가져와서 메모리에 가져와서 Sequence를 채번해서 사용한다 (default: 50)
        - Table: 별도의 테이블을 구성해서 관리하는 방법
        - AUTO
- ⚙︎ 실전 예제 1 - 요구사항 분석과 기본 매핑

<hr/>

### 👉 Section 5
- ⚙︎ 단방향 연관관계
  - 객체와 테이블의 연관관계의 차이
  - 객체는 참조, 테이블은 외래 키
  - 용어
    - 방향(Direction): 단방향, 양방향
    - 다중성(Multiplicity): 다대일(N:1), 일대다(1:N), 일대일(1:1), 다대다(N:M)의 이해
    - 연관관계의 주인(Owner): 객체 양방향 연관관계는 관리 팔요
  - @ManyToOne
    - fetch = FetchType
      - EAGER(default)
      - LAZY: 지연 로딩
  - 객체를 테이블 중심으로 모델링 할 경우 협력관계가 힘듬
    - ex) Member 를 select 하고 TEAM_ID를 얻어와 Team 을 select
- ⚙︎ 양방향 연관관계와 연관관계의 주인 1 - 기본
  - 객체는 단방향 연관관계가 좋지만 DB 테이블 관계랑 같이 양방향 설정이 가능하다
    - @OneToMany(mappedBy = "변수명")
    - 객체는 사실 양뱡향 관계가 아닌 단방향 2개이다.
      - ex) A -> B, B -> A
    - 테이블은 FK 하나로 양방향 관계를 갖을 수 있다.
    - Team 과 Member 둘 다 FK 에 영향을 줄 수 있기 때문에 Owner 를 정해서 관리해야 한다.(양방향일 떄)
      - Owner 는 등록, 수정 -> 외래키 관리 가능
      - Owner 가 아니라면 Read 만 가능
- ⚙︎ 양방향 연관관계와 연관관계의 주인 2 - 주의점, 정리
  - 많이 나오는 실수1) 연관관계의 주인에 값을 입력하지 않음
  - 많이 나오는 실수2) A(Owner), B 객체가 있다고 하면 B 의 콜렉션 객체는 영속성 컨텍스트에 존재하지 않음 -> DB 조회로 가져와야함
    - 연관관계 '편의' 메소드로 따로 빼는 방법 추천: A에 B를 세팅하고, B에 A를 세팅
  - 많이 나오는 실수3) 양방향 매핑 무한 루프
    - toString, lombok, json LIB: 양쪽 toString 메소드에서 순환참조
  - 연관관계의 주인은 외래 키의 위치를 기준으로 정하자
- ⚙︎ 실전 예제 2 - 연관관계 매핑 시작

### 👉 Section 6
- ⚙︎ 다대일 [N:1]
  - RDB 의 경우 '다' 사이드에 설계상 FK 가 위치한다.
- ⚙︎ 일대다 [1:N]
  - '일' 이 연관관계의 Owner
  - @JoinColumn 을 사용하지 않으면 중계 테이블을 생성하기 때문에 넣어줄 것
  - 일대다 단방향은 엔티티를 관리하는 FK가 다른 테이블에 있기 때문에 혼란스럽다.
  - 추가의 update 쿼리가 항상 따른다.
  - 양방향 조회 관계를 만들 수 있다.
    - @JoinColumn(name = "", inserttable = false, updatetable = false)
    - 위 처럼 조회만 가능하도록 설정(읽기 전용)
- ⚙︎ 일대일 [1:1]
  - 양 테이블중 어느곳에든 FK 를 넣어도 무관하다.
  - FK 에 unique 제약 조건 권장
  - 주 테이블, 대상 테이블 어디에 키를 두냐에 따라서 갈림
  - 장점
    - 주 테이블만 조회해도 대상 테이블 확인 가능
    - 일대일에서 일대다로 변경시 테이블 구조 유지
  단점
    - 값이 없으면 FK nullable
    - 지연 로딩 설정에도 즉시 로딩
- ⚙︎ 다대다 [N:M]
  - 실무에서는 요구사항을 다 담아낼 수 없기 때문에 중계 테이블을 엔티티로 만들어서 OneToMany 혹은 ManyToOne으로 변경한다.
- ⚙︎ 실전 예제 3 - 다양한 연관관계 매핑

### 👉 Section 7
- ⚙︎ 상속관계 매핑
  - @Inheritance
    - 속성 strategy
      - JOINED
        - 테이블을 분할해서 생성
      - SINGLE_TABLE
        - 하나의 테이블에 모든 컬럼을 만들고, 데이터가 안들어가는건 null로 채운다.
        - DTYPE 컬럼이 생성되서 구분 가능하도록한다.
      - TABLE_PER_CLASS(abstract class)
        - 슈퍼 클래스의 속성을 서브 클래스가 중복되서 갖도록 허용하는 전략(슈퍼 클래스 테이블 생성 x)
  - @DiscriminatorColumn 상속받은 엔티티명을 넣게됨
  - @DiscriminatorValue 을 통해서 DTYPE 대신 컬럼명 변경 가능하다.
- ⚙︎ Mapped Superclass - 매핑 정보 상속
  - 공통 매핑 정보가 필요할 때 사용
  - 상속 관계 x, 엔티티 x, 테이블과 매핑 x
    - 조회, 검색 불가능
- ⚙︎ 실전 예제 4 - 상속관계 매핑

### 👉 Section 8
- ⚙︎ 프록시
  - find(): DB 에서 데이터를 찾아옴
  - getReference(): DB 가 아닌 가짜 엔티티 조회(프록시)
  - 프록시는 실제 엔티티를 상속 받아서 만들어진다. (실제 클래스와 모양이 같음)
  - 프록시는 속성으로 Entity target 을 가지고 있고, 초기에는 null 이 세팅되어 있음
    1. 조회 요청시 target 이 비어있음
    2. 영속성 컨텍스트가 DB 조회
    3. 엔티티 객체 생성 후 target 이 참조 하도록 변경
  - 프록시 객체는 처음 한 번만 초기화
  - 프록시 객체가 실제 엔티티 객체로 변경되는건 아님(HibernateProxy 클래스 그대로 유지)
    - == 동등 비교하면 같지 않음
    - instanceof 로 비교 해야함
  - 영속성 컨텍스트에 이미 찾고자 하는 엔티티가 있으면 getReference() 호출해도 실제 엔티티 반환
  - 영속성 컨텍스트에서 더 이상 관리하지 않는 상태일 때 프록시 초기화 불가능
    - detach(엔티티)
    - close()
  - PersistenceUnitUtil 의 isLoader() 를 통해서 프록시 초기화 여부를 확인 가능
  - getClass() 를 통해서 프록시 클래스 확인
  - Hibernate.initialize() 를 통해서 강제 초기화 가능
- ⚙︎ 즉시 로딩과 지연 로딩
  - fetch = FetchType.LAZY: 프록시 객체 조회(Lazy Loading)
    - @OneToMany, @ManyToMany 는 default 가 LAZY
  - fetch = FetchType.EAGER: 한 번에 같이 가져오도록 초기화 하는 옵션
    - JPQL 은 N + 1 문제를 발생 시킨다.(fetch join 으로 해결 가능)
      - raw 쿼리가 실행된 후 가지고 있는 엔티티중에서 EAGER 로 설정되어 있는 속성이 있다면 그 수 만큼 실행됨
        - ex) Member 조회 햇는데 10건의 데이터가 있고, Member 가 속한 TEAM 에 대해서 10건의 데이터 조회를 한다.
    - @ManyToOne, @OneToOne 는 default 가 EAGER
- ⚙︎ 영속성 전이: CASCADE와 고아 객체
  - 엔티티를 영속성 상태로 만들 대 연관된 엔티티도 같이 영속성 상태로 만드는 것
  - cascade 옵션에 CascadeType.ALL 추가 해주면됨
  - 단일 엔티티에 종속적일 때 lifecycle 같아서 사용하면 편함
  - 옵션
    - ALL: 저장, 삭제 lifecycle 맞춰서 사용할 때
    - PERSIST: 영속
    - REMOVE: 삭제
    - MERGE, REFRESH, DETACH: 사용 안함
  - 고아 객체: 부모 엔티티와 연관관계가 끊어진 자식 엔티티를 자동으로 삭제
    - orphanRemoval = true
    - 참조하는 곳이 하나일 때 사용할 것
    - ex) 부모에서 자식을 지우면 자식이 DB 에서 삭제됨(양방향 가정)
- ⚙︎ 실전 예제 - 5.연관관계 관리

### 👉 Section 9
- ⚙︎ 기본 값 타입
  - 엔티티 타입: 내부 속성의 값이 변경되도 식별자로 인식 가능
    - ex) 회원의 키를 160 -> 170 으로 변경해도 회원 식별 가능
  - 값 타입
    - 기본 값 타입(Primitive Type): int, String 처럼 단순한 값으로 사용하는 자바 기본 타입이나 객체
      - 생명 주기를 엔티티와 공유한다.
      - 값 타입은 공유하면 안됨(side effect 발생)
- ⚙︎ 임베디드 타입(Embedded Type)
  - 내 생각: 엔티티로 생성은 안하고, 객체를 생성해서 객체지향적 코드를 짤 수 있도록
  - 특징
    - 재사용
    - 높은 응집도
    - 기본 생성자 필수
  - @Embedded, @Embeddable
  - 임베디드 타입을 포함한 모든 값 타입은 값 타입을 소유한 엔티티에 생명주기에 의존함
  - 콜렉션 값 타입(Collection Value Type)
  - 한 엔티티 내에서 같은 타입의 컬럼을 두 개 쓴다면?
    - @AttributeOverrides 를 사용해서 컬럼 명 속성을 재정의
- ⚙︎ 값 타입과 불변 객체
  - 임베디드 타입을 여러 엔티티에서 공유하면 위험하다(side effect 찾기 매우 어려움)
    - 이런 경우 값을 복사해서 사용해야 한다.
  - 객체 타입은 참조 값을 직접 대입하는 것을 막을 방법이 없다.
    - 컴파일 레벨에서 막을 방법도 존재하지 않음
  - 위의 문제들을 해결하기 위해서 객체 타입을 수정할 수 없게 만든다.
    - 값 타입을 불변 객체(Immutable Object)로 설계
    - 어떻게?
      - 값을 생성할 때 제외하고 변경이 불가능하도록 만든다.
      - Setter 를 지운다.
      - 그럼 값을 어떻게 변경하는가? -> 새로운 객체 생성
- ⚙︎ 값 타입의 비교
  - 값 타입은 동등성(Equivalence) 을 비교 해야 하는데 equals() 를 재정의
- ⚙︎ 값 타입 컬렉션
- ⚙︎ 실전 예제 6 - 값 타입 매핑