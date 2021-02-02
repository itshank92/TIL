## 네트워크계층

#### IP주소 기본 내용

* IP주소는 퍼블릭한 공인 IP와 사설 IP가 있다

* IPv4에서는 주소 부족 현상이 있었기에, 라우터에는 공인 IP를 부여하고 개인 IP에는 사설 IP를 부여한다. 

  * 라우터는 WAN과 LAN을 연결해주는 역할을 수행하는 장치다
  * 라우터의 DHCP기능을 통해 라우터는 개인 PC에 사설 IP를 자동으로 할당한다.
  * DHCP: Dynamic Host Configuration Protocol

* IP주소의 전체길이는 32비트이고 읽기 편하도록 8비트로 나눠서 읽는다.

  * 8비트 단위를 옥텟(octet)이라 부른다

  * 우리(사람)는 IP주소를 8비트로 나눈 것을 10진수로 바꿔서 읽는다.    

    

#### IP주소의 구성

* IP주소가 하는 일은 크게 두 가지이다.
  * (1) 어떤 로컬네트워크(LAN)인지 표현하는 부분
  * (2) 네트워크내의 어떤 컴퓨터(PC)인지 표현하는 부분

* 어떤 네트워크인지 표현하는 부분을 **네트워크 ID**라고 하고 어떤 PC인지 표현하는 부분을 **호스트 ID**라고 한다.

* IP 주소의 32비트 중 어디까지가 네트워크 ID이고 어디까지가 호스트ID인지는 IP주소의 종류에 따라 달라진다.  ▶ IP주소의 종류를 알아보자     

  

#### IP주소의 종류

* 네트워크 규모에 따라 A,B,C클래스로 나뉘고 그 밖에 멀티캐스트 주소인 D클래스, 연구 및 특수 용도인 E클래스로 나뉜다. (A가 가장 크고 C가 가장 작은 규모)

  * 멀티캐스트: 한 컴퓨터에서 패킷을 여러 컴퓨터로 동시에 전송하는 것을 말한다.

* A클래스의 경우 처음 8비트가 네트워크 ID고 나머지 24비트가 호스트 ID

* B클래스의 경우 처음 16비트가 네트워크 ID고 나머지 16비트가 호스트 ID

* C클래스의 경우 처음 24비트가 네트워크 ID고 나머지 8비트가 호스트 ID

* 호스트ID가 개인 PC를 나타내는 부분이라는 점을 떠올린다면, 클래스별로 네트워크 규모가 나뉜다는 것을 알 수 있다.     

  



#### 특별한 IP 주소 두 가지

* 컴퓨터나 라우터가 IP로 사용하면 안되는 주소 두 가지가 있다.

  * **네트워크 주소**와 **브로드캐스트 주소**

* 네트워크 주소

  * 형태: 호스트 ID가 십진수로 0인 주소 
    (예시_192.168.1.**0**)
  * 사용용도: 로컬네트워크를 나타내는 대표 주소
    (**192.168.1.0**이 로컬 네트워크 주소고 **192.168.1.1**~**192.168.1.6**은 네트워크 안에 있는 PC)

* 브로드캐스트 주소

  * 형태: 호스트 ID가 십진수로 255인 주소
    (예시_192.168.1.**255**)

  * 로컬 네트워크 내부에 있는 모든 컴퓨터에 데이터를 전송해야 할 때 사용되는 전용 IP주소    


​    

#### 인터넷 프로토콜의 주소를 효율적으로 사용하는 방법에는 대표적으로 2가지가 있다.

* **DHCP(Dynamic Host Configuration Protocol)**, **NAT(Network Address Translation)**





#### 대규모 로컬 네트워크를 작은 네트워크로 나눈 Subnet

* 로컬 네트워크를 작은 규모의 네트워크로 분할하는 것을 서브넷팅(subneting)이라고 한다.
* 이렇게 분할된 작은 네트워크를 subnet(서브넷)이라고 한다.
* 호스트 ID중 일부를 서브넷 ID로 사용해서 서브넷 IP 주소를 표현한다. 
  * A클래스 IP주소의 경우 **네트워크ID(8비트)** + **호스트ID(24비트)** 로 이루어져 있는데,
    서브넷은 이를 **네트워크ID** + **서브넷ID** + **호스트ID** 로 표현하게 된다. 

* 서브넷 마스크

  * 네트워크 ID와 서브넷 ID를 식별할 수 있도록 알려주는 것으로, 2가지 단계로 만들어진다.

    (1) 클래스A ~ C를 구분하기위해 네트워크 ID는 255로 표현하고 호스트 ID는 0으로 표현한다.
    	  \- 클래스A: 255.0.0.0
    	  \- 클래스B: 255.255.0.0
    	  \- 클래스C: 255.255.255.0

    (2) **네트워크ID와 서브넷ID가 차지하는 비트의 수**를 IP주소 뒤에 **/기호를 사용해서 표현**해준다.
           \- 예를들면 255.255.255.0/28인 경우, 클래스 C의 IP주소로 네트워크ID와 서브넷ID가 
             총 28비트를 사용한다는 것이다. 
           \- 클래스C는 원래 네트워크ID에 24비트를 사용하므로 4비트가 서브넷ID에 사용되었음을 알 수 있다. 
           \- 호스트 ID는 8비트였는데 서브넷ID에게 4비트를 사용하라고 주었으므로 결과적으로 4비트가 된다.       

    

> Subnet은 컴퓨터 공학에서 흔히 쓰이는 **“Divide and Conquer"** 전략이 사용된 하나의 예시라고 볼 수 있다. 
>
>  ▶ ‘Divide and Conquer’는 대상이 거대해서 발생하는 문제에 대해 대상을 작게 나눠 해결하는 전략을 의미

​     



#### CIDR(Classless Inter-Domain Routing) 

* Subnet이 기존 IP 주소 체계가 세부적인 (시설) 구분 기능을 제공하지 않는다는 문제의식에서 탄생한 것과 같이 CIDR 역시 기존 IP 주소 체계의 한계에서 출발하였다.

* 다만 CIDR는 Subnet과는 전혀 다른 IP 주소의 한계점에서 기인한 것이고 사실 CIDR는 Subnet과 반대되는 성격의 방식으로 볼 수 있다.

* CIDR의 탄생 배경

  * 기존의 IP 주소 Class로 대형 시설에 대한 IP 주소를 할당할 때, 그 수가 많이 제한적이라는 문제가 있다.

    → 일례로 Class A의 경우 단지 128개의 시설에만 할당이 가능하고 Class B는 16000개만 가능하다

  * 이러한 문제의식에서 출발해서 연속되는 여러 개의 네트워크 ID를 묶어서 하나의 시설(기관)을 나타내는 것으로 인식하는 방식이 바로 CIDR이다.

  * 쉽게 말해서 작은 Class 덩어리를 뭉쳐서 보다 큰 네트워크를 표현하는데 사용하자는 것이다.

* 요약

  ▶ 하나의 IP 주소를 세분화하는 작업이 Subnet이라면 CIDR은 여러개의 IP 주소를 하나로 묶는 작업이다.

  ▶ **Subneting**과 반대되는 기능을 수행하기에 이러한 의미에서 CIDR을 ‘**Supernetting**’ 이라 부르기도 한다.

  ​    





​      

#### 라우터

* 라우터는 로컬 네트워크를 분리할 수 있다. (스위치는 네트워크를 분리할 수 없음)

* 라우터로 나뉜 하나의 로컬 네트워크의 PC에서 다른 로컬 네트워크의 PC로 접속하려면 
  (두 PC가 공동으로 연결된) 라우터의 IP주소를 사용해야 한다.
  * 라우터의 IP주소를 기본 게이트웨이(default gateway)라고 부른다.
  * PC는 네트워크에 연결될 때, 자동으로 라우터의 IP주소를 받아서 기본 게이트웨이로 설정한다.

* 라우터 안에는 **라우팅 테이블**이 존재해서 하나의 PC에서 다른 PC로 데이터를 전송해 줄 수 있다. 
* 라우터는 다른 라우터들과 **라우팅 프로토콜**에 따라 라우팅 테이블 정보를 교환하여 각자의 라우팅 테이블을 업데이트 한다. 
  * 대표적인 라우팅 프로토콜에는 RIP, OSPF, BGP 등이 있다. 

​     



#### 라우팅과 포워딩

* 라우팅: 출발지에서 목적지까지 총 경로를 설정하는 것을 의미한다. (마포-공덕-애오개-충무로-서대문-광화문)

  → **Routing Algorithm**에 의해서 총 경로가 설정된다.

* 포워딩: 각 경로에서 다음 경로로 이동하는 것을 의미한다. (마포-공덕 or 서대문-광화문)

  → 목적지의 IP 주소에 따라 datagram을 바로 다음에 어떤 라우터로 보낼지 결정해서 보내는 것

​      



#### 기존의 Router   vs    (최근에 등장한) Sofeware Defined Network (SDN)

* 기존의 네트워크 계층에서는 라우팅과 포워딩이 Router라는 하나의 동일한 시스템 내에서 이루어졌다.

* 반면에 최근에 나온 SDN에서는 라우팅과 포워딩이 분리되어서 라우팅의 경우 독립된 중앙관리시스템으로 존재한다.

* 중앙관리시스템에서는 전체 네트워크 정보를 모아 최적의 전달 경로를 만들고 이를 각 라우터에 알려준다.

* SDN에서 라우터(스위치)는 중앙관리시스템의 지시에 따라 단순히 포워딩 역할만 수행한다. 

  ​    