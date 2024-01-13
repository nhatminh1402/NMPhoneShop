package common;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PageManager {

	/**
	 * @param table : Truy vấn dữ liệu từ bảng nào
	 * @param countColumn : Tên cột cần đếm số record
	 * @return Trả về tổng số Record hiện có
	 * @throws Exception
	 */
	public static int getTotalRecord(String table, String countColumn) throws Exception {
		String sqlQuery = "select count("+countColumn+") from " + table;
		DBConncet.GetConnect();
		int count = 0;
		
		PreparedStatement preP = DBConncet.cn.prepareStatement(sqlQuery);
		ResultSet rs = preP.executeQuery();
		
		while(rs.next()) {
			count = rs.getInt(1);
		}
		
		DBConncet.closeAllConnection(preP, rs);
		return count;
	}
	
	
	/**
	 * @param numberPerPage : Truyền vào tổng số Record / 1 trang
	 * @param totalRecord : Truyền vào tổng số Record hiện có
	 * @return Trả về tổng số Page hiện tại
	 */
	public static int getTotalPage(int numberPerPage, int totalRecord) {
		if(totalRecord % numberPerPage == 0) {
			return totalRecord / numberPerPage;
		} 
		return (totalRecord / numberPerPage) + 1;
	}
}
