package training.cfg;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import training.dao.JdbcProductDao;
import training.dao.ProductDao;

@Configuration // <beans>...</beans>
public class AppConfig1 {
	
	// autowires this with "dao" -> non scalar
	@Bean(name="dbMap")
	public Map<String, String> map(){
		return new HashMap<>();
	}

	// does not autowire this with "dao" -> scalar
	@Bean(name="username")
	public String username(){
		return "vinod";
	}

	@Bean(name = { "dao" }, autowire=Autowire.BY_NAME) // <bean>...</bean>
	public ProductDao getProductDao() {
		return new JdbcProductDao();
	}

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2:tcp://localhost/~/philips");
		ds.setUsername("sa");
		ds.setPassword("");

		return ds;
	}

}
