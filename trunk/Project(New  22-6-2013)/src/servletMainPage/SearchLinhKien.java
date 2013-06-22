package servletMainPage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.NsxLoaiLKBean;

import com.google.gson.Gson;

import dao.DaoLoaiLK;

/**
 * Servlet implementation class SearchLinhKien
 */
@WebServlet("/SearchLinhKien")
public class SearchLinhKien extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchLinhKien() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		BusSearch bus = new BusSearch();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		
		DaoLoaiLK daoLK = new DaoLoaiLK();
		
		try {
			request.setAttribute("bean", bus.searchLinhKien(search)); //Truyền bean các linh kiện
			NsxLoaiLKBean beanLK = daoLK.getManufactor();
			request.setAttribute("menuLK", beanLK); //Truyền bean loại linh kiện
			RequestDispatcher rd = request.getRequestDispatcher("SearchLinhKien.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			out.write(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		BusSearch bus = new BusSearch();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		
		try {
			out.print(new Gson().toJson(bus.searchLinhKien(search)));
		} catch (Exception e) {
			out.write(e.getMessage());
		}
	}

}
