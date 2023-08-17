package jdbc2rest;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.camel.main.Main;

import jdbc2rest.entities.configuration.Jdbc2RestConfiguration;
import jdbc2rest.services.Jdbc2RestRouteBuilder;
import jdbc2rest.services.SqlExecutor;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MainProcessing {

	private static final Logger log = LoggerFactory.getLogger(MainProcessing.class);
	private static Jdbc2RestConfiguration jdbc2RestConfiguration = null;

	public static void main(String... args) throws Exception {
		log.info("Main run...");
		
		jdbc2RestConfiguration = getJdbc2RestConfiguration();
		Main main = new Main();
		main.configure().addRoutesBuilder(new Jdbc2RestRouteBuilder());
		main.run();
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
