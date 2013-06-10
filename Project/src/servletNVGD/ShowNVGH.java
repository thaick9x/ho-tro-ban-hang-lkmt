package servletNVGD;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.ListCartBean;
import bean.ReadyCartsBean;
import dao.DAOCart;

/**
 * Servlet implementation class ShowNVGH
 */
@WebServlet("/ShowNVGH")
public class ShowNVGH extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowNVGH() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOCart dao = new DAOCart();
		try {
			ListCartBean info = dao.getListCart();
			ReadyCartsBean ready = dao.getReadyCarts("1");
			request.setAttribute("list", info);
			request.setAttribute("ready", ready);
			RequestDispatcher rd = request.getRequestDispatcher("NVGD.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
