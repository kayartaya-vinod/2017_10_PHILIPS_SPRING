package training.programs;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import training.dao.DaoException;
import training.dao.ProductDao;
import training.entity.Product;

public class P06_TestJdbcTemplateProductDao {

	public static void main(String[] args) throws DaoException {
		
		ClassPathXmlApplicationContext ctx =
				new ClassPathXmlApplicationContext("context3.xml");
		
		ProductDao dao = ctx.getBean("jdbcTemplateProductDao", ProductDao.class);

		Product p = new Product();
		p.setName("Test product");
		p.setDescription("Test description");
		p.setUnitPrice(123.45);
		
		dao.addProduct(p);
		
		System.out.println("New product id is " + p.getId());
		ctx.close();
	}
}








