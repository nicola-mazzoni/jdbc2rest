package jdbc2rest.entities.configuration;

public class Datasource {

	private String username;
	private String password;
	private String url;
	private String driver;
	private int minIdle;
	private int maxIdle;
	private int maxActive;
	private int maxWait;
	private int minEvictableIdleTimeMillis;
	private boolean testOnBorrow;
	private boolean testWhileIdle;
	private String validationQuery;
	private int timeBetweenEvictionRunsMillis;
	private boolean removeAbandoned;
	private int removeAbandonedTimeout;
	private boolean logAbandoned;
	private int maxRecordReturned;
	private int validationQueryTimeout;

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getMinIdle() {
		return this.minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public int getMaxIdle() {
		return this.maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMaxActive() {
		return this.maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getMaxWait() {
		return this.maxWait;
	}

	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}

	public int getMinEvictableIdleTimeMillis() {
		return this.minEvictableIdleTimeMillis;
	}

	public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}

	public boolean isTestOnBorrow() {
		return this.testOnBorrow;
	}

	public boolean getTestOnBorrow() {
		return this.testOnBorrow;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public boolean isTestWhileIdle() {
		return this.testWhileIdle;
	}

	public boolean getTestWhileIdle() {
		return this.testWhileIdle;
	}

	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	public String getValidationQuery() {
		return this.validationQuery;
	}

	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}

	public int getTimeBetweenEvictionRunsMillis() {
		return this.timeBetweenEvictionRunsMillis;
	}

	public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	public boolean isRemoveAbandoned() {
		return this.removeAbandoned;
	}

	public boolean getRemoveAbandoned() {
		return this.removeAbandoned;
	}

	public void setRemoveAbandoned(boolean removeAbandoned) {
		this.removeAbandoned = removeAbandoned;
	}

	public int getRemoveAbandonedTimeout() {
		return this.removeAbandonedTimeout;
	}

	public void setRemoveAbandonedTimeout(int removeAbandonedTimeout) {
		this.removeAbandonedTimeout = removeAbandonedTimeout;
	}

	public boolean isLogAbandoned() {
		return this.logAbandoned;
	}

	public boolean getLogAbandoned() {
		return this.logAbandoned;
	}

	public void setLogAbandoned(boolean logAbandoned) {
		this.logAbandoned = logAbandoned;
	}

	public int getMaxRecordReturned() {
		return this.maxRecordReturned;
	}

	public void setMaxRecordReturned(int maxRecordReturned) {
		this.maxRecordReturned = maxRecordReturned;
	}

	public int getValidationQueryTimeout() {
		return this.validationQueryTimeout;
	}

	public void setValidationQueryTimeout(int validationQueryTimeout) {
		this.validationQueryTimeout = validationQueryTimeout;
	}

}
