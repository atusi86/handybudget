package com.handybudget.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.handybudget.database.PersistenceManager;

public class PersistenceAppListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		PersistenceManager.getInstance().closeEntityManagerFactory();
		
	}

}
