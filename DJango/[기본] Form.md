# (장고의) 폼

참고: https://tutorial.djangogirls.org/ko/django_forms/

참고2: https://wayhome25.github.io/django/2017/05/06/django-form/





> 장고의 폼은 왜 태어났을까?

그동안 우리는 HTML의 `form`태그를 직접 하드 코딩해서 사용자의 입력값을 받아왔었다

예를 들면 게시판의 게시글을 올리는 페이지를 작성할 때 직접 `<from action...>`으로 시작하는 코드를 작성했었다.

근데 한번은 괜찮은데 이런 게시판이 여러개 있는 사이트인 경우 각 html 페이지에서 이 `form` 코드를 복사해서 계속 붙여넣어줘야 할까?

이 일을 쉽게 해주는 것이 바로 장고의 폼(form)이다.
(사실 장고의 폼은 form 태그 코드를 쉽게 처리해주는것 외에도 다른 장점도 매우 많다.)

장고에서는 입력값을 받는 form 태그 코드를 하나의 폼 클래스로 만들어서 관리한다.

이제 이 일을 좀 더 쉽게 하기 위해 form도 하나의 클래스로 만들어서 여기저기서 불러와서 사용할 수 있게 만들자.    





> 장고의 폼은 어떻게 만들까?

폼도 폼만의 **`forms.py`**라는 파일에서 관리한다.

폼의 목적은 사용자의 입력값을 쉽게 받고 해당 입력값으로 모델 테이블에 쉽게 저장하기 위함이다.

```PYTHON
from django import forms

from .models import Post  ## form과 연동될 모델 클래스를 불러온다.

class PostForm(forms.ModelForm):

    class Meta:
        model = Post
        fields = ('title', 'text',)
```



> 장고의 폼은 어떤 형태일까?

장고의 폼을 `print`로 출력해보면 **html의 테이블**로 출력이된다.

이는 폼 자체가 html 코드라는 것이 아니라 폼이라는 클래스의 `__str__`함수의 반환값이 html 코드인 것이다. 

아무튼 폼을 통해 우리는  `field`를 칼럼으로 하는 **html 테이블**을 쉽게 만들 수 있다.

따라서 폼을 가지고 손쉽게 웹페이지에서 입력창을 구현할 수 있는 것이다. 







> 장고의 폼의 종류는 두가지가 있단다.

폼에는 종류가 2가지가 있다.  (모델폼, 폼)

* 모델폼
  * **우리가 만들었던 모델에 맞는 폼**이다.
  * **즉 우리가 만들었던 모델의 필드(칼럼)에 맞는 입력 폼을 만들어 주는 것**이다.
  * 우리가 정성들여 만들었던 모델 클래스를 그냥 import해와서 해당 폼의 model 속성의 값으로 주면 된다.
  * 단 주의해야 할 것은 class 내부에서 `Meta`라는 이름의 **자식 class**를 만들고 이 `Meta`의 속성값으로 model을 입력해야한다는 것이다.
  * 모델 입력후에는 field라는 속성값에 우리가 만든 모델의 field를 문자열로 넣어준다.
  * [요약] **모델폼**은 **모델과 필드를 지정**만해주면 **장고가 알아서 만들어주는 폼 양식**이다.

```python
from django import forms

from .models import Acrticle  ## form과 연동될 모델 클래스를 불러온다.

class PostForm(forms.ModelForm): # forms의 ModelForm을 이용한다.

    class Meta:
        model = Acrticle
        fields = ('title', 'text',) 
      # fields = '__all__'
      # exclude = ['제외하려는 필드값',...]
```



| 모델폼 선언시 Meta클래스를 따로 만드는 이유                  |
| ------------------------------------------------------------ |
| 폼은 기본적으로 모든 속성값을 `input`값의 `label`로 인식한다.<br />이를 명시적으로 이해하려면 아래에 나오는 일반 폼을 작성하는 코드를 살펴보면된다.<br />일반 폼 작성의 경우 폼클래스 바로 아래에 `input`값의 `label`들을 작성해준다.<br />하지만 모델폼의 경우 폼 작성의 기준이 되는 모델을 설정하는 것과 모델에서 폼으로 만들 필드 역시 설정해줘야 한다.<br />이러한 작업은 폼클래스 바로 아래에 작성할 수 없다. <br />왜냐하면 앞서 말햇듯 폼클래스 아래 영역은 `label`과 `input`의 영역이다.<br />따라서 모델폼에서는 모델과 필드를 설정해주는 영역을 분리하기 위해 `Meta`라는 새로운 클래스를 사용하는 것이다.  <br />추가적으로 설명하자면 데이터를 관리할 때, 메타데이터를 저장하는 공간은 일반 데이터의 저장공간과 분리해주는 것이 일반적이다. |



| 모델폼에서 `__all__` 과 `exclude`는 무엇인가?                |
| ------------------------------------------------------------ |
| `__all__`는 해당하는 모델의 필드 값 중 `editable=False`인 필드를 제외한 모든 필드를 인자로하는 배열을 가리킨다. <br />`editable=False` 속성을 가진 필드는 `auto_now_add`와 같이 자동적으로 입력되는 필드를 의미한다. <br /> ▶ 자동입력속성값을 지닌 필드는 자동적으로(by default) `editable` 속성값이 `False`가 된다.<br /><br />`exclude`는 필드에서 제외하려는 필드 값을 배열로 받는다.<br />` __all__`을 사용하여 필드를 받은 경우 `exclude`로 세부 설정을 해줄 수 있다. |

​	





* (그냥) 폼
  * 모델폼과 다르게 뭐 어디서 정보를 가져올 것 없이 사용자가 직접 만드는 form 클래스다.
  * 직접 필드를 정의해야한다.(데이터 타입 등등)

(그냥) 폼의 코드는 아래와 같다.

```python
from django import forms

class NormalForm(forms.Form): # forms의 Form을 이용한다.
    title = forms.CharField()
    content=forms.CharField(widget=forms.Textarea)
    
```





> 장고 폼 사용하기

1. #### 장고폼을 views의 함수에서 불러와서 context에 넣어서 html페이지로 넘겨준다.

* 장고 폼 클래스들을 저장한 forms.py에서 우리가 사용할 폼 클래스를 불러오는 것으로 시작한다.

  `from .forms import PostForm`

* 불러온 폼 모듈을 사용하여 객체를 만들어서 변수로 지정한다. 그리고 해당 변수를 context에 넣어서 render의 인자로 보낸다.

  ```python
  # views.py
  
  def post_new(request):
      form = PostForm()
      context = {
          'form':form
      }
      return render(request, 'main/index.html', context)
  ```






2. #### html 파일에서 받은 폼을 가지고 입력란을 쉽게 만든다.

* 앞서 받은 폼을 가지고 입력란을 쉽게 만들 수 있다.

* 예시 코드는 아래와 같다

  ```python
  ...
  <form method:'POST' class='post-form'>
      {% csrf_token %}
      {{ form.as_p }}
      <button type='submit'>Save</button>
  </form>
  ...
  ```

  

* 일단 폼을 불러와도 `form` 태그는 만들어줘야 한다.

  * 왜냐면 우리가 만든 장고의 폼은 어디로 가야하는지 url 정보가 없기 때문이다.

* `form` 태그 아래에서 `form.as_p`라는 DTL 태그를 사용하면 장고가 장고 폼을 사용해서 입력란을 만들어준다.

* `form.as_p`는 `form`의 내용(`html` 테이블의 각 `lable`과 `input`쌍)을 `p`태그로 묶어서 표현해준다.

* `form.as_ul`는 `form`의 내용(`html` 테이블의 각 `lable`과 `input`쌍)을 `li`태그로 묶어서 표현해준다.

* `form`으로 테이블을 만들려면 `<table>` 태그 안에 `{form.as_table}`을 넣어주면 된다.      

* `csrf token`은 장고에서 POST로 보낸 데이터임을 인증하는 기능을 수행한다.

  * 장고에서의 CSRF 인증 태그

    ▷ 이번에 보낼 form 데이터는 현재 사이트에서 만들어진 것으로 인증하는 것

    ▷ 토큰을 form 데이터에 포함해서 전달함으로서 보안을 인증해준다.

   

  | CSRF(Cross-site request forgery)                             |
  | ------------------------------------------------------------ |
  | ● 웹사이트에 대한 공격 중 하나<br />● 공격에 대한 전제 조건<br />1) 공격 대상 서비스 웹사이트에 희생자가 로그인 상태<br />2) 희생자가 해커가 만든 피싱 사이트에 접속 상태<br />위 상황은 발생하기 그리 어려운 상황이 아니다. 예를들면 페이스북에 자동로그인 설정한 상태에서 음란광고로 피싱사이트가 열릴 수 있다. 피싱사이트에서  form의 action 주소가 facebook/내게시판이면 facebook에 희생자의 이름으로 글을 적을 수 있다. |

  



```python
...
form = PostForm(request.POST)
...
```

3. #### 해당 페이지에서 사용자가 입력한 값이 다시 POST로 전달되면 우리는 전달받은 장고 폼을 가지고 데이터를 추출해야 한다.

   * `<form action = 'POST'...>`로 전달한 (입력된) 장고 폼은 `request.POST`에 저장이 된다.

     * `request.POST`는 `POST`방식으로 받은 `request`의 모든 `parameter`와 `값`들을 `dictionary` 형태로 반환해준다.

   * `request.POST`를 인자로 우리가 만들었던 장고 폼 클래스의  인자로 넣어서 새로운 장고폼 객체를 만든다.

     * `form = PostForm(request.POST)`

     * 원래 모델폼 객체는 `field`들의 입력값이 **빈 상태의 `html 파일`**을 만든다. 

       (`PostForm()` ▶ `input`이 비어있는 `form`이 있는 `html 페이지`)

     * 그런데 모델폼 객체에 `request.POST`를 사용해서 입력값이 입력된 `form` 데이터를 전달하면 `field`들의 **입력값이 채워진 상태의 `html 파일`**을 만든다.

       ▷ `request.POST`에 들어있는 `form`에 입력값이 입력된 상태로 `html 파일`을 만들어준다.

       ▷ 즉 `PostForm(request.POST)`는 방금 사용자가 입력값을 채운 `html페이지`를 의미한다.

     * **이 페이지를 사용하여 사용자의 입력값의 유효성을 검증**할 것이다.

   * 이전과 다르게 이번에 만든 장고폼 객체는 인자 즉 입력값이 다 채워진 객체다. 

     * 이전의 장고 폼 객체 (입력값이 없던 빈 장고폼 객체)를 출력시 ▶  `form` 입력란이 비어있는 `html 페이지`
     * 지금 장고 폼 객체(입력값이 있는 채워진 장고폼)를 출력시 ▶  `form` 입력란이 채워져있는 `html 페이지`

| ※ 주의 ※  장고폼 객체와 html 페이지는 같은 것이 아니다.      |
| ------------------------------------------------------------ |
| 장고 폼 객체와 `html 페이지`는 같은 것이 아니다. <br />장고 폼 객체는 말그대로 장고 폼이라는 클래스를 사용하여 만든 객체로 많은 속성값과 메소드를 가지고 있는 대상이다.<br />장고 폼 객체를 출력했을 때 `html 페이지` 코드를 반환하는 이유는 단지 장고 폼 객체의 `__str__` 함수의 반환값이 `html 페이지`이기 때문이다. |





3. #### 우리가 (입력받은 데이터를 넣어서 만든) 장고폼 객체가 유효한지 판단한다.

   * 이때 **'유효하다'**는 것은 **입력 데이터가 제대로 입력되었는지 확인하는 작업**이다.

     ```python
     import timezone
     
     ...
     
     #1
     if form.is_valid():
       	#2  
         post = form.save(commit=False)
         #3
         post.user = request.user
         #4
         post.published_date = timezone.now()
         #5
         post.save()
         
     ```

     ​       

     * **#1**
       * 폼 객체는 `.is_valid()` 메소드로 유효성을 검사한다.
     * **#2**
       * 폼 객체의 `.save()` 는 두 가지 기능이 있다. 
         1. 자신에게 있는 입력데이터 값을 이용해서 해당 **폼 객체와 연결된 모델 객체를 생성**한다.
            * 자신의 meta 데이터에 있는 모델의 객체를 생성하는데 입력데이터를 이용해서 field를 채운다. 
         2. 모델 객체 생성과 동시에 해당 `모델객체.save()`도 동시에 수행한다.
            * 하지만 만들어진 모델 객체에 입력값이 없는 field가 있는 경우 `.save()` 수행시 오류가 발생한다. 
            * 따라서 `폼객체.save()`의 두번째 기능인`모델객체.save()`를 꺼주는 것이 필요하다.
            * 이 기능을 꺼주기 위해서  `commit=False`를 인자로 넣어준다.
            * `commit`은 자동으로 모델객체 `.save()`를 해준다는 의미이다. 
     * **#3**
       * request에 담겨진 user 정보를 모델객체의 user 속성값으로 넣어준다.
     * **#4**
       * 현재 시간(timezone.now())를 불러와서 모델객체의 published_date 속성값으로 넣어준다.
     * **#5**
       * 모델객체의 속성(field)이 모두 채워졌다면 저장한다. 

​     

​      



> 장고 폼으로 입력받은 입력값을 검증하는 이유

* `input`태그의 `required` 속성을 통해 입력값을 입력받을 때 빈 칸을 입력 못하게 1차적으로 검증을 할 수 있는데 왜 다 받은 `form`을 다시 검증할까?

  ▶ `input` 태그에 `required` 속성을 주면 해당 입력값이 빈칸일 때 제출(`submit`)을 하지 못하게 알림창이 뜬다.

* `html` 코드는 사용자가 크롬의 개발자 도구등을 사용해서 확인할 수 있을 뿐만 아니라, 실제 코드를 수정할 수 도 있다. 

* 즉, 사용자 임의로 `input` 태그에 붙은 `required` 속성을 지우고 `submit`을 할 수 있는 것이다. 

* 따라서 개발자는 frontend의 검증(`required`속성)뿐만아니라 입력받은 `form`이 유효한지 backend의 검증을 수행해야 한다. 







### form 덩어리를 각각의 입력칸으로 분리해보기

html 파일에서 원래는 `{{form}}`으로 넣어주는데, 이를 .필드명으로 분리할 수 있다.

(예시) `{{form.title}}`  :  title이라는 field의 값을 입력받는 input 태그





### 위젯을 사용하여 form의 각 입력칸의 출력 형태를 바꿔보자 

```python
class ArticleForm(forms.ModelForm):
    # 여기에는 Meta에서 모델로 받은 Article의 field들이 생략되어 있다.
    # >> 각각의 field는 form이 자동적으로 field의 이름을 가진 label과 input태그로 이뤄진다. 
    # >> 즉 현재 class ArticleForm(forms.ModelForm): 바로 아래에는 다음과 같은 것들이 생략되어있다.
    # >> title = forms.CharField() 
    # >> content = forms.TextField() 
    # 위젯을 사용하고 싶다면 바로 이 생략된 field들을 다시 작성하고 widget 인자의 값을 넣어주면 된다.
    # forms.TextInput은 <input type = 'text> 를 의미한다.
    # attrs는 자신이 속하는 input 태그의 속성:값을 사전형태로 넣어주는 것이다.
    # 예를 들면 attrs = {class:'form-control'}을 넣어주면 <input class = 'form-control'> 이 된다.
    # (참고로 form-control은 부트스트랩에서 제공하는 클래스)
    # forms.Textarea는 textarea 태그를 의미한다.
    # attrs에는 태그의 각종 속성:값을 다 넣어줄 수 있다.
    title = forms.CharField(
        label = "제목",
        widget=forms.TextInput(
            attrs= {}
        )
    )
    class Meta():
        model = Article
        fields = '__all__'
```

