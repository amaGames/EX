package com.amagames.vampire;

public enum LogLevel {

	INFO(Color.PURPLE + "Vampire>> " + Color.GREEN),
	ERROR(Color.RED + "Error>> " + Color.GREEN),
	LOG(Color.CYAN + "Log>> " + Color.WHITE);

	private String prefix;

	LogLevel(String prefix) {
		this.prefix = prefix;
	}

	public String getPrefix() {
		return this.prefix;
	}

}
