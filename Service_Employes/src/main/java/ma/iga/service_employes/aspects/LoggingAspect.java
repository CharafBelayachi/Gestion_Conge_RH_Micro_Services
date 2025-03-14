package ma.iga.service_employes.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Component
@Aspect
@EnableAspectJAutoProxy
public class LoggingAspect {
    Logger logger = Logger.getLogger(LoggingAspect.class.getName());

//    public LoggingAspect() throws IOException {
//        logger.addHandler(new FileHandler("./logEmploye.xml"));
//        logger.setUseParentHandlers(false);
//    }

    //    @Around("execution(* ma.iga.service_employes.service..*(..))")
    @Around("@annotation(ma.iga.service_employes.aspects.Log)")
    public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long t1 = System.currentTimeMillis();
        logger.info("From Logging Aspects ... Before " + proceedingJoinPoint.getSignature());
        Object result = proceedingJoinPoint.proceed();
        logger.info("From Logging Aspects ... After " + proceedingJoinPoint.getSignature());
        long t2 = System.currentTimeMillis();
        logger.info("Duration :" + (t2 - t1));
        return result;
    }

}