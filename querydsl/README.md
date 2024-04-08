### 강의명 : 실전 Querydsl
### 출처  : 인프런
### 강사: 김영한

</br>

# 🗂️ 목차

## 👉 Section 0 ~ 1
- 환경세팅

## 👉 Section 2

### 📌 예제 도메인 모델과 동작확인
JPA 엔티티 설정으로 DB 테이블 생성 후 EntityManager 를 통해서 데이터 insert 하고,
Querydsl 쿼리를 생성해서 조회하는 간단한 테스트 진행

기본적인 것들 소개하느라 많은 시간이 들어갔고, 결과는 조회 쿼리 하나 테스트 했다.

## 👉 Section 3

### 📌 시작 - JPQL vs Querydsl
- QueryBasicTest.java 파일에 두 가지 방식의 조회 방식 비교 소스 코드 작성

### 📌 기본 Q-Type 활용
- 인스턴스 생성해서 사용 vs 기본으로 생성된 인스턴스 사용

### 📌 검색 조건 쿼리
강의에서 제공해준 기본적인 조건들

```
member.username.eq("member1")               // username = 'member1'
member.username.ne("member1")               // username != 'member1'
member.username.eq("member1").not()         // username != 'member1'
member.username.isNotNull()                 // 이름이 is not null
member.age.in(10, 20)                       // age in (10,20)
member.age.notIn(10, 20)                    // age not in (10, 20)
member.age.between(10,30)                   // between 10, 30
member.age.goe(30)                          // age >= 30
member.age.gt(30)                           // age > 30
member.age.loe(30)                          // age <= 30
member.age.lt(30)                           // age < 30
member.username.like("member%")             // like 검색 
member.username.contains("member")          // like ‘%member%’ 검색 
member.username.startsWith("member")        // like ‘member%’ 검색
```

### 📌 결과 조회
- fetch(): 전체 조회
- fetchOne(): 단건 조회
- fetchFirst(): 최초 한 건 조회
- fetchResults(): 페이징 정보 포함 조회
- fetchCount(): count() 조회

### 📌 정렬
- orderBy()
    - .desc(): 내림차순
    - .asc(): 오름차순
    - .nullsLast(): null 값이 마지막으로
    - .nullsFirst(): null 값이 처음으로

### 📌 페이징
페이징 관련된 메소드 설명

- offset()
- limit()

위 메소드 말고도, 위에서 사용한 fetchResults() 를 사용할 여건이 된다면 페이징 정보를
얻어서 쿼리 만들어도 상관 없음

### 📌 집합
집계함수 관련된 메소드 소개

- count()
- sum()
- avg()
- max()
- min()
- groupBy()
- having()

### 📌 조인 - 기본 조인

- join()
- leftJoin()
- rightJoin()
- 세타 조인 (연관관계가 없는 테이블)

### 📌 조인 - on

```
Nothing Spectial
```

### 📌 조인 - 페치 조인

DB 에서 제공하는 기능은 아니고, 연관된 데이터를 전부 가지고 오는 방법

- fetchJoin()

### 📌 서브 쿼리

JPAExpression 을 사용해서 만들 수 있다.
from 절에서 서브 쿼리는 사용 불가능하다.
  
하지만!! from 절의 서브 쿼리는 대체가 가능한 부분임
1. 서브쿼리로 변경
2. 어플리케이션 레벨에서 쿼리를 분리
3. native SQL 작성

### 📌 Case 문

```
Nothing Spectial
```

### 📌 상수, 문자 더하기

- Expressions 을 사용하면 상수를 사용할 수 있음 
- java 는 타입이 있기 때문에 캐스팅이 필요
  - stringValue() - line436

## 👉 Section 3