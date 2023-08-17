package jdbc2rest.entities.configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Server {
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public Ssl getSsl() {
		return ssl;
	}
	public void setSsl(Ssl ssl) {
		this.ssl = ssl;
	}
	private int port;
	
	private Ssl ssl;
}
