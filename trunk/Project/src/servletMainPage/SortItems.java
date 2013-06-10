package servletMainPage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.DAOSortItems;
import bean.HomeMenuBean;

import com.google.gson.*;

/**
 * Servlet implementation class SortItems
 */
@WebServlet("/SortItems")
public class SortItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SortItems() {
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
		String items = request.getParameter("items");
		Gson json = new Gson();
		String[] dkSort = items.split("-");
		DAOSortItems dao = new DAOSortItems();
		HomeMenuBean bean = new HomeMenuBean();
		try {
			bean = dao.getBean(dkSort[0], dkSort[1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String kq = json.toJson(bean);
	    response.setContentType("text/html");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	    response.getWriter().write(kq); 
	}

}
