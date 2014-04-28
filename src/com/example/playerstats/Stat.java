package com.example.playerstats;

import org.json.JSONException;
import org.json.JSONObject;


public class Stat {
	private JSONObject jsonObject;

	public Stat(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public Object get(String stat) throws JSONException {
		return jsonObject.get(stat);
	}
	
	public String toString() {
		return jsonObject.toString();
	}
}
