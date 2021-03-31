# Follow

모르겠으면 3월 31일로 돌아가던가



```python
## models.py

class User(AbstractUser):
    # 같은 모델과 n:m관계인 followings 생성
    followings = models.ManyToManyField('self',symmetrical=False, related_name='followings')
```



```python
# urls.py

path('<int:user_pk>/follow/', views.follow, name = 'follow'),
```



```python
## views.py

@require_POST
def follow(request,user_pk):
    # 팔로우 받는 사람
    person = get_object_or_404(get_user_model(),pk=user_pk)
    
    # 나 자신을 팔로우 할 수 없다.
    if person != request.user
        # 팔로우 끊는 경우
        if person.followers.filter(pk=request.user.pk).exist():
            person.followers.remove(request.user)
        # 팔로우 받는 경우
        else:
            person.followers.add(request.user)
    return redirect('accounts:profile',person.username)
```

​    

```html
<!-- detail.html -->

<div>
    <div>
        팔로잉: {{}} / 팔로워: {{}}
    </div>
    {% if request.user != person %}
    <div>
        <form action="{% url 'accounts:follow' person.pk  %}" method = "POST">
        	{% csrf_token %}
            {% if request.user in person.followers.all %}
            	<button> 언팔로우 </button>
			{% else %}
            	<button> 팔로우 </button>
            {% endif %}
        </form>
    </div>
    {% endif %}
</div>
```

