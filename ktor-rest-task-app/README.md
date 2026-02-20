# ktor-rest-task-app

This project was created using the [Ktor Project Generator](https://start.ktor.io).

Here are some useful links to get you started:


## Docs study - 공부한 내용 정리
<details>
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

<details>
    <summary>JsonPath 라이브러리를 활용한 JSON 구조 검증 - Unit test</summary>

- 서버는 JSON을 직접 핸들링하는 것이 아니기 때문에 JSON 구조에 대한 가정에 확신을 가질 수 없다.
  여기서 말하는 가정이란 아래와 같은 것들이다.
    - values는 실제로 “object”가 사용되는데 “array(배열)”에 저장된다.
    - Properties(특성)는 숫자로 저장되는데, 실제로는 문자열이다.
    - Members는 선언된 순서대로 직렬화되지만 그렇지 않은 경우도 있다.

- 서비스가 여러 clients를 대상으로 제공된다면, JSON 구조를 가지고 있단 확신(confidence)이 매우 중요하다.
  이를 달성하기 위해, Ktor client를 사용해 서버로부터 text를 얻고 JSONPath 라이브러리를 사용해 해당 content를 분석한다.

```kotlin
 
 val result: List<String> = jsonDoc.read("$[*].name")
 jsonDoc.read("$[?(@.priority == '$priority')].name")

```
- 위와 같은 쿼리를 사용하면 반환된 JSON에 대해 형식을 확인할 수 있다. 
  코드 리팩토링 및 서비스 재배포 시, 현재 프레임워크에서 역직렬화에 영향을 미치지 않더라도 직렬화 변경 사항이 식별된다. 
  이를 통해 공개적으로 사용 가능한 API를 안심하고 다시 게시할 수 있습니다.

  ⇒ (opinion) 직렬화를 진행할 때, 의도한 변경임을 나타낼 수 있고 개발자가 직접 조작하여 JSON 형식에 맞게끔 되는 것을 확인하는 데에 의의가 있는 것 같다.

</details>
