# 좋아요 기능을 구현하자





Article 모델에 좋아요를 누른 유저 모델을 연결하자

* Article은 좋아요를 누른 유저와 N:M관계이다.

```python
# models.py

class Article(models.Model):
    user = models.ForeignKey(settings.AUTH_USER_MODEL,on_delete = models.CASCADE)
    like_user = models.ManyToManyField(settings.AUTH_USER_MODEL)
    ...
    
```



위의 코드는 에러가 발생한다.

* settings.AUTH_USER_MODEL(유저모델)과 Article은 1:N관계와 N:M 관계 2개가 생성된다.
* 생성 자체는 괜찮은데 두 관계 모두 역참조하는 경우 `user.article_set.명령어`로 역참조한다.
* 여기서 중복이 발생하므로 에러가 발생한다.



해결방법: 두 관계 중 하나의 관계의 related_name(역참조매니저 이름)을 바꾼다.

```python
# models.py

class Article(models.Model):
    user = models.ForeignKey(settings.AUTH_USER_MODEL,on_delete = models.CASCADE)
    like_user = models.ManyToManyField(settings.AUTH_USER_MODEL related_name='like_articles')
    ...
```

​     



## Like 구현을 어디(어느 어플리케이션)에 하는 게 좋을까?

* 유저가 관여하기는 하지만 글에 대한 것이므로 글을 다루는 Article 어플리케이션에 작성하자.
* urls부터 작성한다.
  * 몇번 article에서 좋아요를 누르는지 url 만든다.



> views 함수 추가

```python
# views.py

@require_POST
def likes(request,article_pk):
    article = get_object_or_404(Article,pk = article_pk)
    
    # article 관계 필드(like_user)에 유저가 있으면 취소, 없으면 누름
    if request.user in article.like_users.all():
    # 다른 표현 방법
    # if article.like_users.filter(pk=request.user.pk).exists():
        # 좋아요 취소
        article.like_users.remove(request.user)                
    else:    
        # 좋아요 누름
        article.like_users.add(request.user)
    return redirect('articles:index')
    

    
```



> 좋아요 버튼 만들까

```html
<!-- index.html -->

<form action = "{ url 'articles:likes' article.pk}" method = "POST"}
      {% csrf_token %}
      {% if request.user in article.like_users.all %}
        <button> 좋아요 취소 </button>      
	  {% else %}
        <button> 좋아요 </button>
	  {% endif %}
</form>

```

​    

> ##명이 이 글을 좋아합니다. 보여주기

```html
<!-- detail.html -->

...

<p> {{ article.like_users.all|length }}명이 이 글을 좋아합니다.  </p>
```



---



### 소스 모델과 타겟 모델 (Source Model & Target Model)

두 모델이 관계를 맺고 있을 때, ForeignKey를 가진쪽이 Source Model이라고 불리고 ForeignKey가 없는 쪽이 Target Model이라고 불린다.



> (기타) 윈도우에서 이모지 사용 단축키

`.`  + `window키`



---

무엇인가를 막아야 하는 일은 크게 두가지로 수행된다.

프론트 페이지에서 막는 것 → template에서 수정

백엔드에서 막는 것 → views에서 수정



