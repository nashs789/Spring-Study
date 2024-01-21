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
- Hello JPA - 애플리케이션 개발
