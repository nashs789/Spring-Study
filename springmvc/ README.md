### 강의명: 스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술
### 출처: 인프런
### 범위: 섹션6 ~ 섹션8
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
- HTTP 응답 - HTTP API, 메시지 바디에 직접 입력
- HTTP 메시지 컨버터
- 요청 매핑 핸들러 어뎁터 구조
- 정리