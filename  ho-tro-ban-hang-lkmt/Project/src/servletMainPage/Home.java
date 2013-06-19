package servletMainPage;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.HomeMenuBean;
import bean.NsxLoaiLKBean;
import dao.DaoLoaiLK;
import dao.DaoTTLK;

/**
 * Servlet implementation class Home
 * Hiện trang chủ
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DaoTTLK daoMain = new DaoTTLK();
		DaoLoaiLK daoLK = new DaoLoaiLK();
		try {
			HomeMenuBean hotBean = daoMain.getItems(1);
			HomeMenuBean newBean = daoMain.getItems(2);
			NsxLoaiLKBean beanLK = daoLK.getManufactor();
			request.setAttribute("hotBean", hotBean);
			request.setAttribute("newBean", newBean);
			request.setAttribute("menuLK", beanLK);
			RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
			rd.forward(request, response);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubb
	}

}
