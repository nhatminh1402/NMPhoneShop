package bo;

import java.sql.SQLException;

import bean.KhachHangBean;
import dao.KhachHangDAO;

public class KhachHangBO {
	KhachHangDAO khDAO = new KhachHangDAO();
	
	public boolean checkEmailExist(String email) throws Exception {
		return khDAO.checkEmailExist(email);
	}
	
	public void insert(KhachHangBean kh) throws SQLException, Exception {
		khDAO.insert(kh);
	}
	
	public KhachHangBean isAccountExist(String email, String matKhau) throws SQLException, Exception {
		return khDAO.isAccountExist(email, matKhau);
	}
}
