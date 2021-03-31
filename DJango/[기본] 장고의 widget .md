# 장고의 Widget

출처: https://docs.djangoproject.com/en/3.1/ref/forms/widgets/

출처: https://bit.ly/2P1d6A7

​      



> 위젯이란?

장고의 폼은 자동적으로 입력란을 만들어주는 편리한 기능이 있지만 그 디자인은 형편없다. **장고 폼에서 사용하는 입력란의 디자인을 설정하는데 사용되는 것이 바로 `widget`**이다. 

앞서 설명했듯 기본적으로 장고의 폼은 자동적으로 입력란(`input`)을 만들어주는데 이 때 사용하는 것이 `widget`이다. 좀 더 자세히 설명하자면 장고의 폼에는 여러 `field`가 있고 각각 `field`에는 설정된 데이터 유형이 존재한다.

장고에서는 이러한 데이터 유형에 맞는 입력란(`input`)을 만들기 위해 미리 데이터 유형별로 만들어 놓은 입력란 틀인 `widget`을 불러와 사용한다.

즉, 기본적으로 장고 폼이 만들어내는 입력란은 각각의 `field`의 데이터 유형에 해당하는 `widget`을 불러와 만들어진 것이다. 

그리고 `field`에 들어가는 데이터 유형별 default `widget`이 존재하고 우리가 장고 폼을 사용해서 웹페이지에서의 입력폼을 만들었을 때 보이는 형태가 바로 이 default widget이다.

​      



> 모델과 장고폼(모델폼)과 위젯의 연결 흐름

**모델 필드**의 데이터 유형에 맞는 **폼의 필드**가 정해지고 

**폼의 필드**의 데이터 유형에 맞는 **위젯**이 결정된다. 

`Model Field ▶ Form Field ▶ Widget`

|   Model Field    |   Form Field    |     Widget      |
| :--------------: | :-------------: | :-------------: |
| models.CharField | forms.CharField | forms.TextInput |
| models.TextField | forms.CharField | forms.Textarea  |

​     



> 위젯 만들기

현재 만들려는 위젯은 참고 블로그 글인(https://bit.ly/2P1d6A7)의 과정을 그대로 따라 만든 것이다. 

입력값을 받는 칸 옆에 현재 사용자가 입력한 글자수를 보여주는 문구를 넣어주기 위한 위젯을 만들 것이다. 

(정확히 말하면 `일반 입력칸(default widget)`이 아닌 `입력칸+입력 글자수 보여주는 기능`의 위젯을 만들 것이다. )



* 위젯은 모델이나 장고 폼과 마찬가지로 app 디렉토리 아래 `widgets.py`라는 파일에 작성한다.

* 위젯은 기본적으로 `form`에 속하는 것이기에 `form` 라이브러리를 사용한다.

  `from django import forms `

* 위젯은 결국 **입력 양식**을 의미하기에 생성한 `widget` 클래스는 **입력 양식 파일(html)**과 연결되게된다. 

```python
from django import forms

class CounterTextInput(forms.TextInput):
    template_name = "widgets/counter_text.html"
    
# >> 'CouterTextInput'이라는 이름을 가진 나만의 Widget이 생성되었다
```





* 위에서 만든 위젯과 연결된 html 파일

```html
<!-- templates/widgets/counter_text.html -->

{% include "django/forms/widgets/input.html" %}  <!-- input 태그 생성 -->

<span id="counter_{{widget.attrs.id}}"></span>   <!-- 안내문구 영역을 span 태그로 생성 -->

<!-- 위에 span 태그에 들어갈 내용을 javascript로 작성 -->
<script>
    document.querySelector('#{{widget.attrs.id}}').addEventListener("input", function(){
        document.querySelector("#counter_{{ widget.attrs.id}}").innerHTML = this.value.length + '글자';
    })
</script>
```

​      

* `django/forms/widgets/input.html` 파일은 기본적으로 만들어져 있는 `input태그`를 담고 있는 `html파일`이다.

  * 해당 파일을 불러와서 `input` 태그를 만들어준다.

    (참고: `html파일`을 포함할때는 `include` 태그를 사용한다)



* 위에서 만든 `input` 태그 아래에`span` 태그를 생성해준다. 

  ▶ `span` 태그에는 입력값의 글자수를 보여주게되는데 이와 관련한 기능은 javascript로 작성되어 있다.
  (자바스크립트는 아직 공부중이라 정확히 해석은 안된다)

​     



> 이렇게 만든 widget은 어떻게 사용할까?

* `widget`은 `form`이 입력란을 만들 때 사용하는 것이다. 따라서 `widget`에 대한 사용은 모두 `forms.py`에서 이뤄진다. 