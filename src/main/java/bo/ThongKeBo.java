package bo;

import com.google.gson.JsonArray;

import dao.ThongKeDAO;

public class ThongKeBo {
	ThongKeDAO tkDAO = new ThongKeDAO();
	
	public JsonArray ThongKeHangTonKho() throws Exception {
		return tkDAO.ThongKeHangTonKho();
	}
}
