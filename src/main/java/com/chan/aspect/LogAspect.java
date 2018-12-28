package com.chan.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Aspect:开启切面
 * @Component:将这个类实例化到Spring容器中 相当于在容器中定义了<bean/>
 * */
@Aspect
@Component
public class LogAspect {

    @Autowired
    HttpServletRequest request;

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.chan.controller.*.*(..))")
    public void addAdvice() {
    }

    /**
     * 前置增强实现日志记录
     */
    @Before("addAdvice()")
    public void before(JoinPoint jp) throws Throwable {
        LOGGER.info("url:{} method:{}", request.getRequestURL(), request.getMethod());
        LOGGER.info("Client IP:{} Port:{}", request.getRemoteAddr(), request.getRemotePort());
        LOGGER.info("class:{}.{}", jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName());
        LOGGER.info("params:{}", Arrays.toString(jp.getArgs()));
    }

    /**
     * 后置增强实现返回值记录
     */
    @AfterReturning(pointcut = "addAdvice()", returning = "resp")
    public void afterReturning(Object resp) {
        LOGGER.info("resp:{}", resp);
    }

//    /**
//     * 异常捕获
//     */
//    @AfterThrowing(value = "addAdvice()", throwing = "e")
//    public void afterThrowing(JoinPoint joinPoint, Exception e) {
//        LOGGER.error(e.getMessage(), e);
//    }

}
