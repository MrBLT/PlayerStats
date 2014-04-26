package com.example.playerstats;

import java.util.HashMap;
import java.util.Map;

public class StatSummary {
	private String            summaryType;
	private Map<String, Stat> stats;

	public StatSummary(String summaryType) {
		this.summaryType = summaryType;
		this.stats       = new HashMap<String, Stat>();
	}

	public String getSummaryType() {
		return summaryType;
	}

	public Map<String, Stat> getStats() {
		return stats;
	}

	public void addStat(String name, Stat stat) {
		stats.put(name, stat);
	}

	public String toString() {
		return stats.toString();
	}
}