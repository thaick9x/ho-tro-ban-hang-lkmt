package servletDangNhap;

import dao.DaoDangNhap;
import dto.DangNhap;

public class BusLogin {
	public DangNhap checkLogin(String user, String pass) throws Exception {
		DaoDangNhap dao = new DaoDangNhap();
		DangNhap kq = dao.getThongTinDN(user);
		
		if (dao.checkUserExits(user)) {
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
	
	public String login(String maQuyen) throws Exception {
		if (maQuyen.equals("nvgd")) {
			return "http://localhost:8080/Project/ShowNVGH";
		} else {
			return "http://localhost:8080/Project/NVKho";
		}
	}
	
}
