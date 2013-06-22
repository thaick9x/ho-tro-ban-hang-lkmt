package servletMainPage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoLoaiLK;
import dao.DaoTTLK;
import bean.HomeMenuBean;
import bean.NsxLoaiLKBean;

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
		DaoTTLK dao = new DaoTTLK();
		HomeMenuBean bean = new HomeMenuBean();
		NsxLoaiLKBean beanLK = new NsxLoaiLKBean();
		DaoLoaiLK daoLK = new DaoLoaiLK();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text");
		try {
			if (items.contains("-")) {
				String[] dkSort = items.split("-");
				bean = dao.sortItems(dkSort[0], dkSort[1]);
			} else {
				bean = dao.sortItems(items);
			}
			beanLK = daoLK.getManufactor();
			request.setAttribute("bean", bean);
			request.setAttribute("menuLK", beanLK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("SortItems.jsp");
		rd.forward(request, response);
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
		response.setContentType("text/html; charset=UTF-8");
		
		try {
			if (items.contains("-")) {
				String[] dkSort = items.split("-");
				bean = dao.sortItems(dkSort[0], dkSort[1]);
			} else {
				bean = dao.sortItems(items);
			}
		} catch (Exception e) {
			out.write(e.getMessage());
		}
		String kq = json.toJson(bean);
		
	    out.write(kq); 
	}

}
