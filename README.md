# Spring-Proxy
프록시 패턴, 데코레이터 패턴 그리고 Spring AOP에 대해 공부한다

## [0523]

**프록시 패턴** 및 **데코레이터 패턴**을 학습하기 위한 예제 프로젝트 작성

- v1 : 인터페이스와 그 구현체 + 스프링 빈 직접 등록
- v2 : 인터페이스x 구현체만 + 스프링 빈 직접 등록
- v3 : 구현체만 + 스프링 빈 Component Scan 되도록

## [0525]

**프록시 패턴** 학습

- 프록시 객체는 캐시 개념. Client 객체가 어떤 작업을 수행하는 메서드를 불러올 때, Proxy객체에서 한번 체크하고 데이터가 있으면 빠르게 반환, 없으면 최초 1회 원래 클래스의 메서드 호출한 뒤 Proxy객체의 필드에 저장. 두번째 부터는 원래 클래스의 메서드를 부를 필요 없이 빠르게 데이터 반환.

![image](https://github.com/hunesu1114/Spring-Proxy/assets/114369093/b09cf039-9793-4ea1-94c7-b25c34a80de5)

![image](https://github.com/hunesu1114/Spring-Proxy/assets/114369093/89e1e585-0910-4ff3-88fd-c485a6c4fd39)

- 데코레이터 패턴의 의도는 기능 추가. 프록시 패턴과 흡사한 구조이다.

![image](https://github.com/hunesu1114/Spring-Proxy/assets/114369093/0fe0ee30-6621-434e-a350-c433d44619b2)

![image](https://github.com/hunesu1114/Spring-Proxy/assets/114369093/51661a2e-7bcd-425c-b9f3-769978345ed2)


## [0608]
**프록시 팩토리, 포인트컷, 어드바이스, 어드바이저** 학습

- 프록시 패턴에 이어 동적 프록시에 대해 공부함
- 인터페이스를 implements 한 클래스에 대해 프록시를 적용하려면 JDK동적 프록시를 적용하면 됨
- 구체클래스(인터페이스가 없는)에 프록시를 적용하려면 CGLIB 프록시 적용하면 됨
- 그러면 인터페이스 있고 없고에 따라 다른 프록시 적용하는것, 그리고 일일이 프록시 만드는것 너무 고달픔
- 이때 프록시 팩토리를 사용하면됨. 프록시 팩토리는 위 두 방법을 추상화 한 인터페이스임. 프록시팩토리에서 적용할 클래스가 JDK프록시 적용인지, CGLIB적용인지 판단을 하고 호출을 위임함
![image](https://github.com/hunesu1114/Spring-Proxy/assets/114369093/af5752e1-a4ec-4bef-b89b-ba8a02622a71)

-위 사진의 Advice 는 사실 다음에 배울 Advisor의 편의 버전임. 어떤 편의냐면 포인트컷이 항상 Pointcut.True로 설정되어있음
- 조금 더 심화 -> advice는 우리가 추가할 부가기능 로직이 들어있는 클래스고, pointcut은 그 부가기능 로직을 어떤 클래스 어떤 메서드에 적용하고 적용하지 않을지 설정하는 클래스임.
- 그리고 advice와 pointcut을 각각 한개씩 포함하는 개념이 Advisor임.
![image](https://github.com/hunesu1114/Spring-Proxy/assets/114369093/f995983c-bbc8-4d2a-9dc8-b0098635af3b)
- 다시 말하자면 프록시팩토리가 어드바이저를 부르고 포인트컷에서 어드바이스를 적용할지말지 판단 후 적용 또는 적용x.

![image](https://github.com/hunesu1114/Spring-Proxy/assets/114369093/07dc3e2e-e50e-4a3a-9619-a8419565d4ba)
![image](https://github.com/hunesu1114/Spring-Proxy/assets/114369093/079a9352-e377-4f6e-bb4c-c6010a662d84)

- 또한 프록시를 여러개 적용할 수도 있음(즉, 여러 advisor를 적용할 수 있다는 말임)
![image](https://github.com/hunesu1114/Spring-Proxy/assets/114369093/38af3c9b-29c6-4a9b-8dc4-6955881c6c25)

## 여기까지 배웠을 때, 남아있는 문제사항들
- 문제1 : 너무 많은 설정
ProxyFactoryConfigV1 , ProxyFactoryConfigV2 보면...Config파일 작성이 보통일이 아님
예를 들어서 애플리케이션에 스프링 빈이 100개가 있다면 여기에 프록시를 통해 부가 기능을 적용하려면 100개의 동적 프록시 생성 코드를 만들어야 함! 무수히 많은 설정 파일 때문에 설정 지옥을 경험..
심지어 요즘은 스프링 빈을 등록하기 귀찮아서 컴포넌트 스캔을 사용하는데, 이렇게 직접 등록하는 것도 모자라서, 프록시를 적용하는 코드까지 빈 생성 코드에 넣어야 한다. 

- 문제2 : 컴포넌트 스캔
애플리케이션 V3처럼 컴포넌트 스캔을 사용하는 경우 지금까지 학습한 방법으로는 프록시 적용이 불가능.
왜냐하면 실제 객체를 컴포넌트 스캔으로 스프링 컨테이너에 스프링 빈으로 등록을 다 해버린 상태이기 때문.
지금까지 학습한 프록시를 적용하려면, 실제 객체를 스프링 컨테이너에 빈으로 등록하는 것이 아니라ProxyFactoryConfigV1 에서 한 것 처럼, 부가 기능이 있는 프록시를 실제 객체 대신 스프링 컨테이너에 빈으로 등록해야 함.

## 두 가지 문제를 한번에 해결하는 방법이 바로 다음에 설명할 빈 후처리기이다.

## [0609]
**빈 후처리기** 학습
- 수많은 설정코드와 Component스캔 대상에 프록시를 적용 할 수 없었던 문제상황에 대한 해결책으로 빈 후처리기를 배움.
- 빈 후처리기는 빈들이 초기화 되기 전이나 후에 빈이 빈 팩토리에 저장되기 직전에 빈을 조작해주는 인터페이스임.

![image](https://github.com/hunesu1114/Spring-Proxy/assets/114369093/b6950cb4-7444-4b2b-8f3e-c2377a789c33)

- 빈 후처리기로 빈을 심지어 바꿔치기 할 수도 있음.

- 그럼 빈을 조작할 이유는 현재로서는 한가지, 빈 객체를 저장할 때, 원본객체 말고 조작을 해서 프록시를 저장해버리는거임.

![image](https://github.com/hunesu1114/Spring-Proxy/assets/114369093/0e996122-3073-463a-97b5-1461426a3472)

-여기까지 BeanPostProcessor를 implements 하여 빈 후처리기를 직접 만들어봤는데, 스프링부트는 자동으로 처리할 수 있는 기능을 제공한다.

![image](https://github.com/hunesu1114/Spring-Proxy/assets/114369093/b1c2dc38-2593-4934-86c4-60158c9171e1)

![image](https://github.com/hunesu1114/Spring-Proxy/assets/114369093/869e1941-02b5-475b-a7ed-1bf382620d61)

- 심지어 여러 advisor를 적용하기도 가능함

![image](https://github.com/hunesu1114/Spring-Proxy/assets/114369093/1ad6424d-65c2-43d0-9f5a-80c98052a7d1)

## 주요 사항!!!
## 빈 후처리기는 포인트컷을 두번 사용한다.
1. 프록시를 생성할지 말지 결정할 때, 포인트컷으로 모든 클래스와 모든 메서드를 일일이 매칭해서 하나라도 매칭되면 프록시를 만든다
2. 프록시를 만든 후, 포인트컷으로 매칭해서 해당 메서드가 포인트컷을 만족하면 프록시 반환, 아니면 원본 객체 반환

## 프록시는 반드시 하나만 생성된다. 
심지어 여러 advisor가 적용되는 상황에서도 프록시는 반드시 하나!


## [0609]
**스프링 AOP** 학습

![image](https://github.com/hunesu1114/Spring-Proxy/assets/114369093/fdb78166-d1e4-4442-96c5-5a61fbfd07ac)

![image](https://github.com/hunesu1114/Spring-Proxy/assets/114369093/6e16d70c-44bb-4e0a-b8c3-1cffedc565c7)

![image](https://github.com/hunesu1114/Spring-Proxy/assets/114369093/472b1ad3-626d-40b7-890b-c0278f1a3476)

![image](https://github.com/hunesu1114/Spring-Proxy/assets/114369093/beefc21a-375b-40f9-88e6-c87f3dccc736)

**조인 포인트(Join point)** 
- 어드바이스가 적용될 수 있는 위치, 메소드 실행, 생성자 호출, 필드 값 접근, static 메서드 접근 같은 프로그램 실행 중 지점
- 조인 포인트는 추상적인 개념이다. AOP를 적용할 수 있는 모든 지점이라 생각하면 된다.
- 스프링 AOP는 프록시 방식을 사용하므로 조인 포인트는 항상 메소드 실행 지점으로 제한된다.
**포인트컷(Pointcut)**
- 조인 포인트 중에서 어드바이스가 적용될 위치를 선별하는 기능
- 주로 AspectJ 표현식을 사용해서 지정
- 프록시를 사용하는 스프링 AOP는 메서드 실행 지점만 포인트컷으로 선별 가능
**타켓(Target)**
- 어드바이스를 받는 객체, 포인트컷으로 결정
**어드바이스(Advice)**
- 부가 기능
- 특정 조인 포인트에서 Aspect에 의해 취해지는 조치
- Around(주변), Before(전), After(후)와 같은 다양한 종류의 어드바이스가 있음
**애스펙트(Aspect)**
- 어드바이스 + 포인트컷을 모듈화 한 것
- @Aspect 를 생각하면 됨
- 여러 어드바이스와 포인트 컷이 함께 존재
**어드바이저(Advisor)**
- 하나의 어드바이스와 하나의 포인트 컷으로 구성
- 스프링 AOP에서만 사용되는 특별한 용어
**위빙(Weaving)**
- 포인트컷으로 결정한 타켓의 조인 포인트에 어드바이스를 적용하는 것
- 위빙을 통해 핵심 기능 코드에 영향을 주지 않고 부가 기능을 추가 할 수 있음
- AOP 적용을 위해 애스펙트를 객체에 연결한 상태
    컴파일 타임(AspectJ compiler)
    로드 타임
    런타임, 스프링 AOP는 런타임, 프록시 방식
**AOP 프록시**
- AOP 기능을 구현하기 위해 만든 프록시 객체, 스프링에서 AOP 프록시는 JDK 동적 프록시 또는 CGLIB 프록시이다.





