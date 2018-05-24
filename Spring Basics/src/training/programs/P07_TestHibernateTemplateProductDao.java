package training.programs;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import training.cfg.AppConfig2;
import training.dao.ProductDao;
import training.entity.Product;

public class P07_TestHibernateTemplateProductDao {

	public static void main(String[] args) throws Exception {

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig2.class);

		ProductDao dao = ctx.getBean("dao", ProductDao.class);
		System.out.println("dao is an instanceof " + dao.getClass());

		Product p = dao.getProduct(55);
		System.out.println("Before update: " + p);

		p.setUnitPrice(p.getUnitPrice() + 1);
		dao.updateProduct(p);
		
		p = dao.getProduct(55);
		System.out.println("After update: " + p);

		List<Product> list = dao.getProductsByBrand(1);
		System.out.printf("There are %d products in 'Fresho' brand\n", list.size());

		// -------------
		double min = 20, max = 10;
		list = dao.getProductsByPrice(min, max);
		System.out.printf("There are %d products between Rs.%.2f and Rs.%.2f\n", list.size(), min, max);

		min = 10;
		max = 20;
		list = dao.getProductsByPrice(min, max);
		System.out.printf("There are %d products between Rs.%.2f and Rs.%.2f\n", list.size(), min, max);
		// -------------

		int pc = dao.getProductCount();
		System.out.printf("There are %d products\n", pc);

		ctx.close();

	}
}
