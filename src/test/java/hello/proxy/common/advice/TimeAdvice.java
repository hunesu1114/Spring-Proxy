package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
/**
 * 꼭 aopalliance 패키지의 MethodInterceptor 를 사용해야함
 */
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

//        Object result=method.invoke(target, args);
        /**
         * ProxyFactory 생성할 때 target을 넘기기 때문에 여기서는 target정보 필요x
         */
        Object result = invocation.proceed();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeProxy 종료 resultTime={}ms", resultTime);

        return result;
    }
}
