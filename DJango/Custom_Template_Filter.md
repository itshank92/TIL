# 커스텀 템플릿 필터



> 커스텀 템플릿 필터란?

DTL에서 사용할 수 있는 사용자 정의 함수  



> 사용방법

앱에 커스텀 템플릿 필터를 폴더를 만드는데 반드시 templatetags 이름으로 해야 한다.

해당 폴더에 `__init__.py`를 만든다.

모듈이름.py를 만든다. (여기서는 `my_filters.py`)





```python
# 모듈이름.py  >> 여기서는 my_filters.py

# 장고의 template을 import한다.
from django import template

register = template.Library()

@register.filter
def hashtag_link(content):
    return f"<a>{content}</a>"

```



이렇게 만든 필터를 template에서 사용하기

```django
{% load my_filters  %}

...
{{ post.content|hashtag_link }}
...
```

* 위 코드 중 `{{ posts.content|hashtag_link }}`는 사실 `hashtag_link(posts.content)`의 형태와 동일한 기능을 수행한다.  

* 하지만 저렇게 하는 경우 결과값은 문자열로 나온다
  * 즉 <a> 가 그냥 문자여로 나온다



이 결과값을 html 문서화하려면 결과값에 |safe를 적용시켜야 한다.

```django
{% load my_filters  %}

...
{{ post.content|hashtag_link|safe }}
...
```

