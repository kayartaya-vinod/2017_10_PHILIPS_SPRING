package training.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import training.entity.Product;

@Transactional(readOnly = true)
public interface ProductDao {

	// CRUD OPERATIONS
	@Transactional(readOnly = false)
	public void addProduct(Product product) throws DaoException;

	public Product getProduct(int id) throws DaoException;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateProduct(Product product) throws DaoException;

	@Transactional(readOnly = false, propagation = Propagation.MANDATORY)
	public void deleteProduct(int id) throws DaoException;

	// QUERIES

	public int getProductCount() throws DaoException;

	public List<Product> getAllProducts() throws DaoException;

	public List<Product> getProductsByPrice(double min, double max) throws DaoException;

	public List<Product> getProductsByBrand(int brandId) throws DaoException;

	public List<Product> getProductsByCategory(int categoryId) throws DaoException;

	public byte[] getPicture(int id) throws DaoException;
}
