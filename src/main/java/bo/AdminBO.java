package bo;

import bean.AdminBEAN;
import dao.AdminDAO;

public class AdminBO {
	AdminDAO aDAO = new AdminDAO();
	
	public boolean isAdminExist(String email) throws Exception {
		return aDAO.isAdminExist(email);
	}
	
	public AdminBEAN getAdmin(String email, String password) throws Exception {
		return aDAO.getAdmin(email, password);
	}
}
