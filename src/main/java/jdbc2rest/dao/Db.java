package jdbc2rest.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import jdbc2rest.MainProcessing;
import jdbc2rest.entities.configuration.Datasource;

public class Db {

	private static Db instance;
	private static DataSource dataSource;

	private Db() {
		try {
			// Get database configuration from your configuration system
			Datasource dataSourceConf = MainProcessing.getJdbc2RestConfiguration().getDatasource();

			PoolProperties p = new PoolProperties();
			p.setDriverClassName(dataSourceConf.getDriver());
			p.setUrl(dataSourceConf.getUrl());
			p.setUsername(dataSourceConf.getUsername());
			p.setPassword(dataSourceConf.getPassword());

			// Connection Pooling Configuration
			p.setMinIdle(dataSourceConf.getMinIdle());
			p.setMaxIdle(dataSourceConf.getMaxIdle());
			p.setMaxActive(dataSourceConf.getMaxActive());
			p.setMaxWait(dataSourceConf.getMaxWait());
			p.setMinEvictableIdleTimeMillis(dataSourceConf.getMinEvictableIdleTimeMillis());

			// Connection Validation
			p.setTestOnBorrow(dataSourceConf.getTestOnBorrow());
			p.setTestWhileIdle(dataSourceConf.getTestWhileIdle());
			p.setValidationQuery(dataSourceConf.getValidationQuery());
			p.setValidationQueryTimeout(dataSourceConf.getValidationQueryTimeout());
			p.setTimeBetweenEvictionRunsMillis(dataSourceConf.getTimeBetweenEvictionRunsMillis());

			// Abandoned Connection Removal
			p.setRemoveAbandoned(dataSourceConf.getRemoveAbandoned());
			p.setRemoveAbandonedTimeout(dataSourceConf.getRemoveAbandonedTimeout());
			p.setLogAbandoned(dataSourceConf.getLogAbandoned());

			p.setTestOnReturn(dataSourceConf.getTestOnReturn());
			

			// Initialize the DataSource with the configured properties
			dataSource = new DataSource();
			dataSource.setPoolProperties(p);

		} catch (Exception e) {
			e.printStackTrace(); // Log the full stack trace for debugging
			throw new RuntimeException("Errore nella configurazione del pool di connessioni: " + e.getMessage(), e);
		}
	}

	public static Db getInstance() {
		if (instance == null) {
			synchronized (Db.class) {
				if (instance == null) {
					instance = new Db();
				}
			}
		}
		return instance;
	}

	public Connection getConnection() throws SQLException {
		if (dataSource == null) {
			System.out.println("ERROR: DataSource is null");
			throw new SQLException("DataSource not initialized");
		}

		try {
			System.out.println("Attempting to get connection...");
			System.out.println("DataSource stats: Active=" + dataSource.getNumActive() +
					", Idle=" + dataSource.getNumIdle() +
					", Max=" + dataSource.getMaxActive());
			Connection conn = dataSource.getConnection();
			if (conn == null) {
				System.out.println("ERROR: Got null connection from pool");
				throw new SQLException("Unable to obtain connection from pool");
			}
			System.out.println("Successfully obtained connection");
			return conn;
		} catch (SQLException e) {
			System.out.println("ERROR getting connection: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
}
