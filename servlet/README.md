### 강의명: 스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술
### 출처: 인프런
### 범위: 섹션1 ~ 섹션5
### 강사: 김영한
</br>

## 🗂️ 목차
### 👉 Section 1
- 웹 서버, 웹 어플리케이션 서버
  - 웹 서버(Web Server
    - HTTP 기반 동작
    - 정적 리소스
    - ex) Apache, NginX
  - 웹 어플리케이션 서버(WAS: Web Application Server)
    - 웹 서버 기능 + 정적 리소스
    - 프로그램 코드로 어플리케이션 로직 수행
    - ex) Tomcat, Undertow
  - 웹 서버와 웹 어플리케이션 차이
    - 둘의 경계는 모호하기 때문에 WAS는 어플리케이션 코드를 실행하는데 특화 되어있다고 생각
  - 웹 시스템 구성 - WAS, DB
    - WAS만 이용해도 시스템을 구성할 수 있다 <img width="1027" alt="스크린샷 2024-01-28 오후 7 51 02" src="https://github.com/nashs789/spring-study/assets/59809278/c6d23bda-7cf8-4e77-a61b-104ab028cffd">
    - 하지만 위 그림처럼 구성하게 된다면 WAS가 담당하는 작업이 많아지고, 장애 발생시 대처가 되지 않기 때문에 아래처럼 구성 <img width="1245" alt="스크린샷 2024-01-28 오후 7 54 38" src="https://github.com/nashs789/spring-study/assets/59809278/f4eb0249-11f1-47e0-ba1c-5093f5589342">
- 서블릿(Servlet)
  - HTTP 요청시 flow
    - WAS는 Request, Response 객체를 생성 -> 서블릿 객체 호출 -> Request 객체의 정보를 사용해서 비지니스 로직 -> 만들어준 Response 객체에 응답 정보 -> WAS는 Response 객체에 담긴 정보 기반으로 HTTP 응답 생성
  - WAS가 Servlet Container에 Servlet 객체를 생성, 초기화, 호출, 생명주기 관리를 담당
  - Servelet 객체는 Singleton 객체로 관리
  - WAS는 동시 요청을 위한 Multi-Thread 지원
- 동시 요청 - 멀티 쓰레드
  - 서블릿은 누가 호출 하는가? -> Thread가
  - 장점:
    - 동시 요청 처리
    - CPU 허용 범위까지 처리가능
    - 다른 쓰레드의 작업과 상관 없이 동작
  - 단점:
    - 생성 비용 ↑
    - Context Switching
    - 너무 많이 생성하면 CPU, 메모리 임계점을 넘어갈 위험
  - 단점 극복:
    - Thread Pool에 사전에 생성해두고 요청 받으면 주고, 요청이 처리되면 반환 받는다
- HTML, HTTP API, CSR, SSR
  - HTML
    - 고정된 HTML, CSS, JS, 이미지 (정적 리소스)
    - WAS에서 로직 실행 후 HTML을 동적으로 생성 (동적 리소스)
  - HTTP API
    - HTML이 아닌 데이터 전달 ex) JSON
    - 다양한 시스템에서 호출 (웹뿐만 아니라 다른 시스템 포함)
      - Server to Server
      - Web Client to Server
      - App Client to Server
  - SSR(Server Side Rendering)
    - 동적으로 생성된 HTML을 응답
    - ex) JSP, Thymeleaf
  - CSR(Client Side Rendering)
    - HTML 결과를 JS를 사용해서 웹 브라우저에서 동적으로 HTML을 생성
    - ex) React, Vue
- 자바 백엔드 웹 기술 역사
  - 서블릿(1997)
  - JSP(1999)
  - 서블릿 + JSP 조합의 MVC 패턴ㅇ
  - '@' 기반의 스프링 MVC
  - 스프링 부트

### 👉 Section 2
- 프로젝트 생성
  - JSP 사용할 때 War 사용해야함
  - Dependency
    - Spring Web
    - lombok
  - Build
    - Gradle -> IntelliJ
  - Annotation Processors
    - Enable Annotation Processing 체크
- Hello 서블릿
  - Servlet 생성 및 테스트
  - HTTP Message 생성 및 헤더 정보 확인 후 응답
- HttpServletRequest - 개요
  - Servlet은 HTTP 요청 메세지를 파싱 해서 객체로 만들어준다 (HttpServletRequest, HttpServletResponse)
- HttpServletRequest -기본 사용법
- HTTP 요청 데이터 - 개요
- HTTP 요청 데이터 - GET 쿼리 파라미터
  - 검색, 필터, 페이징등에 사용되는 방식
  - url뒤에 '?'로 시작하며 파라미터는 '&'로 구분한다
  - 쿼리 스트링의 필드명이 같으면 처음 값이 우선순의를 갖기 때문에 getParameterValues() 를 통해서 조회 가능
    - 중복된 이름을 쓸 일 없음
- HTTP 요청 데이터 - POST HTML Form
  - 쿼리 스트링이나 Body에 payload로 데이터를 보내나 getParameter()를 통해서 데이터를 받을 수 있음
- HTTP 요청 데이터 - API 메시지 바디 - 단순 텍스트
  - HttpBody 데이터를 InputStream으로 받아서 문자열로 출력 까지
    - 인코딩 방식 맞춰주기
- HTTP 요청 데이터 - API 메시지 바디 - JSON
  - Json 라이브러리
- HttpServletResponse - 기본 사용법
  - HTTP 응답코드 지정
  - 헤더 생성
  - 바디 생성
  - 편의 기능
    - Content-Type
    - Cookie
    - Redirect
- HTTP 응답 데이터 - 단순 텍스트, HTML 
- HTTP 응답 데이터 - API JSON
- 정리

### 👉 Section 3 (실습 위주)
- 회원 관리 앱 애플리케이션 요구사항
- 서블릿으로 회원 관리 앱 애플리케이션 만들기
- JSP로 회원 관리 웹 애플리케이션 만들기
- MVC패턴 - 개요
  - '뷰'와 '비지니스 로직'의 라이프 사이클이 다른데 하나의 코드로 유지보수 하는게 좋지 않다.
  - JSP는 화면 렌더링에 최적화 되어있어, 기능에 맞는 업무 분배가 효과적이다.
  - Model의 데이터를 참고해서 View를 렌더링 하는 방식의 플로우를 갖음
- MVC패턴 - 적용
  - WEB-INF 하위 자원은 서블릿을 거쳐서 호출될 수 있음 (브라우저에서 url만 치고 요청하면 불가능)
  - RequestDispatcher에서 forward 하는 것과 redirect 에 대해서
    - redirect는 서버에서 전해주는 url로 이동하는 것이다.(클라 -> 서버 -> 클라 -> 리다이렉트 -> 서버 -> 클라)
    - forward는 단순 요청을 처리(비동기라고 봐도 될듯)(클라 -> 서버 -> 클라)
  - Model에 attribute로 데이터를 저장 후 JSP에서 JSTL을 통해서 동적 렌더링 십습
- MVC패턴 - 한계
- 정리

### 👉 Section 4
- 프론트 컨트롤러 패턴 소개
  - 프론터 컨트롤러(서블릿)을 하나 두어 클라이언트에게 요청을 받아 적합한 핸들러를 호출한다.
  - 하나의 서블릿을 제외하고 다른 서블릿은 필요하지 않게 되었다.
- 프론트 컨트롤러 도입 - v1
  - 다형성을 이용한 핸들러(구현체)들 URI랑 매핑해서 요청에 대한 응답 처리 플로우 실습
  - 번외) 구조를 변경할 때는 구조만 개서할 것(로직은 건들지 말고)
- View 분리 - v2
  - MyView 객체를 생성해서 forward 하는 중복 코드 한 곳에서 관리
- Model 추가 - v3
  - ModelView 객체를 생성해 불필요한 request, response 객체를 파라미터로 보내는 것 제거
  - viewResolver 역할의 메소드를 만들어 중복 코드 제거
- 단순하고 실용적인 컨트롤러 - v4
  - 모든 핸들러에서 Model 을 직접 생성 하던걸 FrontController 에서 생성해서 넘겨주도록 변경
- 유연한 컨트롤러1 - v5
  - 다형성을 이용한 여러 버전의 핸들러가 매핑되어 동작할 수 있도록 구현
- 유연한 컨트롤러2 - v6
- 정리

### 👉 Section 5
- 스프링 MVC 전체 구조
  - FrontController ➡ DispatcherServlet
  - handlerMappingMap ➡ HandlerMapping
  - MyHandlerAdapter ➡ HandlerAdapter
  - ModelView ➡ ModelAndView
  - viewResolver ➡ ViewResolver
  - MyView ➡ View
- 핸들러 매핑 핸들러 어댑터
    - HandlerMapping 우선순위
      - RequestMappingHandlerMapping
      - BeanNameUrlHandlerMapping
      - ....
    - HandlerAdapter 우선순위
      - RequestMappingHandlerAdapter
      - HttpRequestHandlerAdapter
      - SimpleControllerHandlerAdapter
      - ....
- 뷰 리졸버
  - properties 에 prefix, suffix 세팅
  - ViewResolver 우선순위
    - BeanNameViewResolver
    - InternalResourceViewResolver
    - ....
- 스프링 MVC - 시작하기
  - @Controller: componentScan + RequestMappingHandlerMapping 두 개의 대상이 된다.
    - @Component + @RequestMapping (class level)
- 스프링 MVC - 컨트롤러 통합
  - 하나의 컨트롤러에서 매핑하도록 구현
- 스프링 MVC - 실용적인 방식
  - HttpRequest 에서 파라미터 받는 방식 변경
    - @GetMapping
    - @PostMapping
  - Restful API 방식으로 변경
- 정리
