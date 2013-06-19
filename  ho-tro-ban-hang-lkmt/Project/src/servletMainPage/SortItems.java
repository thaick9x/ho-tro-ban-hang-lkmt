package servletMainPage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.DaoTTLK;
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
		String items = request.getParameter("items");
		Gson json = new Gson();
		DaoTTLK dao = new DaoTTLK();
		HomeMenuBean bean = new HomeMenuBean();
		PrintWriter out = response.getWriter();
		
		if (items.contains("-")) {
			String[] dkSort = items.split("-");
			try {
				bean = dao.sortItems(dkSort[0], dkSort[1]);
			} catch (Exception e) {
				out.write(e.getMessage());
			}
		} else {
			try {
				bean = dao.sortItems(items);
			} catch (Exception ex) {
				out.write(ex.getMessage());
			}
		}
		String kq = json.toJson(bean);
	    response.setContentType("text/html");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	    request.setAttribute("bean", kq); 
	    response.sendRedirect("SortItems.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String items = request.getParameter("items");
		Gson json = new Gson();
		DaoTTLK dao = new DaoTTLK();
		HomeMenuBean bean = new HomeMenuBean();
		PrintWriter out = response.getWriter();
		
		if (items.contains("-")) {
			String[] dkSort = items.split("-");
			try {
				bean = dao.sortItems(dkSort[0], dkSort[1]);
			} catch (Exception e) {
				out.write(e.getMessage());
			}
		} else {
			try {
				bean = dao.sortItems(items);
			} catch (Exception ex) {
				out.write(ex.getMessage());
			}
		}
		String kq = json.toJson(bean);
	    response.setContentType("text/html");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	    response.getWriter().write(kq); 
	}

}
