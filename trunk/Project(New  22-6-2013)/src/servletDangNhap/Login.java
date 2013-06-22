package servletDangNhap;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.DaoNhanVien;
import dto.DangNhap;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		BusLogin bul = new BusLogin();
		DaoNhanVien nhanVien = new DaoNhanVien();
		
		try {
			DangNhap info = bul.checkLogin(user, pass);
			
			//Truyền thông tin nhân viên sau khi đã kiểm tra đăng nhập
			if (info.getQuyen().equals("nvgd")) {
				request.getSession().setAttribute("infoNVGD", nhanVien.getThongTinNV(info.getUserID())); //Nhân viên giao dịch
			} else {
				request.getSession().setAttribute("infoNVK", nhanVien.getThongTinNV(info.getUserID())); //Nhân viên kho
			}
			response.sendRedirect(bul.login(info.getQuyen()));
			
		} catch (Exception ex) {
			response.getWriter().print(ex.getMessage());
		}
	}

}
