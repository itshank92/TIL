< telegram bot 만들기 >

[telegram api - bot api 사용해서 bot을 만들것임]

*API문서를 보면서 API를 사용해 챗봇을 만드는 실습진행
(API문서를 읽고 활용하는 방법 학습)

[요청하는 API_Making Request 문서]

https://api.telegram.org/bot<token>/METHOD_NAME
>> <token>: 내가 만든 봇의 token key값(private)을 넣어준다
>> method_name: 봇이 어떤 행위를 할지 정해주는 것(다양한 옵션 제공됨)
>> → available method 페이지에서 옵션들 확인해 볼 수 있음

[api url 주소를 python 파일에 넣어서 api로 활용]
python파일을 만들어서 해당 api를 사용한다
>> token을 변수로 따로 저장해서 쓴다


* chrome에서 json 파일을 예쁘게(indentation)보기위해서는
json viewer chrome을 설치하면 된다.


[bot api의 getUpdates 메소드]
▶ 현재 bot의 상황에 대해 파악하는 메소드
>> bot이 소통한 모든 기록(받은 메세지 기록 줌)
>> 메세지 보낸 사람의 id번호 볼 수 있음
>> (나의 id도 볼 수 있음_내가 메세지를 보냈다면)


[bot api의 sendMessage 메소드]
* api설명링크: https://core.telegram.org/bots/api#sendmessage
▶ required 항목을 잘 확인하여 해당 메소드 사용의 필수
인자를 확인한다. 
- chat_id: 누구에게 보낼 것인지
- text: 어떤 내용을 보낼 것인지

▶ API 주소의 경우 ?이후에는 key=value쌍이 &로 이어져 있다.
>> sendMessage역시 chat_id=보내려는id&text=보내려는내용 을 써줘야 한다.

예시 형태: print(f'{url}?chat_id={chat_id}&text=안녕하세요')
>> 이러면 url이 나온다.

▶ 해당 url을 타고 들어가면 텔레그램에서 bot이 
chat_id 대상자에게 text 내용을 보낸다.
→ 해당 url을 새로고침할때마다 메세지를 보낸다.
★ API의 요청이나 응답은 모두 url을 사용해서 이뤄지기에
url 페이지를 불러오는 요청이 들어가야 해당 기능(method)가 실행된다.



<지금은 만들어진 url을 chrome에서 우리가 직접 실행해서
메세지를 보낼 수 있었다.>
<이를 python에서 자동으로 url을 실행하는 것까지 해보자
▶ requests.get()을 사용해서 url 페이지를 불러오는 것을 요청할 수 있다. 

requests.get(url)을 통해 해당 url페이지에 값을 요청할 수 있다. 


*코드를 줄단위로 움직이는 방법
alt누르고 방향키움직이면 해당 코드를 줄단위로 올리고 내릴 수 있다.

[beautifulsoup의 selece 결과값을 깔끔하게 읽어오기]
>> .string or .text 사용하면 됨



<flask 사용해서 서버 만들기>

app.py파일 만들기
pip install flask 로 설치

flask 빠른 실행 도우미 문서: https://flask.palletsprojects.com/en/1.1.x/quickstart/
>> 위 문서에 있는 코드 app.py에 붙여 넣기

[flask 코드 설명]
@app.route('/hello') 
def hello():
    return '하이'
▶ /hello라는 주소로 들어가면 아래 있는 def를 실행하라는 명령어
▶ 주소/hello 페이지에서 수행되는 def를 정의하는 것이다. 


api도 결국 서버 컴퓨터가 존재하기 때문에 해당
컴퓨터가 우리의 요청을 처리해줄 수 있는것
우리는 지금 그것과  비슷한 서버 컴퓨터를 만들 것이다.

----------------------------------- 아래부터 오류발생
▶ 오류발생 이유: 내 PC이름이 한글로 되어 있는 경우 오류 발생
▶ 한글 PC이름을 바꿔주면 해결되지만 재부팅 필요 >> 따라서 수업 종료 후 할 예정

-- 따라가지 못하기 때문에 필기로 따라가기

<구조>

수신: User → Telegram 서버 → app.py    
▷ app.py를 공개 서버로 만든다(ngrok서비스사용)
▷ telegram에 해당 서버를 알려줌

발신: app.py →  Telegram 서버 →User 

<우리가 해야 하는 것은 2가지>

(1) Telegram 서버에게 우리의 app.py의 주소를 알려주고 연결한다.
▶ 현재 app.py는 우리의 로컬환경에서만 접속할 수 있는 서버(로컬서버)
▶ 이를 서버를 구매해서 할 수 도 있지만 우리는 간단하게 서비스를 이용할 것이다.
▶ ngrok을 사용함(무료버전시 2시간 유지 가능)

- ngrok 파일 다운로드 후 실행파일 경로 이동
  (app.py가 있는 위치로)

- app.py 코드 추가
  >> if __name__ == "__main__":
  >>   app.run(debug=True)
  >> 서버가 잘못 되었을때 자동 재시작 가능하게 한 코드

- flask run을 통해 flask 서버를 돌린다
  (해당 git bash창은 건들지 않는다 _계속 수행)

- 새로운 bash 창을 +를 통해 연다
  ▶ ./ngrok.exe http 5000
      >> ngrok.exe를 실행하여 http 5000번 포트로 바깥과 연결시켜줘
      >> flask는 기본적으로 5000번 포트를 사용함
  ▶ 결과값중 Forwarding https로 시작하는 것을 들어가자
       >> 해당 URL은 외부에 공개된 주소다. 
  ▶ 이 bash창은 이제 건들지 않는다.(서버 계속 사용)
       >>  ngrok으로 된 서버는 띄워두고 안만질것이다.
       >> 서버를 껏다 키면 ngrok의 url이 재발급되기에 끄지 않도록 유의한다.


[Telegram에게 우리의 주소(app.py)를 알려준다]

▶ 이 주소를 web hook(웹훅)이라고 부른다
▶ bot api > getting updates >> setwebhook 문서 확인
   (https://core.telegram.org/bots/api#setwebhook)
▶ setwebhook을 설정하기 위한 새로운 py파일 만든다.

▶ set_webhook.py 코드 작성

import requests

token = '1548668277:AAEnW9E6svwNd_bICKrZ0vsS4mb9qwTJb2A'
url = f'https://api.telegram.org/bot{token}/setWebhook'
ngrok_url = '' # ngrok에서 얻은 https 주소를 복사해서 붙여 넣는다. + 주소끝에 /telegram을 넣어준다.
webhook_url = f'{url}?url={ngrok_url}'  ## 인자를 넣은 최종 url 만든다.
print(webhook_url)  ## python 파일 실행해서 해당 url에 들어가면 webhook 설정이 끝난 것이다.

▶ ngrok_url의 https 주소 뒤에 /telegram을 넣어준다.
    >> 이후 우리가 만들 페이지를 미리 써주는 것이다.


[챗봇한테 가서 메세지를 보내보자]
>> 아무거나 보냄

flask를 돌리고 있는 git bash terminal에 메세지를 
받은 기록이 나온다.
>> 발신자가 telegram 서버에 보낸 메세지를
>> telegram 서버가 app.py의 서버로 보내준 것이다. 


[응답을 해보자]

■ app.py 수정
    import에 request 추가
    >> request는 telegram이 app.py에 전달한 내용이 담김
    >> print(request)로 확인가능


@app.route('/telegram', methods = ['POST'] 수정
print(request.get_json())를 추가

>> 어떤 사용자가 어떤 시간에 어떤 메세지를 telegram에
>> 보냈는지 우리가 json 형태로 확인가능


[우리는 사용자가 보낸 메세지에 따라서 응답을 해주는
챗봇을 코드로 작성할 것이다.]

(1)이를 위해서 어떤 사용자가 어떤 메세지를  보냈는지
찾아서 변수로 저장한다.
→ request.get_json()으로 긁어온 내용에서 찾는다.
→ user_msg에 메세지 내용을, user_id에 유저아이디를 담는다.

(2) requests를 통해서
message url로 요청을 전달한다. 







<flask run과 관련한 내용>

[flask run 대신 디버그 모드를 켠 flask run 수행 명령어]
▶FLASK_ENV=development flask run

[flask run 상태에서 app.py 수정 내용을 저장하면
자동으로 변한 내용이 적용되서 자동으로 재시작한다]



<api 다르게 사용하는 경우>
▶ 네이버 API의 경우 파라미터가 아니라 Header와 Params를 통해 사용한다
▶ 파라미터값이 아닌 header에 키를 넣고 params에 인자를 넣는다.