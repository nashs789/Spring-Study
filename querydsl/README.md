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
### 📌 집합
### 📌 조인 - 기본 조인
### 📌 조인 - on
### 📌 조인 - 페치 조인
### 📌 서브 쿼리
### 📌 Case 문
### 📌 상수, 문자 더하기
