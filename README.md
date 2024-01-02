### 강의명 : 스프링 핵심 원리 - 기본편
### 출처  : 인프런
### 강사: 김영한
</br>

## 📑 수강 목록
- [스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술](https://github.com/nashs789/hello-spring)
- 스프링 핵심 원리 - 기본편

## 🗂️ 목차
### 👉 Section 1
- 이야기 - 자바 진영의 추운 겨울과 스프링의 탄생
    - EJB(Enterprise Java Beans)
    - POJO(Plain Old Java Object)
- 스프링이란?
    - 핵심
        - Java 기반의 프레임워크 -> 객체 지향 언어 -> 객체 지향의 설계
            - 기존 EJB에 종속된 코드에서 벗어난다.  
    - 필수 
        - Spring Framework
        - Srping Boot
    - 선택
        - Spring Data
        - Spring Session
        - Spring Security
        - Spring Rest Docs
        - Spring Batch
        - Spring Cloud
        - [다른 프로젝트 보기](https://spring.io/projects/)
- 좋은 객체 지향 프로그래밍이란?
    - 추상화, 캡슐화, 상속, 다형성
    - 명령어 모음이 아닌 객체들의 모임으로 본다 -> 유연하고 변경에 용이
- 좋은 객체 지향의 설계의 5가지 원칙(SOLID)
    - SRP(Single Responsibility Principle)
    - OCP(Opee Closed Principle)
    - LSP(Liskov Substitution Principle)
    - ISP(Interface Segregation Principle)
    - DIP(Dependency Inversion Principle)
- 객체 지향 설계와 스프링
### 👉 Section 2
- 프로젝트 생성
    - Spring Boot Project 생성 
- 비즈니스 요구사항과 설계
- 회원 도메인 설계
- 회원 도메인 개발
- 회원 도메인 실행과 테스트
- 주문과 할인 도메인 설계
- 주문과 할인 도메인 개발
- 주문과 학인 도메인 실행과 테스트

⚙️ Java 코드만을 이용해서 Spring 없이 개발하는게 중점적인 섹션으로 다형성을 설명하기 위한 코드가 주였음
  
### 👉 Section 3
- 새로운 할인 정책 개발
- 새로운 할인 정책 적용과 문제점
    - OCP, DI의 미 준수
        - 클라이언트가 추상화가 아닌 구현에 의존하고 있기 때문에 수정이 불가피함 
- 관심사의 분리
    - 구현체 생성은 Config 파일에서
- AppConfig 리팩터링
    - 구성하는 부분과 사용 부분의 영역이 분리됨 -> 추상과 구현
- 새로운 구조와 할인 정책 적용
- 전체 흐름 정리
- 좋은 객체 지향 설계의 5가지 원칙 적용
- ioC, DI, 그리고 컨테이너
    - 라이브러리: 작성한 코드가 직접 제어의 흐름을 담당 -> JUnit
    - 프레임워크: 작성한 코드를 제어하고 실행을 담당 -> Jackson, Gson
    - 정적 클래스 의존 관계: 어플리케이션 실행 없이도 import만 보고 판단 가능 -> 클래스 다이어그램
    - 동적 객체 의존 관계: 어플리케이션 실행 시점에서 생성된 객체 인스턴스 참조가 연결된 관계 -> 객체 다이어그램
        - 런타임에 외부에서 구현 객체를 생성 및 클라이언트에 전달해 의존관계를 주입
        - 정적 다이어그램 변경 없이도(의존관계 변경x) -> 다형성
    - IoC 컨테이너(DI 컨테이너): AppConfig 처럼 객체 생성, 관리 그리고 의존관계 연결 역할
- 스프링으로 전환하기
    - @Configuration
        - @Bean이 붙은 메소드를 빈 컨테이너에 등록 -> 등록된 빈 객체를 스프링 빈이라고 부른다 
    - @Bean
        - method 이름을 빈으로 사용
### 👉 Section 4
- 스프링 컨테이너 생성
    - 컨테이너 생성 -> 빈 등록 -> 빈 의존관계 설정 준비 -> 빈 의존관계 설정 이라는 일련과 과정이 있음
    - 빈 생성과 의존 관계 설정 과정이 나누어져 있음
    - Singleton Container
- 컨테이너에 등록된 모든 빈 조회
- 스프링 빈 조회 - 기본
    - NoSuchBeanDefinitionException
- 스프링 빈 조회 - 동일한 타입이 둘 이상
    - NoUniqueBeanDefinitionException -> 빈의 구현체가 중복되는 경우
- 스프링 빈 조회 - 상속 관계
    - 자식 클래스는 전부 끌려나온다.
    - NoUniqueBeanDefinitionException -> 빈의 구현체가 여러개 있는 경우
- BeanFactorty와 ApplicationContext
    - BeanFactorty <- ApplicationContext <- AnnotationConfigApplicationContext
    - ApplicationContext는 Bean 관리를 위한 BeanFactorty 뿐만 아니라 여거가지 인터페이스를 상속 받고 있음
        - EnvironmentCapable: 환경 변수(로컬, 개발, 운영등 구분)
        - ListableBeanFactory
        - HierarchicalBeanFactory
        - MessageSource: 다국어 기능
        - ApplicationEventPublisher: 이벤트 발생 및 구독 모델
        - ResourcePatternResolver: 파일, 클래스, 외부 등에서 리소스 편리하게 조회
- 다양한 설정 형식 지원 - 자바 코드, XML
    - GenericApplicationContext
- 스프링 빈 설정 메타 정보 - BeanDefinition
    - BeanDefinition
        - 추상화를 통해서 빈을 등록하기 때문에 xml, class, annotation 전부 지원 가능하다.
        - 각 구현체는 BeanReader를 통해서 메타 정보를 만들고 BeanDefinition에 저장
