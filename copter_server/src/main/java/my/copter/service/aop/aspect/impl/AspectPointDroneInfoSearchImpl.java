package my.copter.service.aop.aspect.impl;

import lombok.AllArgsConstructor;
import my.copter.service.aop.aspect.AspectPointDroneInfoSearch;
import my.copter.service.aop.process.DroneInfoSearchProcessService;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;

@Aspect
@Component
@AllArgsConstructor
public class AspectPointDroneInfoSearchImpl implements AspectPointDroneInfoSearch {

    private final DroneInfoSearchProcessService service;

    @Override
    @Pointcut("execution(* my.copter.facade.pdp.DronePDPFacade.findById(..))")
    public void pointcut() {}

    @Override
    @Around(value = "pointcut()")
    public Object doAspect(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        if (ArrayUtils.isNotEmpty(args)) {
            Long id = (long) args[0];
            service.saveRequestToDronePdp(id);
        }
        return point.proceed();
    }
}
