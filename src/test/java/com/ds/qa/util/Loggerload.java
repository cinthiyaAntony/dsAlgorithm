package com.ds.qa.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Loggerload {

	public static Logger logger = LogManager.getLogger(Loggerload.class);

	public static void info(final String message) {
		logger.info(message);
	}

	public static void warn(final String message) {
		logger.warn(message);
	}

	public static void error(final String message) {
		logger.error(message);
	}

	public static void fatal(final String message) {
		logger.fatal(message);
	}

	public static void debug(final String message) {
		logger.debug(message);
	}

}
