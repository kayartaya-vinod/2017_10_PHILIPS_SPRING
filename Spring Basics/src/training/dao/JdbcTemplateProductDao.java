package training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import training.entity.Product;

@Repository
public class JdbcTemplateProductDao implements ProductDao {

	@Autowired(required = false)
	private JdbcTemplate template;

	private RowMapper<Product> rowMapper = (rs, index) -> {
		Product product = new Product();
		product.setId(rs.getInt("id"));
		product.setName(rs.getString("name"));
		product.setPicture(rs.getString("picture"));
		product.setQuantityPerUnit(rs.getString("quantity_per_unit"));
		product.setDescription(rs.getString("description"));
		product.setBrandId(rs.getInt("brand_id"));
		product.setCategoryId(rs.getInt("category_id"));
		product.setUnitPrice(rs.getDouble("unit_price"));
		product.setDiscount(rs.getDouble("discount"));
		return product;
	};

	@Override
	public void addProduct(Product product) throws DaoException {
		String sql = "insert into products (category_id, name, brand_id, "
				+ "description, quantity_per_unit, unit_price, " + "picture, discount) values (?,?,?,?,?,?,?,?)";

		KeyHolder kh = new GeneratedKeyHolder();
		template.update(conn -> {
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, product.getCategoryId());
			stmt.setString(2, product.getName());
			stmt.setInt(3, product.getBrandId());
			stmt.setString(4, product.getDescription());
			stmt.setString(5, product.getQuantityPerUnit());
			stmt.setDouble(6, product.getUnitPrice());
			stmt.setString(7, product.getPicture());
			stmt.setDouble(8, product.getDiscount());
			return stmt;
		}, kh);
		int id = kh.getKey().intValue();
		product.setId(id);
	}

	class PSC implements PreparedStatementCreator {

		@Override
		public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

	}

	@Override
	public Product getProduct(int id) throws DaoException {
		try {
			return template.queryForObject("select * from products where id = ?", rowMapper, id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void updateProduct(Product product) throws DaoException {
		try {
			String sql = "update products set category_id=?, name=?, "
					+ "brand_id=?, description=?, quantity_per_unit=?, "
					+ "unit_price=?, picture=?, discount=? where id=?";

			template.update(sql, product.getCategoryId(), product.getName(), product.getBrandId(),
					product.getDescription(), product.getQuantityPerUnit(), product.getUnitPrice(),
					product.getPicture(), product.getDiscount(), product.getId());
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void deleteProduct(int id) throws DaoException {
		try {
			String sql = "delete from products where id = ?";
			// use update() for all DML (insert/update/delete)
			int rowCount = template.update(sql, id);
			if (rowCount == 0) {
				throw new DaoException("Invalid id for deletion: " + id);
			}
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public int getProductCount() throws DaoException {
		try {
			return template.queryForObject("select count(*) from products", Integer.class);
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<Product> getAllProducts() throws DaoException {
		try {
			return template.query("select * from products", rowMapper);
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<Product> getProductsByPrice(double min, double max) throws DaoException {
		try {
			String sql = "select * from products where unit_price between ? and ?";
			return template.query(sql, rowMapper, min, max);
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<Product> getProductsByBrand(int brandId) throws DaoException {
		try {
			String sql = "select * from products where brand_id = ?";
			return template.query(sql, rowMapper, brandId);
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<Product> getProductsByCategory(int categoryId) throws DaoException {
		try {
			String sql = "select * from products where category_id = ?";
			return template.query(sql, rowMapper, categoryId);
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public byte[] getPicture(int id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
