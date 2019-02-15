package test;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handybudget.domains.view.UserView;
import com.handybudget.facade.user.UserFacade;

@WebServlet("/welcome")
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Welcome() {
        super();
        // TODO Auto-generated constructor stub
    }

    private UserFacade userFacade = new UserFacade();
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		
		List<UserView> userList = userFacade.getUsers();
		
		for(UserView user : userList) {
			System.out.println(user.getName());
			System.out.println(user.getEmail());
		}
		
		
		request.setAttribute("name", name);
		request.getRequestDispatcher("welcome.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
