package cn.ep.spring.boot.ch03.s05.dynamic;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DynamicDataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Pointcut("execution( * cn.ep.spring.boot.*.*.service.*.*(..))")
    public void serviceAspect() {
    }

    @Before("serviceAspect()")
    public void switchDataSource(JoinPoint point) {
        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();

        //获得访问的方法名
        String methodName = point.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
        String dataSourceKey = DataSourceConfigurer.DS_KEY_DEFAULT;
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);

            // 判断是否存在@DataSource注解
            if (method.isAnnotationPresent(DataSource.class)) {
                DataSource annotation = method.getAnnotation(DataSource.class);
                // 取出注解中的数据源名
                dataSourceKey = annotation.value().name();
                logger.info("Annotation DataSource is [{}]", dataSourceKey);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Set DataSource to [{}]", dataSourceKey);
        //存在该数据源名
        if (DynamicDataSourceContextHolder.containDataSourceKey(dataSourceKey)
                //该数据源名不是当前数据源
                && !dataSourceKey.equalsIgnoreCase(DynamicDataSourceContextHolder.getDataSourceKey())) {
            DynamicDataSourceContextHolder.setDataSourceKey(dataSourceKey);
            logger.info("Switch DataSource to [{}] in Method [{}]",
                    DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
        }
    }

    @After("serviceAspect())")
    public void restoreDataSource(JoinPoint point) {
        logger.info("Restore DataSource to [{}] in Method [{}]",
                DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
        DynamicDataSourceContextHolder.clearDataSourceKey();
    }

}
