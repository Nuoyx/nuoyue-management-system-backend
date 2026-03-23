package org.nuoyue.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.nuoyue.mapper.OperateLogMapper;
import org.nuoyue.pojo.OperateLog;
import org.nuoyue.utils.CurrentHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(org.nuoyue.anno.Log)")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Enter the operation log aspect, method: {}", joinPoint.getSignature().toShortString());
        long startTime = System.currentTimeMillis();
        // Execute the target method
        Object result = joinPoint.proceed();
        // Calculate execution time
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;


        // Create and save the operation log
        OperateLog olog = new OperateLog();
        olog.setOperateEmpId(getCurrentUserId());
        // Set other log details
        olog.setOperateTime(LocalDateTime.now());
        olog.setMethodName(joinPoint.getSignature().getName());
        olog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        olog.setReturnValue(result != null ? result.toString() : "void");
        olog.setCostTime(costTime);


        // Save the log to the database
        operateLogMapper.insert(olog);
        // Return the result of the target method
        return result;
    }

    private Integer getCurrentUserId() {
        return CurrentHolder.getCurrentId();
    }
}
