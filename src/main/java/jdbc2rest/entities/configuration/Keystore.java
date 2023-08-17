package jdbc2rest.entities.configuration;

public class Keystore {
	public String getJks() {
		return jks;
	}
	public void setJks(String jks) {
		this.jks = jks;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String jks;
	private String password;
}
