package controllerForUser;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Locale;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import bean.ChiTietSanPhamBEAN;
import bo.ChiTietSanPhamBO;
import bo.GioHangBo;

/**
 * Servlet implementation class DatHangController
 */
@WebServlet("/GioHang")
public class GioHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GioHangController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String id_chi_tiet = request.getParameter("id-chi-tiet");
		String id_xoa = request.getParameter("del-id");
		GioHangBo ghBO = new GioHangBo();

		String action = request.getParameter("action");

		if (action != null) {
			if (action.equals("updateQuantity")) {
				update(request, response, session);
				// Gọi hàm xử lý liên quan đến việc update
				return;
			}
			if(action.equals("reduce")) {
				reduce(request, response, session);
				return;
			}
		}

		try {
			if (id_chi_tiet != null) {
				if (session.getAttribute("gioHang") == null) {
					session.setAttribute("gioHang", ghBO);
				}

				ghBO = (GioHangBo) session.getAttribute("gioHang");
				ghBO.add(Integer.parseInt(id_chi_tiet), 1);
				session.setAttribute("gioHang", ghBO);
				response.sendRedirect("GioHang");
				return;

			}

			// Thực hiện xóa
			if (id_xoa != null) {
				ghBO = (GioHangBo) session.getAttribute("gioHang");
				ghBO.xoa(Integer.parseInt(id_xoa));
				session.setAttribute("gioHang", ghBO);
			}

			request.getRequestDispatcher("GioHang.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	protected void update(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws ServletException, IOException {
		String soLuong = request.getParameter("soLuong");
		String id_chi_tiet = request.getParameter("id_chi_tiet");

		JsonObject obj = new JsonObject();
		//
		try {
			ChiTietSanPhamBO ctBO = new ChiTietSanPhamBO();
			// Nếu số lượng mua > hàng trong kho
			if (ctBO.getSoLuongSanPham(Integer.parseInt(id_chi_tiet)) < Integer.parseInt(soLuong)) {
				System.out.println(id_chi_tiet);
				obj.addProperty("result", "un-valid");
				String output = obj.toString();
				PrintWriter out = response.getWriter();
				out.print(output);
				System.out.println(output);
			} else {
				// Số lượng mua < hàng trong kho
				GioHangBo gh = (GioHangBo) session.getAttribute("gioHang");
				obj.addProperty("result", "valid");
				gh.IncreseOneProduct(Integer.parseInt(id_chi_tiet));

				Locale localeVN = new Locale("vi", "VN");
				NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

				String moneyFormat = currencyVN.format(gh.Tongtien());

				obj.addProperty("TongTien", moneyFormat);

				session.setAttribute("gioHang", gh);
				String output = obj.toString();
				PrintWriter out = response.getWriter();
				out.print(output);
				System.out.println(output);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	protected void reduce(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws ServletException, IOException {
		String soLuong = request.getParameter("soLuong");
		String id_chi_tiet = request.getParameter("id_chi_tiet");

		JsonObject obj = new JsonObject();
		//
		try {
			ChiTietSanPhamBO ctBO = new ChiTietSanPhamBO();

			GioHangBo gh = (GioHangBo) session.getAttribute("gioHang");
			
			gh.reduce(Integer.parseInt(id_chi_tiet));

			Locale localeVN = new Locale("vi", "VN");
			NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

			String moneyFormat = currencyVN.format(gh.Tongtien());

			obj.addProperty("TongTien", moneyFormat);

			session.setAttribute("gioHang", gh);
			String output = obj.toString();
			PrintWriter out = response.getWriter();
			out.print(output);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
