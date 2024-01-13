package controllerForUser;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.GioHangBean;
import bean.KhachHangBean;
import bo.DatHangBO;
import bo.GioHangBo;

/**
 * Servlet implementation class DatHangController
 */
@WebServlet("/DatHang")
public class DatHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatHangController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		KhachHangBean kh = (KhachHangBean)session.getAttribute("KhachHang");
		
		if(session.getAttribute("KhachHang") == null) {
			response.sendRedirect("DangNhap");
			return;
		}
		
		String diaChiNhanHang = request.getParameter("diaChiNhanHang");
		String ghiChu = request.getParameter("ghichu");
		
		try {
			if(diaChiNhanHang != null) {
				if (ghiChu == null) {
					ghiChu = "-";
				}
				GioHangBo ghBO = (GioHangBo) session.getAttribute("gioHang");
				DatHangBO dhBO = new DatHangBO();
				System.out.println(kh.getMa_Khach_hang());
				int madonhang = dhBO.TaoHoaDon(kh.getMa_Khach_hang(), diaChiNhanHang, ghiChu);
				
				System.out.println(madonhang);
				
				for(GioHangBean gh : ghBO.getAll()) {
					dhBO.TaoChiTietHoaDon(madonhang, gh.getMa_chi_tiet_San_pham(), gh.getSo_luong_mua());
				}
				
				
				String output = "<h1 style=\"font-size: 20px;\">THÔNG TIN ĐƠN HÀNG CỦA QUÝ KHÁCH</h1>\r\n"
						+ "    <table style=\"border-collapse: collapse;\">\r\n"
						+ "      <tr>\r\n"
						+ "        <td style=\" border: 1px solid #dddddd;\r\n"
						+ "            text-align: left;\r\n"
						+ "            padding: 8px;\">TÊN SẢN PHẨM</td>\r\n"
						+ "        <td style=\" border: 1px solid #dddddd;\r\n"
						+ "            text-align: left;\r\n"
						+ "            padding: 8px;\">DUNG LƯỢNG LƯU TRỮ</td>\r\n"
						+ "        <td style=\" border: 1px solid #dddddd;\r\n"
						+ "            text-align: left;\r\n"
						+ "            padding: 8px;\">BỘ NHỚ RAM</td>\r\n"
						+ "        <td style=\" border: 1px solid #dddddd;\r\n"
						+ "            text-align: left;\r\n"
						+ "            padding: 8px;\">MÀU SẮC</td>\r\n"
						+ "        <td style=\" border: 1px solid #dddddd;\r\n"
						+ "            text-align: left;\r\n"
						+ "            padding: 8px;\">SỐ LƯỢNG</td>\r\n"
						+ "        <td style=\" border: 1px solid #dddddd;\r\n"
						+ "            text-align: left;\r\n"
						+ "            padding: 8px;\">THÀNH TIỀN</td>\r\n"
						+ "      </tr>";
				// Tạo nội dung gởi thư email
				for(GioHangBean gh : ghBO.getAll()) {
					output += " <tr>\r\n"
							+ "        <td style=\" border: 1px solid #dddddd;\r\n"
							+ "            text-align: left;\r\n"
							+ "            padding: 8px;\">"+gh.getTen_san_pham()+"</td>\r\n"
							+ "        <td style=\" border: 1px solid #dddddd;\r\n"
							+ "            text-align: left;\r\n"
							+ "            padding: 8px;\">"+gh.getDung_luong_luu_tru()+"</td>\r\n"
							+ "        <td style=\" border: 1px solid #dddddd;\r\n"
							+ "            text-align: left;\r\n"
							+ "            padding: 8px;\">"+gh.getRam()+"</td>\r\n"
							+ "        <td style=\" border: 1px solid #dddddd;\r\n"
							+ "            text-align: left;\r\n"
							+ "            padding: 8px;\">"+gh.getMau_sac()+"</td>\r\n"
							+ "        <td style=\" border: 1px solid #dddddd;\r\n"
							+ "            text-align: left;\r\n"
							+ "            padding: 8px;\">"+gh.getSo_luong_mua()+"</td>\r\n"
							+ "        <td style=\" border: 1px solid #dddddd;\r\n"
							+ "            text-align: left;\r\n"
							+ "            padding: 8px;\">"+gh.getThanh_tien()+"</td>\r\n"
							+ "      </tr>";
				}
				output += " </table>\r\n"
						+ "  </body><div class=\"thank-you-message\">\r\n"
						+ "        <p>Cảm ơn bạn đã mua hàng!</p>\r\n"
						+ "    </div>\r\n"
						+ "    \r\n"
						+ "    <div class=\"contact-us\">\r\n"
						+ "        <p>Nếu bạn có bất kỳ câu hỏi hoặc cần hỗ trợ thêm, vui lòng liên hệ với chúng tôi qua email: <a href=\"mailto:nhatminhle1402@gmail.com\">nhatminhle1402@gmail.com</a>.</p>\r\n"
						+ "    </div>";

				common.Email.sendMail(kh.getEmail(), output);
				session.removeAttribute("gioHang");
				response.sendRedirect("DatHangThanhCong.jsp");
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("DatHang.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
