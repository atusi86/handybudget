package com.handybudget.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public interface IServletAction {

	public abstract String getAuthenticationClassName();
	
}
