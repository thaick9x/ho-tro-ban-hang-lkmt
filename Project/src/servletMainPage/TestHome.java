package servletMainPage;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.HomeMenuBean;
import bean.ManufactorBean;
import dao.DAOMainPage;
import dao.DAOMenuLK;

/**
 * Servlet implementation class TestHome
 */
@WebServlet("/TestHome")
public class TestHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOMainPage daoMain = new DAOMainPage();
		DAOMenuLK daoLK = new DAOMenuLK();
		try {
			HomeMenuBean hotBean = daoMain.getItems(1);
			HomeMenuBean newBean = daoMain.getItems(2);
			ManufactorBean beanLK = daoLK.getManufactor();
			request.setAttribute("hotBean", hotBean);
			request.setAttribute("newBean", newBean);
			request.setAttribute("menuLK", beanLK);
			RequestDispatcher rd = request.getRequestDispatcher("TestHome.jsp");
			rd.forward(request, response);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
