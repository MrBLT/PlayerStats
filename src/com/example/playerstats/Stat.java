package com.example.playerstats;

import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;

public class Stat {
	private JsonValue jsonValue;

	public Stat(JsonValue jsonValue) {
		this.jsonValue = jsonValue;
	}

	public boolean isLeaf() {
		return jsonValue.getValueType() == ValueType.STRING
		    || jsonValue.getValueType() == ValueType.NUMBER;
	}

	public Object get() {
		if (isLeaf()) {
			return jsonValue.toString();
		} else {
			return jsonValue;
		}
	}

	public Object get(String property) {
		return ((JsonObject)jsonValue).get(property);
	}

	public String toString() {
		return jsonValue.toString();
	}
}
