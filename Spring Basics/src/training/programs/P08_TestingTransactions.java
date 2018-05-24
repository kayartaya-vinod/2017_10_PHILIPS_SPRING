package training.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import training.cfg.AppConfig2;
import training.dao.DaoException;
import training.dao.ProductDao;
import training.service.ProductService;

public class P08_TestingTransactions {

	public static void main(String[] args) throws DaoException {

		AnnotationConfigApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig2.class);

		ProductService ps = ctx.getBean(ProductService.class);

		 ps.updateProductPriceBulk(-20);
		 System.out.println("Prices updated!");

		ProductDao dao = ctx.getBean("dao", ProductDao.class);
		// dao.deleteProduct(55); // error; no transaction started
		// ps.deleteProduct(56);

		System.out.println("Product deleted!");
		ctx.close();

	}
}
