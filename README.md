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
### 👉 Section 5
- 앱 어플리케이션과 싱글톤
    - 멀티 스레드를 사용하면서 여러 호출로 동시에 여러개의 객체가 중복되서 생성 -> 메모리 낭비
- 싱글톤 패턴
    - 생성자를 private으로 설정해 new 키워드로 인스턴스 생성 방지
    - 문재점
        - 싱글톤 관리를 위한 코드
        - 구현체에 의존함 -> DIP 위반
        - 클라이언트가 구현체에 의존함 -> OCP 위반 가능성
        - private한 생성자 -> 자식 클래스 어려움
        - 안티패턴으로 불림
- 싱글톤 컨테이너
    - 특정 상황에 맞춰 싱글톤이 아닌 스코프에 맞춰서 생성하는 경우도 있음
- 싱글톤 방식의 주의점
    - statefull하지 않은 stateless로 설계
        - 필드에 의존하면 안됨
        - 필드를 수정할 수 있으면 안됨(readonly) 
- @Configuration과 싱글톤
- @Configuration과 바이트코드 조작 마법
    - SpringCGLIB 바이트코드 조작 라이브러리를 통해서 클래스 인스턴스 생성해 빈 컨테이너에 등록
### 👉 Section 6
- 컴포넌트 스캔과 의존관계 자동 주입 시작하기
    - @Component 
    - @ComponentScan
        - 클래스명을 빈 이름으로 등록하는데 맨 앞 글자만 소문자로 사용
    - @Autowired
        - 기본적으로 컨테이너에서 타입이 같은 빈을 찾아서 주입
        - 생성자 파라미터도 위와 같이 찾아서 주입
- 탐색 위치와 기본 스캔 대상
    - @ComponentScan에 경로를 지정
        - default: @ComponentScan이 붙은 클래스의 패키지를 시작 위치로 스캔 시작
- 필터
    - @Interface
    - @Filter
- 중복 등록과 충돌
    - BeanDefinitionStoreException -> 중복으로 인한 발생 에러
    - 자동 vs 자동 충돌
    - 수동 vs 자동 충돌
        - Spring Framework: Overriding bean definition -> 수동 빈이 우선순위를 갖고 오버라이딩
        - Spring Boot: overriding is disabled -> 오버라이딩이 default 값으로 false
            - 허용하고 싶다면 spring.main.allow-bean-definition-overriding=true 프로퍼티에 추가 해줘야함 
### 👉 Section 7
- 다양한 의존관계 주입 방법
    - 생성자 주입
        - 단 한 번 호출 -> 불변, 필수 의존관계
            - 필수: 빈 생성에 있어서 반드시 필요한 경우 ex) final로 설정해둔 주입 받도록 한 변수
        - 생성자가 1개라면 @Autowired 없어도 자동 등록
    - 수정자 주입(Setter)
        - 변경 가능성 있는 의존관계 -> required 옵션으로 선택 가능
    - 필드 주입
        - 안티패턴
        - 코드가 간결하지만 외부에서 변경이 불가능해 테스트가 어려움 -> Java 코드로는 테스트가 힘들거나 추가 코드가 필요함
        - 테스트 할 때는 필드 주입으로 해도 편하게 할 수 있음
    - 일반 메서드 주입
- 옵션 처리
    - required
    - @Nullable
    - Optional
- 생성자 주입을 선택해라!
    - 수정자와 다르게 필요 의존관계 세팅 하지 않으면 컴파일 단계에서 알 수 있기 때문에
    - final 키워드를 사용할 수 있음 -> 초기화 누락 방지
- 롬복과 최신 트렌드
    - Preference -> Compiler -> Annotation Processors
        - Enable annotation processing 체크
    - @Getter
    - @Setter
    - @RequiredArgsConstructor
    - Annotation Processor
        - 롬복이 사용해 컴파일 시점에 코드 생성
- 조회 빈이 2개 이상 - 문제
- @Autowired 필드명, @Qualifier, @Primary
    - @Autowired: 필드, 파라머티 이름으로 매칭되는 빈을 매핑
    - @Qualifier: 추가 구분자 but 빈 이름 변경 x
    - @Primary: 우선순위 지정
- 애노테이션 직접 만들기
- 조회한 빈이 모두 필요할 때 List, Map
    - 모든 빈을 Map, List로 얻어오는 방법
- 자동, 수동의 올바른 실무 운영 기준
    - 업무 로직 빈 vs 기술 지원 빈
        - 업무 로직: MVC의 유사한 패턴을 갖고 비지니스 로직이 포함됨 -> 자동
        - 기술 지원: 데이터베이스 연결, 공통 로그 처럼 업무를 지원하기 위한 하부 기술 -> 수동
        - 절대적인 것은 아니며 업무 로직도 요구 사항이 동적으로 변경되는 경우 사용할 수 있다. (다형성 적극 활용)
### 👉 Section 8
