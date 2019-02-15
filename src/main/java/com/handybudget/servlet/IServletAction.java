package com.handybudget.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public interface IServletAction {

	JSONObject processRequest(HttpServletRequest request, HttpServletResponse response, JSONObject postData);
	String getAuthenticationClassName();
}
