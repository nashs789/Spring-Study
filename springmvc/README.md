### 강의명: 스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술
### 출처: 인프런
### 범위: 섹션6 ~ 섹션7
### 강사: 김영한
</br>

### 👉 Section 6
- 프로젝트 생성
- 로킹 간단히 알아보기
  - @RestController
    - 반환 값이 String 인경우 '뷰'를 찾아서 렌더링 하는데, 어노테이션 선언시 body에 값을 넣고 응답한다.
  - 코드를 건들지 않고 설정으로만 조절 가능하다는 장점이 있다. 
- 요청 매핑
  - url 매핑
  - HTTP method에 따른 매핑
  - @PathVariable
  - content type에 따른 매핑
- 요청 매핑 - API 예시
  - Restful 한 API 설계 예시 실습
- HTTP 요청 - 기본, 헤더 조회
  - spring 에서 제공하는 헤더 정보 보는 방법 실습
    - HttpServletRequest 에서 꺼내는 방식이 아닌 더 간단한 방법을 제공
- HTTP 요청 파라미터 - 쿼리 파라미터, HTML, Form
- HTTP 요청 파라미터 - @RequestParam
  쿼리 스트링 @RequestParam 을 통해서 받음
  - 기본형의 경우 required 가 false 여도 null 은 예외를 발생시킴
    - Wrapper Class 사용할 것
  - defaultValue 를 설정하면 required 는 사용하지 않아도 된다.(= 항상 값 있음)
  - 생략해도 무관하게 동작한다.
- HTTP 요청 파라미터 - @ModelAttribute
  - @ModelAttribute
    - 객체의 프로퍼티를 찾고, setter를 호출해 데이터 바인딩을 해준다.
    - 생략해도 무관하게 동작한다.
  - @RequestParam, @ModelAttribute 둘 다 생략 가능한데 무슨 기준으로 판단할까?
    - 기본 타입과 나머지 타입 두 가지고 구분해서 판단한다.
- HTTP 요청 메시지 - 단순 텍스트
  - HttpEntity 를 통해서 header, body 정보 조회 가능
    - HttpEntity 를 상속받는 밑의 두 클래스
    - RequestEntity
    - ResponseEntity
  - Entity 클래스 사용하지 않고 어노테이션으로 해결 가능
    - @RequestBody
    - @ResponseBody
  - HttpMessageConverter
- HTTP 요청 메시지 - JSON
  - @RequestBody 는 생략 불가능
    - 생략시 @ModelAttribute 가 default 값이고, 쿼리 스트링에 따라서 null, 0 값 등으로 초기화됨 
  - content-type 이 application/json 로 설정되어 있어야 JSON 요청에 해당하는 Converter 가 동 
- 응답 - 정적 리소스, 뷰 템플릿
  - 정적 리소스: 브라우저에 정적인 HTML, css 제공할 때
    - /static, /public, /resources, /META-INF/resources 경로에 정적 리소스를 제공한다.
  - 뷰 템플릿: 동적인 HTML을 제공할 때
  - thymeleaf 사용할 때 기본 설(application.properties)
    - spring.thymeleaf.prefix=classpath:/templates/
    - spring.thymeleaf.suffix=.html
- HTTP 응답 - HTTP API, 메시지 바디에 직접 입력
  - 응답
    - Writer
    - ResponseEntity
    - String
    - Dto
  - @RestController = @Controller + @ResponseBody
- HTTP 메시지 컨버터
  - return String: StringHttpMessageConverter
  - return 객체: MappingJackson2HttpMessageConverter
  - return byte: ByteArrayHttpMessageConverter
  - return 다른 타입 : HttpMessageConverter
- 요청 매핑 핸들러 어뎁터 구조
  - Converter 동작은 어디에서 할까? (ArgumentResolver, ReturnValueHandler 가 사용해서 파라미터를 생성한다.)
    - RequestMappingHandlerAdapter 에서 동작한다.
    - HandlerMethodArgumentResolver 를 호출하고, 파라미터의 값을 생성하고, 컨트롤러를 호출한다.
      - HttpServlet, HttpEntity, Model, @RequestParam, @ModelAttribute, @RequestBody 파라미터를 유연하게 처리 가능
      - 30 개 이상의 ArgumentResolver 를 제공한다.
    - HandlerMethodReturnValueHandler 가 응답 값을 변환하고 처리한다. (ArgumentResolver 랑 역할을 동일함)
- 정리

### 👉 Section 7
- 요구사항 분석
- 상품 도메인 개발
- 상품 서비스 HTML
- 상품 목록 - 타임리프
  - URL 링크 표현식: @{...}
  - 리터럴 대체: |...|
  - 반복 출력: th:each
  - 변수 표현식: ${...}
    - 모델에 포함된 값이나 타임리프 변수로 선언한
  - th:text
    - 내용의 값을 text 값으로 변경한다.
  - URL 링크 표현식2
    - ex1) th:href="@{/basic/items/{itemId}(itemId=${item.id})}" - 경로 변수 스타일
    - ex2) th:href="@{/basic/items/{itemId}(itemId=${item.id}, query='test')}" - 쿼리 스트링 생성
    - ex3) th:href="@{|/basic/items/${item.id}|}" - ex1 과 같은 경로 표현식
- 상품 상세
- 상품 등록 폼
  - th:action: 값이 비어있다면 현재 url 을 그대로 사용
- 상품 등록 처리 - @ModelAttribute
- 상품 수정
- PRG Post/Redirect/Get
- RedirectAttributes
- 정리