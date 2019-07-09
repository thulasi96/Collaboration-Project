package com.coll.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.coll.dao.BlogDAO;
import com.coll.dao.BlogDAOimpl;
import com.coll.dao.UserDAOimpl;
import com.coll.dao.UserDetailDAO;
import com.coll.model.Blog;
import com.coll.model.BlogComment;
import com.coll.model.Friend;
import com.coll.model.Job;
import com.coll.model.UserDetail;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.coll")
public class DBconfig 
{
	@Bean(name="datasource")
	public DataSource getoracleDataSource()
	{
		DriverManagerDataSource datasource=new DriverManagerDataSource();
		
		datasource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		datasource.setUrl("jdbc:oracle:thin:@localhost:1521:thulasi");
		datasource.setUsername("thulsi");
		datasource.setPassword("12345");
		System.out.println("******Datasource object created******");
		return datasource;
	}

	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory()
	{
		Properties hibernateprop=new Properties();
		
		hibernateprop.put("hibernate.hbm2ddl.auto","update");
		hibernateprop.put("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
		LocalSessionFactoryBuilder factory=new LocalSessionFactoryBuilder(getoracleDataSource());
		factory.addProperties(hibernateprop);
		factory.addAnnotatedClass(Blog.class);
		factory.addAnnotatedClass(UserDetail.class);
		factory.addAnnotatedClass(BlogComment.class);
		factory.addAnnotatedClass(Job.class);
		factory.addAnnotatedClass(Friend.class);
		
		System.out.println("******SessionFactory Object created******");

		return factory.buildSessionFactory();
		
	}
@Bean(name="TransactionManager")
public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
{
	System.out.println("******TransactionManager Object created******");
	return new HibernateTransactionManager(sessionFactory);
}

@Bean(name="userdetailDAO") 
public UserDetailDAO getUserDetailDAO() {
	return new UserDAOimpl();
}
@Bean(name="blogDAO") 
public BlogDAO getBlogDAO() {
	return new BlogDAOimpl();
}
}