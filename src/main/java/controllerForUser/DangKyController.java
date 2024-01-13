package controllerForUser;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.KhachHangBean;
import bo.KhachHangBO;
import common.MD5Encryptor;
import dao.KhachHangDAO;

/**
 * Servlet implementation class DangKyController
 */
@WebServlet("/DangKy")
public class DangKyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DangKyController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String email = request.getParameter("email");
		String hoTen = request.getParameter("hoTen");
		String soDienThoai = request.getParameter("soDienThoai");
		String password = request.getParameter("password");
		String xacNhanMatKhau = request.getParameter("xacNhanMatKhau");

		try {
			if (email != null && hoTen != null && soDienThoai != null && password != null && xacNhanMatKhau != null) {
				KhachHangBO khBO = new KhachHangBO();
				Boolean isErr = false;

				if (khBO.checkEmailExist(email)) {
					request.setAttribute("errEmail", "Email đã tồn tại trong hệ thống");
					isErr = true;
				}

				if (!password.equals(xacNhanMatKhau)) {
					request.setAttribute("errPass", "Mật khẩu không trùng khớp!");
					isErr = true;
				}
				
				if(!isErr) {
					String ps = MD5Encryptor.ecrypt(xacNhanMatKhau);
					KhachHangBean kh = new KhachHangBean(hoTen, email, ps, soDienThoai);
					session.setAttribute("KhachHang", kh);
					khBO.insert(kh);
					 response.sendRedirect("Home");
					 return;
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
								
		request.getRequestDispatcher("DangKy.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
