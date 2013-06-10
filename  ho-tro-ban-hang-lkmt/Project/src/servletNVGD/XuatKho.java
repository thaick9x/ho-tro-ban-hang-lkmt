package servletNVGD;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOCart;

/**
 * Servlet implementation class XuatKho
 */
@WebServlet("/XuatKho")
public class XuatKho extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XuatKho() {
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
		String quay = request.getParameter("quay");
		DAOCart cart = new DAOCart();
		try {
			cart.changeStatusNVGD(maGio, quay);
			response.getWriter().print("success");
		} catch (Exception ex) {
			response.getWriter().print(ex.getMessage());
		}
	}

}
