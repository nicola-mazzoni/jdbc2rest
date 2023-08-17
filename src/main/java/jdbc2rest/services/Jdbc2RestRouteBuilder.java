package jdbc2rest.services;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jetty.JettyHttpComponent;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.support.jsse.KeyManagersParameters;
import org.apache.camel.support.jsse.KeyStoreParameters;
import org.apache.camel.support.jsse.SSLContextParameters;

import jdbc2rest.MainProcessing;
import jdbc2rest.entities.Request;
import jdbc2rest.entities.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Jdbc2RestRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		final Logger logger = LoggerFactory.getLogger(Jdbc2RestRouteBuilder.class);

		String port = new Integer(MainProcessing.getJdbc2RestConfiguration().getServer().getPort()).toString();

		if (MainProcessing.getJdbc2RestConfiguration().getServer().getSsl() != null) {

			logger.info("SSL configuration...");

			try {
				KeyStoreParameters ksp = new KeyStoreParameters();
				ksp.setResource(MainProcessing.getJdbc2RestConfiguration().getServer().getSsl().getKeystore().getJks());
				ksp.setPassword(
						MainProcessing.getJdbc2RestConfiguration().getServer().getSsl().getKeystore().getPassword());

				KeyManagersParameters kmp = new KeyManagersParameters();
				kmp.setKeyStore(ksp);
				kmp.setKeyPassword(
						MainProcessing.getJdbc2RestConfiguration().getServer().getSsl().getKeystore().getPassword());

				SSLContextParameters scp = new SSLContextParameters();
				scp.setKeyManagers(kmp);

				JettyHttpComponent j = getContext().getComponent("jetty", JettyHttpComponent.class);
				j.setSslContextParameters(scp);

				restConfiguration().component("jetty").host("0.0.0.0").port(port).scheme("https")
						.bindingMode(RestBindingMode.off);

				logger.info("SSL ACTIVE");

			} catch (Exception e) {
				logger.error("Error on SSL configuration");
				e.printStackTrace();
				// TODO: handle exception
			}

		} else {
			
			restConfiguration().component("jetty").host("0.0.0.0").port(port).scheme("http")
					.bindingMode(RestBindingMode.json);
			logger.warn("SSL NOT ACTIVE, DATA TRANSFERT NOT SECURE");


		}

		logger.info("port:" + MainProcessing.getJdbc2RestConfiguration().getServer().getPort());
		logger.info("Server running...");

		/*
		 * rest("/jdbc2rest") .get("/healthcheck") .consumes("application/json")
		 * .to("file:/tmp/?fileName=HealthCheck_${date:now:yyyy_MM_dd_hhmmss}.json");
		 */

		rest("/jdbc2rest").get("/v1").type(Request.class).outType(Response.class)
				.to("bean:jdbc2rest.services.SqlExecutor");

	}

}
