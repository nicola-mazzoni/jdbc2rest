package jdbc2rest.entities.configuration;

import java.util.List;

public class User {
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public List<String> getAuth() {
		return auth;
	}
	public void setAuth(List<String> auth) {
		this.auth = auth;
	}
	private String token;
	private List<String> auth;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String name;
}
