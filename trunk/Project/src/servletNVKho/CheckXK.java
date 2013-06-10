package servletNVKho;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import dao.DAOXK;

/**
 * Servlet implementation class CheckXK
 */
@WebServlet("/CheckXK")
public class CheckXK extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckXK() {
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
		String idRow = request.getParameter("idRow");
		String maLK = request.getParameter("maLK");
		String maGio = request.getParameter("maGio");
		String maTenLK = request.getParameter("maTenLK");
		response.setContentType("text");
		DAOXK dao = new DAOXK();
		try {
			LinkedList<String> kq = dao.setInfoXK(maGio, idRow, maLK, maTenLK);
			if (kq.size() == 0) {
				response.getWriter().write("success");
			} else {
				response.getWriter().write(new Gson().toJson(kq));
			}		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
