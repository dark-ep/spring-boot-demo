package cn.ep.spring.boot.ch02.s05;

import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.slf4j.*;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@Aspect
public class AOPConfig {

    private Logger logger = LoggerFactory.getLogger("aop");

    @Before("target(org.springframework.boot.CommandLineRunner)")
    public void before(JoinPoint point) {
        logger.info("[Aspect] before advise");
    }

    @Around("@within(org.springframework.stereotype.Component)")
    public Object around(final ProceedingJoinPoint point) throws Throwable {
        logger.info("[Aspect] around advise");
        Object[] args = point.getArgs();
        logger.info("args:" + Arrays.asList(args));
        //调用原有方法
        Object o = point.proceed();
        logger.info("return:" + o);
        return o;
    }

    @AfterReturning("target(org.springframework.boot.CommandLineRunner)")
    public void afterReturning(JoinPoint point) {
        logger.info("[Aspect] afterReturning advise");
    }

    @AfterThrowing("execution(* cn.ep.spring.boot.ch02.s05.MyCommandLineRunner.run(..))")
    public void afterThrowing(JoinPoint point) {
        logger.info("[Aspect] afterThrowing advise");
    }

    @After("@annotation(TestAnn)")
    public void after(JoinPoint point) {
        logger.info("[Aspect] after advise");
    }
}
