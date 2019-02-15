package com.handybudget.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class MainFilter implements javax.servlet.Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String uri = httpServletRequest.getRequestURI().replace(httpServletRequest.getContextPath(), "");
		JSONObject responseObject = new JSONObject();

		Map<String, String> mapping = new HashMap<String, String>();
		mapping.put("/api/login", "com.handybudget.api.Login");

		String className = mapping.get(uri);
		Class<?> cls = null;
		try {
			cls = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (cls != null) {
			IServletAction action = null;
			try {
				action = (IServletAction) cls.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JSONObject postData = new JSONObject();

			try (BufferedReader inputStream = new BufferedReader(
					new InputStreamReader(request.getInputStream(), "UTF-8"))) {

				StringBuilder sb = new StringBuilder();
				String line = inputStream.readLine();
				while (line != null) {
					sb.append(line);
					line = inputStream.readLine();
				}
				String rawdata = URLDecoder.decode(sb.toString(), "UTF-8");
				if (rawdata.startsWith("{")) {
					postData = new JSONObject(rawdata);
				}
			}

			responseObject = action.processRequest(httpServletRequest, httpServletResponse, postData);
		} else {
			responseObject.put("msg", "There is no available uri mapping for this request.");
		}

		httpServletResponse.getWriter().write(responseObject.toString());
		httpServletResponse.setContentType("application/json");

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
