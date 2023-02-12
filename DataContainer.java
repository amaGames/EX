package com.amagames.vampire;

import java.util.concurrent.ConcurrentHashMap;

public class DataContainer {

	private static final ConcurrentHashMap<String, String> RANK_TABLE = new ConcurrentHashMap<>();

	public ConcurrentHashMap<String, String> getRankTable() {
		return RANK_TABLE;
	}

}
