package jdbc2rest.entities;

import java.util.LinkedHashMap;
import java.util.List;

public class Response {
	
	
	public List<LinkedHashMap<String, Object>> getRecords() {
		return records;
	}

	public void setRecords(List<LinkedHashMap<String, Object>> records) {
		this.records = records;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String message;
	private List<LinkedHashMap<String, Object>> records;
}
