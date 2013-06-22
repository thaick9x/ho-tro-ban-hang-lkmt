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

import com.google.gson.*;

import dao.DaoLoaiLK;
import dto.ItemsInCart;



/**
 * Servlet implementation class ShowCartItems
 * Hiện thông tin chi tiết của giỏ hàng
 */
@WebServlet("/ShowCartItems")
public class ShowCartItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCartItems() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cart = request.getParameter("cart");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		DaoLoaiLK daoLK = new DaoLoaiLK();
		
		try {
			ItemsInCart items = BusGioHang.getItemsInCart(cart);
			NsxLoaiLKBean beanLK = daoLK.getManufactor();
			request.setAttribute("items", items); //Truyền bean thông ting giỏ hàng
			request.setAttribute("menuLK", beanLK); //Truyền bean loại linh kiện
			RequestDispatcher rd = request.getRequestDispatcher("Cart.jsp");
			rd.forward(request, response);
		} catch (Exception ex) {
			out.println(ex.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		String cart = request.getParameter("cart");
		ItemsInCart items = new ItemsInCart();
		
		try {
			items = BusGioHang.getItemsInCart(cart);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().write(new Gson().toJson(items));
	}
}
