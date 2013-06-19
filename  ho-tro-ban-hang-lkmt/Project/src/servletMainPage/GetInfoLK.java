package servletMainPage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import dao.DaoTTLK;
import dto.ItemsInfo;
/**
 * Servlet implementation class GetInfoLK
 * Xuất thông tin kỹ thuật của từng linh kiện
 */
@WebServlet("/GetInfoLK")
public class GetInfoLK extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetInfoLK() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maTenLK = request.getParameter("serial");
		System.out.println(maTenLK);
		DaoTTLK dao = new DaoTTLK();
		ItemsInfo info = new ItemsInfo();
		Gson json = new Gson();
		response.setContentType("text");
		response.setCharacterEncoding("UTF-8");
		try {
			info = dao.getInfo(maTenLK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().write(json.toJson(info));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maTenLK = request.getParameter("serial");
		DaoTTLK dao = new DaoTTLK();
		ItemsInfo info = new ItemsInfo();
		Gson json = new Gson();
		response.setContentType("text");
		response.setCharacterEncoding("UTF-8");
		try {
			info = dao.getInfo(maTenLK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().write(json.toJson(info));
	}
}
