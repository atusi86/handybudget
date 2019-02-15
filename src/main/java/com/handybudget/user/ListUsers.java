package com.handybudget.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handybudget.domains.view.UserView;
import com.handybudget.facade.user.UserFacade;

@WebServlet("/listUsers")
public class ListUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    private UserFacade userFacade = new UserFacade();
    private int test = 10;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<UserView> userList = userFacade.getUsers();
		
		for(UserView user : userList) {
			System.out.println(user.getName());
			System.out.println(user.getEmail());
		}
		
		test++;
		System.out.println(test);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
