package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long srt = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try{
            return joinPoint.proceed();
        } finally {
            long est_time = System.currentTimeMillis() - srt;

            System.out.println("START: " + joinPoint.toString() + " " + est_time + "ms");
        }
    }
}
