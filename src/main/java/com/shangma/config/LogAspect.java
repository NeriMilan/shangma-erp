package com.shangma.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.shangma.entity.system.Log;
import com.shangma.entity.system.User;
import com.shangma.service.system.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/**
 * @ClassName:LogAspect
 * @Description: TODO
 * @Author:
 * @Date:2021/11/23 11:49
 * @Version: JDK1.8
 */
@Aspect
@Component
public class LogAspect {
    @Autowired
    private LogService logService;
    @Autowired
    private HttpServletRequest request;

    @Around("execution(* com.shangma.service..*.*(..))&&!execution(* com.shangma.service.system.impl.UserServiceImpl.listByLoginNameAndPassword(..))&&!execution(* com.shangma.service.sale.b2c.base.impl.BaseServiceImpl.insert(..))&&!execution(* com.shangma.service.system.impl.UserServiceImpl.getById(..))&&!execution(* com.shangma.service.system.impl.PermissionServiceImpl.getDownloadRows(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        User user= (User)request.getSession().getAttribute("user");
        Log log = new Log();
        log.setUserId(user.getId());
        log.setUserName(user.getUsername());
        log.setIp(request.getRemoteHost());
        MethodSignature signature =(MethodSignature) proceedingJoinPoint.getSignature();
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.setMethod(className+"."+methodName+"()");
        Object[] args = proceedingJoinPoint.getArgs();
        Object[] argsNew = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest||args[i] instanceof ServletResponse||args[i] instanceof MultipartFile)
                continue;
            argsNew[i]=args[i];
        }
        String s = Arrays.toString(argsNew);
        log.setArgs(s);
        logService.insert(log);
        return proceedingJoinPoint.proceed();
    }
}
