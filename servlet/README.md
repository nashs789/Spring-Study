### 강의명 : 스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술
### 출처  : 인프런
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
- HttpServletRequest - 개요
- HttpServletRequest - 기본 사용법
- HTTP 요청 데이터 - 개요
- HTTP 요청 데이터 - GET 쿼리 파라미터
- HTTP 요청 데이터 - POST HTML Form
- HTTP 요청 데이터 - API 메시지 바디 - 단순 텍스트
- HTTP 요청 데이터 - API 메시지 바디 - JSON
- HttpServletResponse - 기본 사용법
- HTTP 응답 데이터 - 단순 텍스트, HTML
- HTTP 응답 데이터 - API JSON
- 정리