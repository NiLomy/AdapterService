package ru.lobanov.adapter.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Aspect which provides logging logic into {@code Service} methods.
 */
@Slf4j
@Aspect
@Component
public class LoggingAspect {

    public static final String LOGGING_BUSINESS_LOGIC_START_MESSAGE = "Call method: {}.{} with args: {}.";
    public static final String LOGGING_BUSINESS_LOGIC_END_MESSAGE = "Execution time of {}.{} = {} ms with return: {}.";
    public static final String LOGGING_EXCEPTION_OCCURRED_MESSAGE = "Exception at: {}.";
    public static final String LOGGING_EXCEPTION_DETAILS_MESSAGE = "Exception details: {}";

    /**
     * Pointcut for {@code Service} methods.
     */
    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void serviceMethod() {
    }

    /**
     * Advice for logging actions in business logic.
     *
     * @param joinPoint specific join point
     * @return methods return value
     */
    @Around(value = "serviceMethod()")
    public Object logMethodCalling(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getName();
        String methodName = methodSignature.getName();
        Object[] argsOfMethod = joinPoint.getArgs();

        log.info(LOGGING_BUSINESS_LOGIC_START_MESSAGE, className, methodName, argsOfMethod);

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        stopWatch.stop();
        log.info(LOGGING_BUSINESS_LOGIC_END_MESSAGE, className, methodName, stopWatch.getTotalTimeMillis(), result);
        return result;
    }

    /**
     * Advice for logging exceptions in business logic.
     *
     * @param joinPoint specific join point
     * @param exception throwing exception
     */
    @AfterThrowing(value = "serviceMethod()", throwing = "exception")
    public void logExceptionThrowing(JoinPoint joinPoint, Exception exception) {
        log.warn(LOGGING_EXCEPTION_OCCURRED_MESSAGE, joinPoint.getSignature().getName());
        log.warn(LOGGING_EXCEPTION_DETAILS_MESSAGE, exception.toString());
    }
}
