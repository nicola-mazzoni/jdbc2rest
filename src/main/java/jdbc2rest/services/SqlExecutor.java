package jdbc2rest.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jdbc2rest.MainProcessing;
import jdbc2rest.dao.Db;
import jdbc2rest.entities.Request;
import jdbc2rest.entities.Response;
import jdbc2rest.entities.configuration.User;

public class SqlExecutor {

	private static final Logger log = LoggerFactory.getLogger(SqlExecutor.class);

	public Response SqlExecutor(Request req) {
		Response res = new Response();

		try {
			log.info("new request...");
			log.info("Token....:" + req.getToken());
			log.info("Limit...:" + req.getLimit());
			log.info("Offset..:" + req.getOffset());
			log.info("SQL.....:" + req.getSql());

			if (req.getSql() == null) {
				log.warn("sql is null");
				res.setMessage("sql is null");
				return res;
			}

			if (req.getToken() == null) {
				log.warn("token is null");
				res.setMessage("token is null");
				return res;
			}

			String SqlClause = req.getSql().substring(0, req.getSql().indexOf(" "));

			boolean isAuthorized = false;
			List<User> users = MainProcessing.getJdbc2RestConfiguration().getUsers();
			for (User user : users) {
				if (user.getToken().compareTo(req.getToken()) == 0) {
					for (String auth : user.getAuth()) {
						if (auth.compareToIgnoreCase(SqlClause) == 0) {
							isAuthorized = true;
						}
					}
				}
			}

			if (!isAuthorized) {
				log.warn("request NOT authorized");
				res.setMessage("request not authorized");
				return res;
			}

			log.info("request authorized");

			Db db = new Db();
			Connection conn = db.getConnection();

			ResultSet rs = conn.createStatement().executeQuery(req.getSql());

			List<LinkedHashMap<String, Object>> recs = resultSetToList(rs, req.getOffset(), req.getLimit());

			res.setRecords(recs);

			conn.close();

			log.info("...request closed");

		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}

		return res;
	}

	private List<LinkedHashMap<String, Object>> resultSetToList(ResultSet rs, Integer offset, Integer limit)
			throws SQLException {

		int maxRecordReturned = MainProcessing.getJdbc2RestConfiguration().getDatasource().getMaxRecordReturned();

		if (offset == null) {
			offset = 0;
		}

		if (limit == null) {
			limit = maxRecordReturned;
		}

		if (limit > maxRecordReturned) {
			limit = maxRecordReturned;
		}

		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		List<LinkedHashMap<String, Object>> rows = new ArrayList<LinkedHashMap<String, Object>>();
		int rowNbr = 0;
		while (rs.next()) {
			rowNbr++;
			if (rowNbr <= offset) {
				continue;
			}
			if (rows.size() > limit) {
				break;
			}

			LinkedHashMap<String, Object> row = new LinkedHashMap<String, Object>(columns);
			for (int i = 1; i <= columns; ++i) {
				String ColumnName = md.getColumnName(i);
				String KeyName = "";
				KeyName = ColumnName;
				row.put(KeyName, rs.getObject(i));
			}
			rows.add(row);
		}

		rs.close();
		rs = null;

		return rows;
	}
}