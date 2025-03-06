package jdbc2rest;

import java.io.File;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import jdbc2rest.dao.Db;
import jdbc2rest.entities.configuration.Jdbc2RestConfiguration;
import jdbc2rest.services.Jdbc2RestRouteBuilder;

public class MainProcessing {

	private static final Logger log = LoggerFactory.getLogger(MainProcessing.class);
	private static Jdbc2RestConfiguration jdbc2RestConfiguration = null;

	public static void main(String... args) throws Exception {
		log.info("Main run...");

		CamelContext camelContext = new DefaultCamelContext();

        try {

            camelContext.addRoutes(new Jdbc2RestRouteBuilder());
            camelContext.start();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    camelContext.stop();
                    camelContext.close();
                    log.info("Main stop...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }));

            synchronized (MainProcessing.class) {
                MainProcessing.class.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public static Jdbc2RestConfiguration getJdbc2RestConfiguration() {
		if (jdbc2RestConfiguration == null) {
			try {
				File propFile = new File(MainProcessing.class.getClassLoader().getResource("Jdbc2RestConfiguration.json").getFile());
			    ObjectMapper mapper = new ObjectMapper();
				jdbc2RestConfiguration = mapper.readValue(propFile, Jdbc2RestConfiguration.class);
			} catch (Exception e) {
				log.error("loading Jdbc2RestConfiguration.json FAILED");				
				log.error(e.getMessage());
				e.printStackTrace();
			}     						
		}
		return jdbc2RestConfiguration;
	}

}
