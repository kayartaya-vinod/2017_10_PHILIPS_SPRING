package training.cfg;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableAspectJAutoProxy
@Configuration
@ComponentScan(basePackages = { "training.dao", "training.aspects", "training.service" })
public class AppConfig2 {


	@Bean(name = "txMgr", autowire = Autowire.BY_NAME)
	public PlatformTransactionManager htm() {
		// need to wire the sessionFactory
		return new HibernateTransactionManager();
	}

	@Bean(autowire = Autowire.BY_NAME) // inject "sessionFactory"
	public HibernateTemplate template() {
		return new HibernateTemplate();
	}

	// bean representing the Hibernate's SessionFactory
	@Bean(name = "sessionFactory", autowire = Autowire.BY_NAME) // inject
																// "dataSource"
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));
		return lsfb;
	}

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2:tcp://localhost/~/philips");
		ds.setUsername("sa");
		ds.setPassword("");
		ds.setInitialSize(10);
		ds.setMaxActive(100);
		ds.setMaxIdle(10);
		ds.setMinIdle(5);

		return ds;
	}

}
