package training.programs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import training.entity.Product;

public class P04_TestingJdbcTemplate {

	static JdbcTemplate template;

	public static void main(String[] args) {

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("context3.xml");

		template = ctx.getBean(JdbcTemplate.class);

		// printProductCount();
		// printProductDetails(22); // 22 -> products's id
		// printProductsByPriceRange(10.0, 20.0); // -> min_price = 10, max_price=20
		
		printProduct(22); 
		
		ctx.close();
	}

	static void printProduct(int id) {
		String sql = "select * from products where id = ?";
		RowMapper<Product> rowMapper = (rs, index) -> {
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
		Product p = template.queryForObject(sql, rowMapper, id);
		System.out.println(p);
	}
	
	

	static void printProductsByPriceRange(double min, double max) {
		String sql = "select * from products where unit_price between ? and ? order by unit_price desc";
		List<Map<String, Object>> list = template.queryForList(sql, min, max);

		for (Map<String, Object> rec : list) {
			System.out.printf("%s --> Rs.%.2f\n", rec.get("name"), rec.get("unit_price"));
		}
	}

	static void printProductDetails(int id) {
		// use queryForMap when sql returns 1 row, multiple columns
		Map<String, Object> rec = template.queryForMap("select * from products where id = ?", id);
		System.out.println("Name = " + rec.get("name"));
		System.out.println("Desc = " + rec.get("description"));
		System.out.println("Quantity Per unit = " + rec.get("QUANTITY_per_unit"));
		System.out.println("Price = Rs." + rec.get("unit_price"));
	}

	static void printProductCount() {
		// use queryForObject when sql returns 1 row 1 column
		int pc = template.queryForObject("select count(*) from products", Integer.class);
		System.out.printf("There are %d product\n", pc);
	}
}
