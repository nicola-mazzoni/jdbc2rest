package jdbc2rest.services;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jetty.JettyHttpComponent;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.support.jsse.KeyManagersParameters;
import org.apache.camel.support.jsse.KeyStoreParameters;
import org.apache.camel.support.jsse.SSLContextParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jdbc2rest.MainProcessing;
import jdbc2rest.entities.Request;
import jdbc2rest.entities.Response;
import jdbc2rest.entities.configuration.Server;

public class Jdbc2RestRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		final Logger logger = LoggerFactory.getLogger(Jdbc2RestRouteBuilder.class);

		Server server = new Server();
		server = MainProcessing.getJdbc2RestConfiguration().getServer();

		if (server.getSsl() != null) {

			logger.info("SSL configuration...");

			try {
				KeyStoreParameters ksp = new KeyStoreParameters();
				ksp.setResource(server.getSsl().getKeystore().getJks());
				ksp.setPassword(
						server.getSsl().getKeystore().getPassword());

				KeyManagersParameters kmp = new KeyManagersParameters();
				kmp.setKeyStore(ksp);
				kmp.setKeyPassword(server.getSsl().getKeystore().getPassword());

				SSLContextParameters scp = new SSLContextParameters();
				scp.setKeyManagers(kmp);

				JettyHttpComponent j = getContext().getComponent("jetty", JettyHttpComponent.class);
				j.setSslContextParameters(scp);

				restConfiguration()
						.component("jetty").host("0.0.0.0")
						.port(server.getPort()).scheme("https")
						.bindingMode(RestBindingMode.off);

				logger.info("SSL ACTIVE");

			} catch (Exception e) {
				logger.error("Error on SSL configuration");
				e.printStackTrace();
			}

		} else {

			restConfiguration().component("jetty")
					.host("0.0.0.0").port(server.getPort()).scheme("http")
					.bindingMode(RestBindingMode.json);

			logger.warn("SSL NOT ACTIVE, DATA TRANSFERT NOT SECURE");
		}

		logger.info("port:" + server.getPort());
		logger.info("Server running...");

		/*
		 * rest("/jdbc2rest") .get("/healthcheck") .consumes("application/json")
		 * .to("file:/tmp/?fileName=HealthCheck_${date:now:yyyy_MM_dd_hhmmss}.json");
		 */

		rest("/jdbc2rest")
				.post("/v1")
				.type(Request.class).outType(Response.class)
				.to("direct:jdbc2rest");

		from("direct:jdbc2rest")
				.throttle(server.getThrottle())
				.to("bean:jdbc2rest.services.SqlExecutor");

	}
}
