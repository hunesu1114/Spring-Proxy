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

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ff6b62ed-cd2f-4970-aee8-76bc09081a97/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9eb157f6-f672-4aeb-aa03-6f216da88545/Untitled.png)
