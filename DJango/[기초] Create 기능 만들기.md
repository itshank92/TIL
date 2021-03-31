#### CRUD 중 C에 해당하는 Create 기능을 수행하는 페이지와 함수를 작성하자 

---

> 특징

* 게시물을 작성하고 저장하는 기능을 하나의 함수에서 구현한다.
  (따라서 `url` 역시 하나의 `url`에서 입력과 저장이 처리된다.)

* `url`은 중복되지만(==`url`은 하나지만) `request`의 `method`가 `GET`인지 `POST`인지에 따라서 입력기능이 수행되기도하고 저장기능이 수행되기도한다. 



>  데이터를 입력받아서 저장하는 함수

```python
# app의 views.py 

from django.shortcuts import render, redirect
from .forms import ArticleForm


def new_create(request):
    # request의 method가 POST인 경우 form의 유효성 검사
    if request.method == 'POST':
        form = ArticleForm(request.POST)
        # 유효하다면 저장
        if form.is_valid():
            form.save()
            # article = form.save() ▶ 저장하는 객체를 article에 받아올 수 있다.
            return redirect('articles:index')

    # request의 method가 GET인 경우 새로운 form 생성
    else:     
        form = ArticleForm()

    # POST실패시 form은 사용자 입력데이터가 남아있는 form일 것이다.
    # GET일 때 form은 방금 막 생성한 비어있는 form일 것이다.
    context = {
        'form':form
    }
    # 입력페이지로 render
    return render(request,'articles/new_create.html',context)
```



* ★주의★ **왜 `else`인 경우에만 `form`을 새로 만들어서 입력페이지를 `rendering`할까?**

  (다시 말하면 `POST` 실패시 무조건 `form` 을 새로 만들어서 입력페이지 `rendering`할수도 있는데 왜 굳이?)

  ▶ `POST`로 받은 `request`의 `form` 유효성 검사 실패시 사용자가 입력한 내용 중 유효한 내용이 기존의 `form`에 존재할 수 있다.

  ▶ 따라서 이 경우 새로운 `form`을 만들어서 처음부터 다시 입력하게 하는 것보다 입력된 데이터를 가진 입력페이지를 `rendering`하는 것이 더 좋은 서비스라고 할 수 있다. 





> 데이터를 입력받는 html 페이지

```html
<!-- app디렉토리 - templates -app이름 폴더 - new_create.html -->

{% extends 'base.html' %}


{% block content %}
  <form action="{% url 'articles:new_create' %}" method = 'POST'>
    {% csrf_token %}
    <table>
      {{form.as_table}}
    </table>
    <input type="submit">
  </form>
{% endblock  %}
```





