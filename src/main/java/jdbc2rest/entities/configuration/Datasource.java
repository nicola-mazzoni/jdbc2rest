package jdbc2rest.entities.configuration;

public class Datasource {

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
	public int getQueryTimeout() {
		return queryTimeout;
	}
	public void setQueryTimeout(int queryTimeout) {
		this.queryTimeout = queryTimeout;
	}
	public int getMaxRecordReturned() {
		return maxRecordReturned;
	}
	public void setMaxRecordReturned(int maxRecordReturned) {
		this.maxRecordReturned = maxRecordReturned;
	}
	private String username;
	private String password;
	private String url;
	private int queryTimeout;
	private int maxRecordReturned;
	private String driver;

}
