package com.handybudget.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public abstract class WebAction implements IServletAction{

	public static final ActionType type = ActionType.FORWARD;
	
	public abstract JSONObject processRequest(HttpServletRequest request, HttpServletResponse response, JSONObject postData);
	

	public abstract String getAuthenticationClassName();


}
