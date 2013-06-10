package servletNVGD;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LuuGiaoDich
 */
@WebServlet("/LuuGiaoDich")
public class LuuGiaoDich extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LuuGiaoDich() {
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
		GiaoDich gd = new GiaoDich();
		String maNV = request.getParameter("maNV");
		String maGio = request.getParameter("maGio");
		try {
			gd.finishGD(maNV, maGio);
			response.getWriter().print("success");
		} catch (Exception ex) {
			response.getWriter().print(ex.getMessage());
		}
	}

}
