package cz.vsb.vea;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* cz.vsb.vea.controllers.api.*.*(..))")
    private void api(){}

    @Before("api()")
    public void logBefore(JoinPoint joinPoint){
        System.out.println("Class: " + joinPoint.getSignature().getDeclaringTypeName() + " Method: " +  joinPoint.getSignature().getName());
    }
}
