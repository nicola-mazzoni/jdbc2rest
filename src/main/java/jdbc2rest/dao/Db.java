package jdbc2rest.dao;

import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jdbc2rest.MainProcessing;
import jdbc2rest.services.SqlExecutor;

public class Db {

	private DataSource dataSource = null;

	private static final Logger log = LoggerFactory.getLogger(Db.class);

	public Db() throws Exception {

		PoolProperties p = new PoolProperties();
		p.setUrl(MainProcessing.getJdbc2RestConfiguration().getDatasource().getUrl());
		p.setDriverClassName(MainProcessing.getJdbc2RestConfiguration().getDatasource().getDriver());
		p.setUsername(MainProcessing.getJdbc2RestConfiguration().getDatasource().getUsername());
		p.setPassword(MainProcessing.getJdbc2RestConfiguration().getDatasource().getPassword());

		if (MainProcessing.getJdbc2RestConfiguration().getDatasource().getQueryTimeout() > 0) {
			p.setRemoveAbandonedTimeout(MainProcessing.getJdbc2RestConfiguration().getDatasource().getQueryTimeout());
			p.setLogAbandoned(true);
			p.setRemoveAbandoned(true);
		}

		// p.setJmxEnabled(true);
		// p.setTestWhileIdle(false);
		// p.setTestOnBorrow(true);
		// p.setValidationQuery("SELECT 1");
		// p.setTestOnReturn(false);
		// p.setValidationInterval(30000);
		// p.setTimeBetweenEvictionRunsMillis(30000);
		// p.setMaxActive(100);
		// p.setInitialSize(10);
		// p.setMaxWait(10000);
		// p.setMinEvictableIdleTimeMillis(30000);
		// p.setMinIdle(10);
		// p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");

		dataSource = new DataSource();
		dataSource.setPoolProperties(p);

	}

	public Connection getConnection() throws Exception {
		return this.dataSource.getConnection();
	}

}
