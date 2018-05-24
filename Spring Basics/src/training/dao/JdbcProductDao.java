package training.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import training.entity.Product;

@Repository
public class JdbcProductDao implements ProductDao {

	// dependencies:
	// fields
	private String driver;
	private String url;
	private String username;
	private String password;

	// dependency on a non-scalar / non-collection
	// a connection pool
	// @Qualifier("ds2")
	@Autowired
	private DataSource dataSource;

	// default no-arg constructor; best practice
	// spring uses this by default
	public JdbcProductDao() {
		// System.out.println("In the JdbcProductDao(), dataSource is " +
		// dataSource);
	}

	@PostConstruct
	public void init() {
		// System.out.println("In the init(), dataSource is " + dataSource);
	}

	// parameterized constructor
	// spring can use this constructor for dependency injection
	public JdbcProductDao(String driver, String url, String username, String password) {
		System.out.println("JdbcProductDao(...) called");
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public JdbcProductDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// a setter is a.k.a a mutator or a writable property called "driver"
	public void setDriver(String driver) {
		System.out.println("property 'driver' is assigned with '" + driver + "'");
		this.driver = driver;
	}

	// property "url"
	public void setUrl(String url) {
		System.out.println("property 'url' is assigned with '" + url + "'");
		this.url = url;
	}

	// property "username"
	public void setUsername(String username) {
		System.out.println("property 'username' is assigned with '" + username + "'");
		this.username = username;
	}

	public void setPassword(String password) {
		System.out.println("property 'password' is assigned with '" + password + "'");
		this.password = password;
	}

	// for spring this is a writable property called "dbInfo" of type String[]
	public void setDbInfo(String[] info) {
		this.driver = info[0];
		this.url = info[1];
		this.username = info[2];
		this.password = info[3];
	}

	// used by spring for DI; writable property called "dbData" or "DbData"
	public void setDbData(List<String> list) {
		driver = list.get(0);
		url = list.get(1);
		username = list.get(2);
		password = list.get(3);
	}

	// property called "dbMap"
	public void setDbMap(Map<String, String> map) {
		System.out.println("map is an instanceof " + map.getClass());

		this.driver = map.get("driver-name");
		this.url = map.get("db-url");
		this.username = map.get("user");
		this.password = map.get("passcode");
	}

	// property namme = "dbProperties"
	public void setDbProperties(Properties props) {

		// props.list(System.out);

		// key can be any string
		this.driver = props.getProperty("driver");
		this.url = props.getProperty("url");
		this.username = props.getProperty("username");
		this.password = props.getProperty("password");
	}

	// writable property called "dataSource" of type javax.sql.DataSource
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private Connection getConnection() throws DaoException {
		try {

			// if pool is available
			if (dataSource != null) {
				// return a connection from the pool
				return dataSource.getConnection();
			}

			Class.forName(driver);
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	@Override
	public int getProductCount() throws DaoException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "select count(*) from products";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
	}

	@Override
	public void addProduct(Product product) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	@Override
	public Product getProduct(int id) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	@Override
	public void updateProduct(Product products) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	@Override
	public void deleteProduct(int id) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	@Override
	public List<Product> getAllProducts() throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	@Override
	public List<Product> getProductsByPrice(double min, double max) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	@Override
	public List<Product> getProductsByBrand(int brandId) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	@Override
	public List<Product> getProductsByCategory(int categoryId) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

	@Override
	public byte[] getPicture(int id) throws DaoException {
		throw new DaoException("Method not implemented!");
	}

}
