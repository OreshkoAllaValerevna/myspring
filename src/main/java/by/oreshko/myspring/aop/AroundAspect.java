package by.oreshko.myspring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class AroundAspect {

    @Pointcut("@annotation(AroundAnnotation)")

    public void callAroundPersonController() {
    }

    @Around("callAroundPersonController()")
    public Object aroundMethod(ProceedingJoinPoint jp) throws Throwable{
        long start = System.currentTimeMillis();
        Object proceed = jp.proceed();
        long executionTime = System.currentTimeMillis() - start;

        log.info(jp.toString() + " выполнен за " + executionTime + " мс");

        return proceed;
    }

}
