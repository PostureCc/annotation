package com.chan.aspect;

import com.chan.annotation.user.UserAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAspect.class);

    //定义切点
    @Pointcut("@annotation(com.chan.annotation.user.UserAnnotation)")
    public void addAdvice() {
    }

//    @Pointcut("execution(* com.chan.controller.*.*(..)) && @annotation(com.chan.annotation.user.UserAnnotation)")
//    public void addAdvice() {
//    }

    /**
     * 环绕通知 包围一个连接点的通知，类似Web中Servlet规范中的Filter的doFilter方法。可以在方法的调用前后完成自定义的行为，也可以选择不执行。
     */
    @Around(value = "addAdvice()")
    public void after(ProceedingJoinPoint pjp) throws Throwable {
        UserAnnotation userAnnotation = ((MethodSignature) pjp.getSignature()).getMethod().getAnnotation(UserAnnotation.class);
//        String[] str = userAnnotation.value();
//        for (int i = 0; i < str.length; i++) {
//            LOGGER.info("userValue:{}", str[i]);
//        }
        LOGGER.info("role:{},token:{}", userAnnotation.role(), userAnnotation.token().toString());
        //调用目标(原)方法 如果在前面需要做取值操作则该方法需要最后调用
        pjp.proceed();

    }

}
