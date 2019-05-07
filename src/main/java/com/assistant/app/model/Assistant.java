package com.assistant.app.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("assistant")
public class Assistant {
private String appname;
public String getAppname() {
	return appname;
}
public void setAppname(String appname) {
	this.appname = appname;
}
public String getAppage() {
	return appage;
}
public void setAppage(String appage) {
	this.appage = appage;
}
public String getAppscope() {
	return appscope;
}
public void setAppscope(String appscope) {
	this.appscope = appscope;
}
private String appage;
private String appscope;
}
