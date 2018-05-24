package training.programs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import training.dao.DaoException;
import training.dao.ProductDao;

public class P02_GetProductCount {

	public static void main(String[] args) throws DaoException {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("context2.xml");
		ProductDao dao = (ProductDao) ctx.getBean("jdbcProductDao");
		
		System.out.println("dao is an instanceof " + dao.getClass());
		
		int pc = dao.getProductCount();
		System.out.printf("There are %d products\n", pc);
		((AbstractApplicationContext) ctx).close();
	}
}
