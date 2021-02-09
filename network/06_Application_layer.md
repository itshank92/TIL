### WWW(=W3)

* 인터넷에 연결된 컴퓨터들을 이용해 사람들과 정보를 공유할 수 있는 전체 공간을  용어

* 인터넷 시스템 중 하나이지만 사실상 인터넷과 동의어로 취급될 정도로 일반적인 인터넷 시스템이다. 

* 인터넷: TCP/IP를 기반으로 전 세계적으로 연결된 컴퓨터 네트워크다. 

  *  WWW(웹)은 인터넷의 한 부분으로 전자메일, 파일 공유, 웹캠, 동영상 스트리밍, 온라인 게임 등 인터넷에는 다양한 영역이 존재한다.     

    

### www에는 3가지 기술이 사용된다.

* html, url, http
  *  URL(Uniform Resource Locator)
    * 인터넷에서 파일 위치를 지정하기 위해 기술된 주소(웹사이트 주소를 지정하는데 사용된다)
  * HTTP: 서버의 80번 포트를 사용해서 통신한다 (HTTPS의 경우 **443번**)
    * 데이터 요청시에는 클라이언트는 **GET**이라는 요청명령을 전송한다.
    * HTTP 2.0에서는 비동기 방식의 통신을 통해 웹페이지의 각 부분을 먼저 받는대로 보여준다.
      (이전까지는 순차적으로 앞의 패킷을 받은 다음, 이후 패킷을 받는 형식으로 통신했다.)







### Application 계층에 존재하는 application들이 서로 통신하는 방식    

* application layer 상에서 network applications이 서로 communication하는 방식에는 
  두 가지 model이 존재한다. (client-server model, peer-to-peer model)



* **client-server model** vs **p2p model**
  * client-server model : 서버가 유일하게 데이터를 제공하는 역할, 나머지는 모두 데이터를 받는 역할
    → 우리가 현재 사용 중인 web service가 client-server model 사용.
  * p2p model : 네트워크 참여자 모두가 데이터를 주는 역할과 받는 역할을 겸하고 있는 구조





### client-server model

- **client-server model의 특징 中 server의 특징**

  1. server는 항상 가동 상태**(always-on)**에 있다

     * client의 요청이 언제 어디서 들어올지 모르고 + 데이터를 제공하는 유일한 대상이기에

  2. server의 IP 주소는 항상 동일하다 **(permanent IP address)**

     * 유일한 데이터 제공자인 server의 IP 주소가 변동적이면 client들이 지속적으로 서비스 받기 어렵다. 

  3. 안정적인 서비스를 위해서 (보통) server는 **data center**형태가 됨 (중복된 서버를 (여러개) 구성)

     * 인터넷이 널리 퍼지면서 client 수가 늘어나게되고 따라서 하나의 server로는 client의 요청을 감당하기 어렵게 됨 → data center에 서버를 복제해서 여러 개를 갖춰 clients의 많은 request 감당   

       

- client-server model의 특징 中 client의 특징

  1. 보통 server와 간헐적으로**(intermittently)** 연결 된다
     * client는 data가 필요로 할 때만 server와 연결하려하기에 항상 연결되어있을 필요가 없다
  2. client는 permanent IP address를 가질 필요가 없다 **(dynamic IP address)**
     * client-server model에서는 항상 request는 client 쪽에서 발생하기에 client의 IP address는 통신과정 동안에만 고정되있으면 되고 다음 통신에는 바뀌어도 아무 상관없다. 
  3. client들은 서로 직접적으로 연결되지 않는다 **(서로 통신하지 않는다)**
     * 바로 다음에 다룰 p2p model과의 핵심적인 차이점이다.



### Peer-to-peer model 

- peer-to-peer model에서는 특정한 하나의 server가 존재하지 않기에 always-on server도 없다.
  - **개별 peer들은 client이면서 동시에 server다**. (상황에 따라 client가 되기도 하고 server가 되기도 한다)
- peer들은 **통신을 할 때만 연결을 하고(intermittently) 따라서 고정된 IP 주소를 가질 필요가 없다**. 
- peer들은 자신이 원할 때 **직접적(directly)으로 통신 작업을 수행**한다.
   - “직접적”이라는 말은 server를 거치지 않고 통신을 한다는 의미다.
      (참고_ arbitrary: 제멋대로인, 임위적인)
- self scalability(자기 확장성) ▶ Peer-to-peer model의 가장 큰 특징
  - p2p model에서는 네트워킹에 참여하는 **peer의 수가 많아지더라도 scalability가 보장**된다. 
  - client-server model의 경우 request를 처리하는 역할은 server가 독점하기에 client가 늘어나면 
    server에 overhead(과부하)가 발생할 수 있다. 
    → 이게 바로 DDos (Distributed Denial Of Service attack)의 기본 구조이다.
  - 그러나 p2p model에서 peer client이면서 동시에 server이기에 peer의 증가가 발생해도 overhead는 쉽게 발생하지 않는다.



**scalability(확장성)**

- 소프트웨어 시스템에 있어 확장성이란 시스템에 부하가 어느 정도 증가하더라도 좋은 성능을 유지할 수 있는 특성을 의미한다. 
  