package jdbc2rest.entities.configuration;

import java.util.List;

public class Jdbc2RestConfiguration {
	public Datasource getDatasource() {
		return datasource;
	}
	public void setDatasource(Datasource datasource) {
		this.datasource = datasource;
	}
	public Server getServer() {
		return server;
	}
	public void setServer(Server server) {
		this.server = server;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	private Datasource datasource;
	private Server server;
	private List<User> users;
}
