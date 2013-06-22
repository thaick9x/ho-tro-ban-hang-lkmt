package servletNVGD;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.DaoXK;

/**
 * Servlet implementation class ShowXK
 */
@WebServlet("/ShowXK")
public class ShowXK extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowXK() {
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
		DaoXK dao = new DaoXK();
		Gson json = new Gson();
		response.setContentType("text/html; charset=UTF-8");
		try {
			response.getWriter().print(json.toJson(dao.getInfoXK(maGio)));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
