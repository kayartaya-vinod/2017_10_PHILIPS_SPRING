package training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import training.dao.DaoException;
import training.dao.ProductDao;
import training.entity.Product;

@Service
public class ProductService {
	@Autowired
	ProductDao dao;
	
	@Transactional
	public void deleteProduct(int id) throws DaoException {
		dao.deleteProduct(id);
	}

	@Transactional(rollbackFor = { DaoException.class })
	public void updateProductPriceBulk(double amount) throws DaoException {

		Product p1 = dao.getProduct(1);
		Product p2 = dao.getProduct(2);
		Product p3 = dao.getProduct(3);
		Product p4 = dao.getProduct(4);

		// any of the following trowing an exception will result in the transaction 
		// started by this function to rollback
		p1.setUnitPrice(p1.getUnitPrice() + amount);
		p2.setUnitPrice(p2.getUnitPrice() + amount);
		p3.setUnitPrice(p3.getUnitPrice() + amount);
		p4.setUnitPrice(p4.getUnitPrice() + amount);
		
		dao.updateProduct(p1);
		dao.updateProduct(p2);
		dao.updateProduct(p3);
		dao.updateProduct(p4);
		
	}
}







