grid system이 어떻게 가능할까?

이것은 미디어 쿼리덕분!





미디어 쿼리

반응형 디자인의 핵심 구성 요소로 CSS 문법에 해당한다.

CSS 내부에서 화면을 동적으로 나누고 관리하는 기능

미디어 쿼리는 좋은 기능이지만 문법이 어렵다 

Bootstrap의 grid system은 미디어쿼리를 이용해서 작성되었고 우리가 편하게 미디어쿼리의 기능을 사용할 수 있게 도와주는 것이다.





애니메이션

CSS속성을 이용해서 해당 요소를 이동하거나 회전하거나 사라지게 만드는 기능

```HTML
<style>
    div {
        animation:move;
        animation-duration: 3s;
    }
    
    
    
    /* 해당 요소 이동(margin을 변경함으로서)*/
    @keyframes move{
        /*여기서 %는 앞에서 설정한 3s에 대한 시간개념: 0%는 0초시점 100%는 3초시점 */
        0% {
            margin-left: 0px;   
        }
        100% {
			margin-left: 200px;
        }    
    }
    
    /* 색깔 조정 */
    @keyframes color-change{
        from {
            background-color: brown;
        }
        to {
            background-color : red;
        }
    }


</style>

```



animated.css 라이브러리 

animate.css 사이트에서 다양한 css animation을 볼 수 있다.

다양한 애니메이션 기능을 클래스로 제공한다.

animated.css를 사용해서 css animation 기능을 쓰자(직접 제작하는 것보다 이게 더 좋다)





애니메이션 사용 주의사항

큰요소(큰태그)에는 사용하지 말자







폰트

구글폰트를 사용하자

select this style를 클릭하면html파일에 바로 쓸 수 있는 link 태그가 생긴다.

head에 넣는다.

`<style>`태그에 font-family: 폰트이름을 쓰면 된다.





아이콘

bootstrap사이트의 icon카테고리에서 cdn 주소를 가져와서 사용한다.

fontawesome에서는 좀 더 많은 icon을 제공한다.

`<i class="아이콘이름"></i>`

 





form의 배우지 않은 속성

aria-describedby: 읽기 어려운 사람이 이것을 읽을 수 있도록 도와주는 것



접근성: 장애가 있는 사람들도 쉽게 사용할 수 있는지에 대한 성질(서비스 평가 요소)

nuli라는 사이트에서 접근성에 대해 배울 수 있따.





---

오늘 프로젝트

1.

2.

3.





navigation bar를 만든다.

* 상단에 고정하기 위해서 fixed-top을 사용한다.
* 로고이미지는 클릭이가능한 링크다 (a)로 해야 한다.
* 네비게이션 바 내부의 네비게이션 리스트는 ul과 li 태그를 사용한다.
  * nav라는 클래스를 사용해서 만들수있다. 
* 네비게이션리스트를 오른쪽에 배치한다. 
  * 가장 사용하기 쉬운 방법은 d-flex

* 768 미만일 경우 네비게이션리스트가 햄버거 아이콘(작대기세개)로 교체된다.



2번



header는 이미지 자동 전환 → 캐루젤

576미만에는 한줄에 하나씩

576이상은 2개 이상





3번

게시판 aside

왼쪽은 리스트

가운데는 표(table)  → 부트스트랩에 table을 사용하는 문서를 보면 좋다.



