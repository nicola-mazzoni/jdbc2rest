package jdbc2rest.entities.configuration;

public class Server {

	private int port;

	private Ssl ssl;

	private int throttle;

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

	public int getThrottle() {
		return throttle;
	}

	public void setThrottle(int throttle) {
		this.throttle = throttle;
	}
}
