# fontawesome 사용하기

출처: https://citylock77.tistory.com/42



사전작업

static관련 설정을 settings에 모두 해줘야 한다(STATIC_ROOT도 설정해야된다)

프로젝트 디렉토리와 같은 레벨에서 staticfiles이름(STATIC_ROOT로 설정한 값)의 폴더를 만든다.



다운로드 받는다



다운로드 받은 파일에서 **all.css** 을 **static/css/ 폴더**에 복사해 넣는다. 





다음은 webfonts 폴더 전체를 복사해서 static 폴더 아래로 복사한다. 



터미널로 돌아와서 python manage.py collectstatic 명령어를 통해서 모든 static 파일을 한번 정리한다. 



프로젝트 파일로 돌아와서 base.html 코드에 조금전에 다운로드 받은 all.css 파일을 링크 걸어준다. 



사이트에서 원하는 아이콘의 코드를 찾아서 html코드를 찾아서 복사해서 사용한다.

(사용하려면 해당 페이지에서 {% load static %} 필수 )







### fontawesome 컬러 사용하기

출처: https://fontawesome.com/how-to-use/on-the-web/referencing-icons/basic-use



* span으로 묶어서 사용한다.

```html
<span style="font-size: 3em; color: Tomato;">
  <i class="fas fa-camera"></i>
</span>
```





### fontawesome 아이콘 회전하기

출처: https://fontawesome.com/how-to-use/on-the-web/styling/rotating-icons