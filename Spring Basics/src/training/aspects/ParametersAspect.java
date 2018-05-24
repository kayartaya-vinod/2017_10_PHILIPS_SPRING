package training.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ParametersAspect {

	@Around("execution(* training..ProductDao.*(double, double))")
	public Object checkAndSwapParams(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		double p1 = Double.parseDouble(args[0].toString());
		double p2 = Double.parseDouble(args[1].toString());

		Object obj;
		if (p1 > p2) {
			obj = pjp.proceed(new Object[] { p2, p1 });
		} else {
			obj = pjp.proceed();
		}

		return obj;
	}

}
