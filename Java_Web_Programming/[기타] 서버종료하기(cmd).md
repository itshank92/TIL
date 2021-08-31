```
> netstat -aon | find "8080"
  TCP    0.0.0.0:8080           0.0.0.0:0              LISTENING       2196
  TCP    [::]:8080              [::]:0                 LISTENING       2196
  
> taskkill /pid 2196 /f
  SUCCESS: The process with PID 2196 has been terminated.
```



`netstat -aon | find "8080"`

* 8080 포트 번호를 사용하는 모든 대상을 보여달라는 명령어



`taskkill /pid 2196 /f`

* 아이디가 2196인 대상을 종료하라는 명령어