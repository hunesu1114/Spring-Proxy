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