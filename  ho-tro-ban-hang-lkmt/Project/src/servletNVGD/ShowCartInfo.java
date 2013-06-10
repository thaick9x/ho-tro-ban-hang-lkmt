package servletNVGD;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import dao.DAOCart;

/**
 * Servlet implementation class ShowCartInfo
 */
@WebServlet("/ShowCartInfo")
public class ShowCartInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCartInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maGio = request.getParameter("maGio");
		DAOCart dao = new DAOCart();
		Gson json = new Gson();
		response.setContentType("text/plain");
		try {
			response.getWriter().print(json.toJson(dao.getCartInfoGD(maGio)));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
