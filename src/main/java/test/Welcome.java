package test;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/welcome")
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Welcome() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("testPU");
		EntityManager em = emf.createEntityManager();
		TypedQuery<TestEntity> testQuery = em.createQuery("SELECT t FROM TestEntity t", TestEntity.class);
		List<TestEntity> testEntityList = testQuery.getResultList();
		for(TestEntity entity : testEntityList) {
			System.out.println(entity.toString());
		}
		
		
		request.setAttribute("name", name);
		request.getRequestDispatcher("welcome.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
