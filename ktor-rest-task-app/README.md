# ktor-rest-task-app

This project was created using the [Ktor Project Generator](https://start.ktor.io).

Here are some useful links to get you started:


## Docs study - 공부한 내용 정리
<details open>
    <summary>Content Negotiation</summary>
Content Negotiation 플러그인은 클라이언트가 렌더링을 할 수 있는 content의 타입을 보고,
현재 서비스가 제공하는 content 타입과 매치시켜준다.  그래서 Content Negotiation이다.

HTTP에서 클라이언트는 Accept header를 통해 렌더링할 수 있는 content 타입을 알려준다.
이 값은 한 개의 타입일 수도 있고 여러 개의 타입일 수도 있다.
브라우저에서 개발자 도구를 열어 쉽게 확인할 수 있다.
`*/*` 표시는 HTML, XML, 이미지 뿐만 아니라 다른 어떤 타입들도 다 accept 가능하다.

Content Negotiation plugin은 브라우저에 다시 되돌려줄 데이터 포맷을 찾아야 한다.
브라우저의 요청같은 경우, ContentNegotiation plugin은 JSON만 반환할 수 있는 것을 알고 있다. (Serialization.kt 파일 참조)
브라우저는 받은 내용을 화면에 표현하면 된다.

(이해하기로, 서버 측에서 ContentNegotiation plugin을 통해 Json 형식으로 직렬화를 하고 이를 브라우저에게 보내면, 브라우저는 단순히 display만 하면 된다는 뜻 같다.)

</details>

