package servletMainPage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import dao.DAOInfo;
import dto.ItemsInfo;
/**
 * Servlet implementation class GetInfoLK
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
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maTenLK = request.getParameter("serial");
		DAOInfo dao = new DAOInfo();
		ItemsInfo info = new ItemsInfo();
		Gson json = new Gson();
		response.setContentType("text/plain");
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
