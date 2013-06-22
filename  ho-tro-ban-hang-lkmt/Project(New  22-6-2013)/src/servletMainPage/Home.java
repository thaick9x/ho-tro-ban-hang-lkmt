package servletMainPage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

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
			HomeMenuBean hotBean = daoMain.getItems(1);	//Lấy bean gồm danh sách các linh kiện được truy cập nhiều nhất
			HomeMenuBean newBean = daoMain.getItems(2); //Lấy bean gồm danh sách các linh kiện mới được nhập
			NsxLoaiLKBean beanLK = daoLK.getManufactor(); //Bean danh sách loại linh kiện và hãng sản xuất
			
			//Truyền các bean vào trang jsp
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
		DaoTTLK daoMain = new DaoTTLK();
		Gson json = new Gson();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		
		try {
			HomeMenuBean hotBean = daoMain.getItems(1); //Lấy bean gồm danh sách các linh kiện được truy cập nhiều nhất
			HomeMenuBean newBean = daoMain.getItems(2); //Lấy bean gồm danh sách các linh kiện mới được nhập
			
			//Chia 2 bean bằng dấu ! để nhập vào 1 json
			String kq = json.toJson(hotBean) + "!" + json.toJson(newBean);
			out.print(kq);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
