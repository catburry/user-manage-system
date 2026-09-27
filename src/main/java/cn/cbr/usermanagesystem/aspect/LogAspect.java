package cn.cbr.usermanagesystem.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
    @Before("execution(* cn.cbr.usermanagesystem..*.*(..))")
    public void before(JoinPoint joinPoint){
        System.out.println("请求进入");
        System.out.println("方法名："+joinPoint.getSignature().getName());
    }
}
