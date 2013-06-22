package servletDangNhap;

import dao.DaoDangNhap;
import dto.DangNhap;

/*
 * Xử lý logic cho servlet Login.java
 */
public class BusLogin {
	
	/*
	 * Kiểm tra username và password được nhập vào có đúng hay không.
	 * Được gọi khi người dùng bấm login
	 */
	public DangNhap checkLogin(String user, String pass) throws Exception {
		DaoDangNhap dao = new DaoDangNhap();
		DangNhap kq = dao.getThongTinDN(user);
		
		//Kiểm tra username có tồn tại hay không
		if (dao.checkUserExits(user)) {
			//Kiểm tra password có đúng với username
			if (pass.equals(kq.getPass()) == false) {
				Exception ex = new Exception("Sai mật khẩu.");
				throw ex;
			}
		} else {
			Exception ex = new Exception("Sai tên đăng nhập");
			throw ex;
		}
		return kq;
	}
	
	/*
	 * Trả về giao diện đúng với vị trí của người dùng
	 * Được gọi sau khi đã kiểm tra đăng nhập là đúng
	 */
	public String login(String maQuyen) throws Exception {
		if (maQuyen.equals("nvgd")) {
			return "/Project/ShowNVGH"; //Trả về địa chỉ servlet của nhân viên giao dịch
		} else {
			return "/Project/NVKho"; //Trả về địa chỉ servlet của nhân viên kho
		}
	}
	
}
