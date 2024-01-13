package controllerForAdmin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.AdminBEAN;
import bo.AdminBO;
import common.MD5Encryptor;

/**
 * Servlet implementation class LoginController
 */
@WebServlet({ "/Admin/Dang-nhap" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
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
		
		if((String)session.getAttribute("AdminLogin") != null) {
			response.sendRedirect("http://localhost:8080/NMPhoneShop/Admin/Quan-ly-san-pham");
			return;
		}
		
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		boolean isError = false;
		
		try {
			if( email != null && pass != null ) {
				AdminBO adBO = new AdminBO();
				
				// Kiểm tra tài khoản có tồn tại
				if(!adBO.isAdminExist(email)) {
					request.setAttribute("emailErr", "Tài khoản không tồn tại!");
					request.setAttribute("email", email);
					isError = true;
				}
				
				// Kiểm tra tài khoản và mật khẩu có đúng hay không
				String passAfterEncrypt = MD5Encryptor.ecrypt(pass);
				
				AdminBEAN adminLogin = adBO.getAdmin(email, passAfterEncrypt);
				
				if(adminLogin == null) {
					request.setAttribute("passErr", "Mật khẩu không chính xác!");
					request.setAttribute("pass", pass);
					isError = true;
				}		
				
				if(!isError) {
					session.setAttribute("AdminLogin", email);
					response.sendRedirect("http://localhost:8080/NMPhoneShop/Admin/Quan-ly-san-pham");
					return;
				}
			}
			request.getRequestDispatcher("/Admin/Login.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
