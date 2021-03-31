### 기존에 만든 데이터를 수정하고 다시 저장하는 기능을 만든다.

> 특징

* Create와 마찬가지로 하나의 `url`을 사용해서 기존의 데이터를 수정하고 다시 저장하도록 코드를 작성한다.



* Update를 위한 함수의 전체적인 틀은 Create와 동일하다.
  * `request`의 method가 `POST`인지 `GET`인지를 구분하여 수정페이지로 넘어갈지, 저장페이지로 넘어갈지 결정한다.



* Create와 다른 Update의 특징은 저장시 기존의 데이터를 수정해줘야 한다는 것이다. 

  * `POST`방식일 때 `form`의 유효성 검사를 위해 `form`을 만들어준다.
  * 이때 `instance`라는 인자의 값으로 원래의 데이터를 넣어준다. 
    * 따라서 Update함수의 가장 상단에 원래 데이터를 `objects.get` 방식으로 읽어와서 변수에 담아야 한다.

  * 이후 `form`에 대한 유효성 검사를 한 다음 `form.save()`를 통해서 저장한다.
    * 이때 내부에서는 `instance` 인자에 데이터가 존재한다면 해당 데이터에 `form`으로 입력받은 데이터를 덧씌워주는 작업이 수행된다.

​     

> 코드

```python
# views.py

from django.shortcuts import render, redirect
from .models import Article
from .forms import ArticleForm

def update(request,pk):
    # 수정 대상이 되는 데이터를 변수에 담는다.
    article = Article.objects.get(pk=pk)

    # request의 method가 POST 방식인 경우 (=수정데이터를 받아왔을 때)
    if request.method == 'POST':
        # 입력데이터로 form을 만들어주는데, 이때 기존 데이터를 instance로 넣어준다.
        form = ArticleForm(request.POST, instance = article)
        # form의 유효성 검사
        if form.is_valid():
            # 유효하다면 데이터를 저장한다.
            # 이때 내부적으로 instance에 해당하는 데이터가 있다면 수정 & 저장한다.
            form.save()
            return redirect('articles:detail',pk)
    
    # request의 method가 GET 방식인 경우 (= 수정데이터 입력전)
    else:
        # 기존 데이터를 사용하여 form을 생성한다.
        # 이를 통해 수정페이지에서 form에 기존의 입력값이 입력된 상태로 보여줄 수 있음
        form = ArticleForm(instance = article)
        
    context = {
        'form':form,
    }
	
    return render(request, 'articles/update.html',context)
```





> 모델 폼 생성 코드 해석

1. `form = ArticleForm()`   ▶  비어있는 인자
   * 비어있는 인자인 경우 **비어있는 `form`을 생성**한다. (입력값 없음)



2. `form = ArticleForm(request.POST)`  ▶ `request.POST`를 인자로
   * **`request.POST`에 있는 `form`의 입력값으로 채워진 `form`을 생성**한다.



3. `form = ArticleForm(instance = article)` ▶ 모델 데이터를 `instance`의 값으로
   * `article`라는 이름의 **모델 데이터의 데이터로 입력값이 채워진 `form`을 생성**한다.



4. `ArticleForm(request.POST, instance = article)`  ▶ `request.POST`와 모델데이터를 인자로
   * **`request.POST`에 있는 `form`의 입력값으로 채워진 `form`을 생성**한다.
   * 즉, `form`의 입력값에 들어가는 데이터는 `request.POST`가 1순위 `instance`가 2순위다.
   * `request.POST`와 `instance`를 둘 다 넣어주는 이유는, 폼을 저장할 때 `instance`가 있다면 해당 `instance`를 참조하여 수정해서 저장하기 때문이다. 



