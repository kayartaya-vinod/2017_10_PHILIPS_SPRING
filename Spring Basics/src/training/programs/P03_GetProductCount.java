package training.programs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import training.cfg.AppConfig1;
import training.dao.DaoException;
import training.dao.ProductDao;

public class P03_GetProductCount {

	public static void main(String[] args) throws DaoException {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig1.class);
		String user = ctx.getBean("username", String.class);
		
		System.out.println("user = " + user);
		
		ProductDao dao = (ProductDao) ctx.getBean("dao");
		int pc = dao.getProductCount();
		System.out.printf("There are %d products\n", pc);
		((AbstractApplicationContext) ctx).close();
	}
}
