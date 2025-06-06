package com.aepl.sam.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {

	private static Properties properties;
	private static String environment;
	private static final String CONFIG_FILE_FORMAT = "src/main/resources/%s.config.properties";

	private ConfigProperties() {
	}

	public static synchronized void initialize(String env) {
		if (env == null || env.isEmpty()) {
			throw new IllegalArgumentException("Environment must not be null or empty.");
		}
		environment = env.toLowerCase();
		String propertiesFile = String.format(CONFIG_FILE_FORMAT, environment);
		properties = new Properties();

		try (FileInputStream fis = new FileInputStream(propertiesFile)) {
			properties.load(fis);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load properties file: " + propertiesFile, e);
		}
	}

	public static String getProperty(String key) {
		if (properties == null) {
			throw new IllegalStateException("ConfigProperties is not initialized. Call initialize(env) first.");
		}
		return properties.getProperty(key);
	}

	public static synchronized void reloadProperties() {
		if (environment == null) {
			throw new IllegalStateException("ConfigProperties is not initialized. Call initialize(env) first.");
		}

		initialize(environment);
	}

	public static String getEnvironment() {
		return environment;
	}

	public static void setProperty(String string, String passwordFromOutlook) {
		if (properties == null) {
			throw new IllegalStateException("ConfigProperties is not initialized. Call initialize(env) first.");
		}
		properties.setProperty(string, passwordFromOutlook);
		try {
			properties.store(new FileOutputStream(String.format(CONFIG_FILE_FORMAT, environment)), null);
		} catch (IOException e) {
			throw new RuntimeException("Failed to save properties file", e);
		}
	}
}
