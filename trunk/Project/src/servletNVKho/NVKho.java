package servletNVKho;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ThongTinNV;
import dao.DaoGioHang;

/**
 * Servlet implementation class NVKho
 * Hiện trang dành cho nhân viên kho sử dụng
 */
@WebServlet("/NVKho")
public class NVKho extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NVKho() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ThongTinNV infoNV = (ThongTinNV)(request.getSession().getAttribute("infoNVK"));
		if (infoNV == null) {
			response.sendRedirect("Login.html");
		}
		else {
			DaoGioHang dao = new DaoGioHang();	
			try {
				LinkedList<String> id = dao.getExportCart();
				request.setAttribute("infoNVK", infoNV);
				request.setAttribute("cart", dao.getCartInfoKho(id));
				RequestDispatcher rd = request.getRequestDispatcher("Kho.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
