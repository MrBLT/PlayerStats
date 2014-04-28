package com.example.playerstats;


import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class StatSummary {
	private String               summaryType;
	private Map<String, Long> fields;
	private JSONObject           aggregatedStats;
	
	public StatSummary(String summaryType, JSONObject aggregatedStats) {
		this.summaryType     = summaryType;
		this.fields          = new HashMap<String, Long>();
		this.aggregatedStats = aggregatedStats;
	}
	
	public String getSummaryType() {
		return summaryType;
	}
	
	public String getField(String fieldName) {
		return fields.get(fieldName).toString();
	}
	
	public void addField(String name, long field) {
		fields.put(name, field);
	}
	
	public boolean hasField(String fieldName) {
		return fields.containsKey(fieldName);
	}
	
	public String getAggregatedStat(String statName) throws JSONException {
		int x = aggregatedStats.getInt(statName);
		String y = ""+x;
		return y;
	}
}