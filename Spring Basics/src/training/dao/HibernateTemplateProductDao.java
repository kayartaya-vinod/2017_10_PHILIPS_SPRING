package training.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import training.entity.Product;

@SuppressWarnings("unchecked")
@Repository("dao")
public class HibernateTemplateProductDao implements ProductDao {

	@Autowired(required = false)
	private HibernateTemplate template;

	@Override
	public void addProduct(Product product) throws DaoException {
		try {
			template.save(product);
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Product getProduct(int id) throws DaoException {
		try {
			return template.get(Product.class, id);
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void updateProduct(Product product) throws DaoException {
		if(product.getUnitPrice()<0){
			throw new DaoException("Price is " + product.getUnitPrice() + "! cannot be negative.");
		}
		try {
			template.update(product);
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void deleteProduct(int id) throws DaoException {
		Product p = this.getProduct(id);
		if (p == null) {
			throw new DaoException("Invalid id for deletion: " + id);
		}
		try {
			template.delete(p);
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int getProductCount() throws DaoException {
		System.out.println("**** inside the getProductCount()");
		try {
			Object obj = template.find("select count(p) from Product p").get(0);
			return new Integer(obj.toString());
		} catch (DataAccessException | NumberFormatException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<Product> getAllProducts() throws DaoException {
		try {
			return (List<Product>) template.find("from Product");
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<Product> getProductsByPrice(double min, double max) throws DaoException {
		try {
			return (List<Product>) template.find("FROM Product WHERE unitPrice BETWEEN ? AND ?", min, max);
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<Product> getProductsByBrand(int brandId) throws DaoException {
		try {
			return (List<Product>) template.find("FROM Product WHERE brandId = ?", brandId);
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<Product> getProductsByCategory(int categoryId) throws DaoException {
		try {
			return (List<Product>) template.find("FROM Product WHERE categoryId = ?", categoryId);
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public byte[] getPicture(int id) throws DaoException {
		throw new DaoException("Method not implemtned");
	}

}
