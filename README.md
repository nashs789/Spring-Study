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
- 스프링 JdbcTemplate
- JPA
- 스프링 데이터 JPA
