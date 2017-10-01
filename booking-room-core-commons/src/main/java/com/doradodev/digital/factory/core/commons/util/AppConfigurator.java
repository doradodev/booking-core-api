package com.doradodev.digital.factory.core.commons.util;

import static com.google.common.base.Strings.isNullOrEmpty;

import com.doradodev.digital.factory.core.commons.profile.Profile;

/**
 * @author cristiandorado
 * 
 */
public final class AppConfigurator {

	public static final Profile PROFILE;

	private static final String COM_DORADODEV_ENV = "COM_DORADODEV_ENV";
	private static final String COM_DORADODEV_API_PORT = "COM_DORADODEV_API_PORT";

	private static final String COM_DORADODEV_ENV_PROPERTY = "com.doradodev.digital.factory.env";
	private static final String COM_DORADODEV_API_PORT_PROPERTY = "com.doradodev.digital.factory.api.port";

	private static final String SPRING_PROFILE = "spring.profiles.active";
	private static final String SPRING_PORT = "server.port";

	static {
		String profile = getEnvironmentProperty(COM_DORADODEV_ENV, COM_DORADODEV_ENV_PROPERTY);
		if (isNullOrEmpty(profile) || (!Profile.PROD.name().equals(profile) && !Profile.DEV.name().equals(profile))) {
			profile = Profile.DEV.name();
		}

		System.setProperty(SPRING_PROFILE, profile);

		String port = getEnvironmentProperty(COM_DORADODEV_API_PORT, COM_DORADODEV_API_PORT_PROPERTY);
		if (!isNullOrEmpty(port)) {
			System.setProperty(SPRING_PORT, port);
		}

		PROFILE = Profile.valueOf(profile);
	}

	private AppConfigurator() {
	}

	private static String getEnvironmentProperty(String envProperty, String sysProperty) {
		String value = System.getenv(envProperty);
		if (isNullOrEmpty(value)) {
			value = System.getProperty(sysProperty);
		}

		return value;
	}

	public static void configure(String... strings) {
		// TODO: Add base configuration!
	}

}
