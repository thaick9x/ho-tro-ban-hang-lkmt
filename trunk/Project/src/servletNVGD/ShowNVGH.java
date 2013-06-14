package servletNVGD;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.DsGioHangBean;
import bean.DsGioGD;
import bean.ThongTinNV;
import dao.DaoGioHang;

/**
 * Servlet implementation class ShowNVGH
 * Hiện trang cho nhân viên gioa dịch sử dụng
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
		ThongTinNV infoNV = (ThongTinNV)(request.getSession().getAttribute("infoNVGD"));
		if (infoNV == null) {
			response.sendRedirect("Login.html");
		}
		else {
			DaoGioHang dao = new DaoGioHang();
			try {
				DsGioHangBean list = dao.getListCart();
				DsGioGD ready = dao.getReadyCarts("1");
				request.setAttribute("infoNVGD", infoNV);
				request.setAttribute("list", list);
				request.setAttribute("ready", ready);
				RequestDispatcher rd = request.getRequestDispatcher("NVGD.jsp");
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
