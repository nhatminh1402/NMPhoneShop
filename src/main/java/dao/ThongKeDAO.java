package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import common.DBConncet;

public class ThongKeDAO {

	public JsonArray ThongKeHangTonKho() throws Exception {
		JsonArray jsonArray = new JsonArray();
		String sql = "select HSX.hang_san_xuat, COUNT(ttct.so_luong) as sl\r\n"
				+ "from (san_pham as sp join thong_tin_Chi_tiet ttct\r\n"
				+ "on sp.id_san_pham = ttct.id_san_pham) join Hang_San_Xuat HSX\r\n"
				+ "on HSX.id_hang = sp.id_hang\r\n" + "group by HSX.hang_san_xuat, HSX.id_hang\r\n" + "";

		DBConncet.GetConnect();
		PreparedStatement preP = DBConncet.cn.prepareStatement(sql);
		ResultSet rs = preP.executeQuery();
		while (rs.next()) {
			JsonObject data = new JsonObject();
			data.addProperty(rs.getString(1), rs.getInt(2));
			jsonArray.add(data);
		}

		DBConncet.closeAllConnection(preP, rs);

		return jsonArray;
	}
}
