package jdbc2rest.dao;

import java.sql.Connection;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import jdbc2rest.MainProcessing;
import jdbc2rest.entities.configuration.Datasource;

public class Db {

	private static Db instance;
	private static DataSource dataSource;

	public Db() {
		try {
			Datasource dataSourceConf = new Datasource();
			dataSourceConf = MainProcessing.getJdbc2RestConfiguration().getDatasource();

			PoolProperties p = new PoolProperties();
			p.setDriverClassName(dataSourceConf.getDriver());
			p.setUrl(dataSourceConf.getUrl());
			p.setUsername(dataSourceConf.getUsername());
			p.setPassword(dataSourceConf.getPassword());

			// Configurazione Pooling
			p.setMinIdle(dataSourceConf.getMinIdle());
			p.setMaxIdle(dataSourceConf.getMaxIdle());
			p.setMaxActive(dataSourceConf.getMaxActive());
			p.setMaxWait(dataSourceConf.getMaxWait());
			p.setMinEvictableIdleTimeMillis(dataSourceConf.getMinEvictableIdleTimeMillis());

			// Validazione Connessione
			p.setTestOnBorrow(dataSourceConf.getTestOnBorrow());
			p.setTestWhileIdle(dataSourceConf.getTestWhileIdle());
			p.setValidationQuery(dataSourceConf.getValidationQuery());
			p.setTimeBetweenEvictionRunsMillis(dataSourceConf.getTimeBetweenEvictionRunsMillis());

			// Rimozione Connessioni Abbandonate
			p.setRemoveAbandoned(dataSourceConf.getRemoveAbandoned());
			p.setRemoveAbandonedTimeout(dataSourceConf.getRemoveAbandonedTimeout());
			p.setLogAbandoned(dataSourceConf.getLogAbandoned());

			dataSource = new DataSource();
			dataSource.setPoolProperties(p);

		} catch (Exception e) {
			throw new RuntimeException("Errore nella configurazione del pool di connessioni", e);
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

	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (Exception e) {
			throw new RuntimeException("Errore nel recupero della connessione", e);
		}
	}
}
