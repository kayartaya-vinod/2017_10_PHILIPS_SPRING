package training.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

	public LoggerAspect() {
		System.out.println("LoggerAspect instantiated!");
	}

	@Pointcut("execution(* training.dao.ProductDao.getProduct(..))")
	public void pc1() {
	}

	@Pointcut("execution(* training.dao.ProductDao.getProductCount(..))")
	public void pc2() {
	}

	@Before("pc1() || pc2()")
	public void logBefore(JoinPoint jp) {
		System.out.printf(">>> before going to the %s() function\n", jp.getSignature().getName());
	}

	@After("pc1()")
	public void logAfter(JoinPoint jp) {
		System.out.printf("<<< after coming from the %s() function\n", jp.getSignature().getName());
	}
}
