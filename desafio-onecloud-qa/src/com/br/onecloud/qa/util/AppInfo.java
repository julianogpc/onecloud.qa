package com.br.onecloud.qa.util;

public class AppInfo {
	private String host, port, user, pass, path;

	public AppInfo(String host, String port, String user, String pass, String path) {
		this.host = host;
		this.port = port;
		this.user = user;
		this.pass = pass;
		this.path = path;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getUrl() {	
		return host + ":" + port + "/" + path + "/";
	}
	
	public String toString() {	
		return "url: " + host + ":" + port + "/" + path + ", user: " + user + ", pass: " + pass;
	}
}
