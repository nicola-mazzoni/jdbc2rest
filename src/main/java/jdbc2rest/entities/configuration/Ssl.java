package jdbc2rest.entities.configuration;

public class Ssl {
	
	public Keystore getKeystore() {
		return keystore;
	}
	public void setKeystore(Keystore keystore) {
		this.keystore = keystore;
	}
	private Keystore keystore;

}
