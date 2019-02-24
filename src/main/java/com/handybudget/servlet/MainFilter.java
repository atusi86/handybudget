package com.handybudget.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Properties;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.handybudget.util.GeneralHelper;
import com.handybudget.util.TokenAuthentication;

public class MainFilter implements javax.servlet.Filter {

	private static final String CONFIGURATION_URLMAPPING = "urlmapping.properties";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String uri = httpServletRequest.getRequestURI().replace(httpServletRequest.getContextPath(), "");
		JSONObject responseObject = new JSONObject();

		if (!uri.endsWith(".css") && !uri.endsWith(".js")) {

			Properties prop = new Properties();
			InputStream is = getClass().getClassLoader().getResourceAsStream(CONFIGURATION_URLMAPPING);
			prop.load(is);
			String className = prop.getProperty(uri);

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

				try (BufferedReader inputStream = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"))) {

					StringBuilder sb = new StringBuilder();
					String line = inputStream.readLine();
					while (line != null) {
						sb.append(line);
						line = inputStream.readLine();
					}
					String rawdata = URLDecoder.decode(sb.toString(), "UTF-8");
					if (rawdata.startsWith("{")) {
						postData = new JSONObject(rawdata);
					} else {
						String[] postRaw = rawdata.split("&");
						for (String postRecord : postRaw) {
							String[] postParts = postRecord.split("=");
							String key = null;
							if (postParts.length > 0) {
								key = postParts[0];
							}
							String value = null;
							if (postParts.length > 1) {
								value = postParts[1];
							}
							postData.put(key, value);
						}
					}
				}

				String authenticationClassName = action.getAuthenticationClassName();
				if (!GeneralHelper.isEmptyString(authenticationClassName)) {
					String authToken = httpServletRequest.getHeader("Authorization");
					try {
						Class<?> authClass = Class.forName(authenticationClassName);
						TokenAuthentication ta = (TokenAuthentication) authClass.newInstance();
						JSONObject authObject = ta.checkToken(authToken);
						boolean isAuthenticated = authObject.getBoolean("isAuthenticated");
						if (isAuthenticated) {

							int userId = authObject.getInt("userId");
							postData.put("userId", userId);
							filterRequest(httpServletRequest, httpServletResponse, action, postData);

						} else {
							httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
						}

					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {

					filterRequest(httpServletRequest, httpServletResponse, action, postData);
				}

			} else {
				responseObject.put("msg", "There is no available uri mapping for this request.");
			}
		} else {
			chain.doFilter(request, response);
			httpServletResponse.setHeader("Cache-Control", "private, no-cache, no-store, must-revalidate"); // HTTP 1.1
			httpServletResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0
			httpServletResponse.setDateHeader("Expires", 0); // Proxies.
			httpServletResponse.setCharacterEncoding("UTF-8");
		}

	}

	private void filterRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, IServletAction action, JSONObject postData) throws IOException, ServletException {

		String uri = httpServletRequest.getRequestURI().replace(httpServletRequest.getContextPath(), "");

		JSONObject responseObject;
		if (action instanceof JSONAction) {
			responseObject = ((JSONAction) action).processRequest(httpServletRequest, httpServletResponse, postData);
			httpServletResponse.getWriter().write(responseObject.toString());
			httpServletResponse.setContentType("application/json");

		} else if (action instanceof WebAction) {

			String type;
			if (!"/api/main".equals(uri)) {

				responseObject = ((WebAction) action).processRequest(httpServletRequest, httpServletResponse, postData);
				type = responseObject.getString("type");

				if ("forward".equals(type)) {
					String forwardUri = responseObject.optString("uri");

					if ("/api/main".equals(forwardUri)) {
						String authHeader = responseObject.optString("authHeader");
						try {
							String pageAfterLogin;
							if (!GeneralHelper.isEmptyString(authHeader)) {
								Class<?> main = Class.forName("com.handybudget.api.Main");
								IServletAction mainInstance = (IServletAction) main.newInstance();
								postData.put("Authorization", authHeader);
								responseObject = ((WebAction) mainInstance).processRequest(httpServletRequest, httpServletResponse, postData);
								boolean isAuthenticated = responseObject.optBoolean("isAuthenticated");

								if (isAuthenticated) {
									pageAfterLogin = "main";
								} else {
									pageAfterLogin = "unauthorized";
								}
							} else {
								pageAfterLogin = "unauthorized";
							}
							httpServletRequest.getServletContext().getRequestDispatcher("/" + pageAfterLogin + ".jsp").forward(httpServletRequest, httpServletResponse);
						} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
							e.printStackTrace();
						}

					} else {

						httpServletResponse.setStatus(HttpServletResponse.SC_SEE_OTHER);
						httpServletResponse.setHeader("Location", forwardUri);
					}

				} else if ("dispatch".equals(type)) {
					String jspName = responseObject.optString("jsp");
					httpServletRequest.getServletContext().getRequestDispatcher("/" + jspName + ".jsp").forward(httpServletRequest, httpServletResponse);
				}

			} else {

				httpServletRequest.getServletContext().getRequestDispatcher("/unauthorized.jsp").forward(httpServletRequest, httpServletResponse);

			}

		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
