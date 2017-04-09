package com.github.senin24.selenidegridpoc.common;

import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;

/**
 * Class works with properties from "test.properties" file
 */
@Resource.Classpath("test.properties")
public class Config {

	private static Config config;

	private Config() {
		PropertyLoader.populate(this);
	}

	public static Config getInstance() {
		if (config == null) {
			config = new Config();
		}
		return config;
	}

	@Property("app.server")
	private String appServer;

	@Property("app.login")
	private String appLogin;

	@Property("app.logout")
	private String appLogout;

	@Property("browser")
	private String browser;

	@Property("test.timeout")
	private long timeout;

	@Property("test.longtimeout")
	private long longTimeout;

	@Property("test.smallTimeout")
	private long smallTimeout;

	@Property("grid.isUsed")
	private boolean isGridUsed;

	@Property("grid.hubHostName")
	private String gridHubHostName;

	@Property("threads.number")
	private static String threadsNumber;

	public String getLoginUrlServer() {
		return "http://" + appServer + appLogin;
	}

	public String getLogoutUrlServer() {
		return "http://" + appServer + appLogout;
	}

	public String getBrowser() {
		return browser;
	}

	public long getTimeout() {
		return timeout;
	}

	public long getLongTimeout() {
		return longTimeout;
	}

	public long getSmallTimeout() {
		return smallTimeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public void setLongTimeout(long longTimeout) {
		this.longTimeout = longTimeout;
	}

	public void setSmallTimeout(long smallTimeout) {
		this.smallTimeout = smallTimeout;
	}

	public boolean isGridUsed() {
		return isGridUsed;
	}

	public String getGridHubHostName() {
		return gridHubHostName;
	}

	public int getThreadsNumber() {
		return Integer.parseInt(threadsNumber);
	}

	public String getAppServer() {
		return appServer;
	}

}