package my.copter.service.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public interface AspectPointDroneInfoSearch {
    void pointcut();
    Object doAspect(ProceedingJoinPoint point) throws Throwable;
}
