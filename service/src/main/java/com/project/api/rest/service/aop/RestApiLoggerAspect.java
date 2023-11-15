package com.project.api.rest.service.aop;

import com.project.api.rest.model.dto.PostDto;
import com.project.api.rest.model.entity.Comment;
import com.project.api.rest.model.exception.EntityNotFoundException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Aspect
@Configurable
@Component
public class RestApiLoggerAspect {
    Logger logger = LoggerFactory.getLogger(RestApiLoggerAspect.class);

    @After("@annotation(com.project.api.rest.service.aop.RestApiLogger)")
    public void logBrmsExecution(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        Object arg = joinPoint.getArgs()[0];
        int objectId;
        if(arg instanceof PostDto){
            objectId = ((PostDto) arg).getId();
        }else{
            objectId = ((Comment) arg).getId();
        }

        logger.info("Method [" + method + "] gets called and entity saved with id : " + objectId);
    }

    @AfterThrowing(value = "execution(* com.project.api.rest.service.api.PostService.getPostById(..))",
            throwing = "exception")
    public void logRestApiException(JoinPoint joinPoint, EntityNotFoundException exception) {
        logger.error("An exception has been thrown in " + joinPoint.getSignature().getName() + " () - exception: " + exception.getMessage());
    }
}