package training.programs;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import training.dao.DaoException;
import training.dao.ProductDao;
import training.entity.Product;

public class P05_TestJdbcTemplateProductDao {

	public static void main(String[] args) throws DaoException {
		
		ClassPathXmlApplicationContext ctx =
				new ClassPathXmlApplicationContext("context3.xml");
		
		ProductDao dao = ctx.getBean("jdbcTemplateProductDao", ProductDao.class);

		// dao.deleteProduct(22);
		
		Product p = dao.getProduct(23);
		System.out.println(p);
		p.setUnitPrice(p.getUnitPrice() + 5);
		dao.updateProduct(p);
		p = dao.getProduct(23);
		System.out.println(p);
		
		List<Product> list = dao.getAllProducts();
		System.out.println("Total product count = " + list.size());
		
		list = dao.getProductsByPrice(10, 20);
		System.out.println("Total product count between Rs.10 and Rs.20 = " + list.size());
		
		list = dao.getProductsByCategory(1); 
		System.out.printf("There are %d vegitables\n", list.size());

		list = dao.getProductsByBrand(1); 
		System.out.printf("There are %d products by brand 'Fresho'\n", list.size());
		
		ctx.close();
	}
}








