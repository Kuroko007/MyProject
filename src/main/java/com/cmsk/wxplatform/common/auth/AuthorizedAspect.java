package com.cmsk.wxplatform.common.auth;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.lang.annotation.Annotation;

/**
 * @author zhanglgstart
 * @create 2021-03-08 11:14
 */
@Aspect
@Component
public class AuthorizedAspect {
    private final AuthContext authContext;

    public AuthorizedAspect(AuthContext authContext) {
        this.authContext = authContext;
    }

    @Pointcut("execution(* *..wx.controller.*Controller.*(..))")
    public void methodPointCut() {
    }

    @Before("methodPointCut() && @annotation(authorized)")
    public void doBefore(JoinPoint joinPoint, Authorized authorized)
            throws MissingServletRequestParameterException {
        if (!isClassValidateAuthorization(joinPoint)) {
            authContext.verifyToken();
        }
    }

    @Before("methodPointCut()")
    public void doBefore(JoinPoint joinPoint) throws MissingServletRequestParameterException {
        if (isClassValidateAuthorization(joinPoint)) {
            authContext.verifyToken();
        }
    }

    private boolean isClassValidateAuthorization(JoinPoint joinPoint) {
        Annotation[] annotations = getAuthorizedAnnotations(joinPoint);
        return annotations != null && annotations.length > 0;
    }

    private Annotation[] getAuthorizedAnnotations(JoinPoint joinPoint) {
        Class type = joinPoint.getSignature().getDeclaringType();
        return type.getAnnotationsByType(Authorized.class);
    }
}
