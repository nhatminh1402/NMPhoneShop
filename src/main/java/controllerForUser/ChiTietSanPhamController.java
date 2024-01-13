package controllerForUser;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.ChiTietSanPhamBEAN;
import bean.SanPhamBEAN;
import bo.AnhSanPhamBO;
import bo.ChiTietSanPhamBO;
import bo.SanPhamBO;

/**
 * Servlet implementation class ChiTietSanPhamController
 */
@WebServlet("/ChiTietSanPham")
public class ChiTietSanPhamController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChiTietSanPhamController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String action = request.getParameter("action");

		if (action == null) {
			try {
				String productId = request.getParameter("product-id");
				if (productId != null) {
					int id_sp = Integer.parseInt(productId);
					AnhSanPhamBO anhBO = new AnhSanPhamBO();
					ChiTietSanPhamBO ctBO = new ChiTietSanPhamBO();
					request.setAttribute("listSlideProduct", anhBO.getAll(id_sp));
					request.setAttribute("listColor", ctBO.getAllColor(id_sp));
					request.setAttribute("product_describe", new SanPhamBO().selectDescribeProduct(id_sp));
					request.setAttribute("productName", new SanPhamBO().selectById(id_sp));

					request.getRequestDispatcher("ProductDetail.jsp").forward(request, response);
					return;
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		switch (action) {
		case "getAllBoNho":
			getAllBoNho(request, response);
			break;
		case "getRam":
			getAllRam(request, response);
			break;
		case "showPrice":
			showPrice(request, response);
			break;

		default:
			break;
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

	protected void showPrice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("product_id");
		String mau_sac = request.getParameter("mau_sac");
		String dung_luong = request.getParameter("dung_luong");
		String ram = request.getParameter("ram");

		try {
			if (productId != null && mau_sac != null && dung_luong != null && ram != null) {
				int id = Integer.parseInt(productId);
				ChiTietSanPhamBO ctBO = new ChiTietSanPhamBO();

				ChiTietSanPhamBEAN sp = ctBO.getGia(ram, dung_luong, mau_sac, id);

				PrintWriter out = response.getWriter();

				Gson json = new Gson();// chúng ta dùng thư viện Gson để chuyển đối tượng sang json
				String information = json.toJson(sp);

				out.print(information);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void getAllRam(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("product_id");
		String mau_sac = request.getParameter("mau_sac");
		String dung_luong = request.getParameter("dung_luong");

		try {
			if (productId != null && mau_sac != null && dung_luong != null) {
				int id = Integer.parseInt(productId);
				ChiTietSanPhamBO ctBO = new ChiTietSanPhamBO();
				ArrayList<String> ds = ctBO.getRamByMauVaDungLuong(dung_luong, mau_sac, id);

				PrintWriter out = response.getWriter();

				String outPut = "<div class=\"product-ram col-md-12\">\r\n"
						+ "<div class=\"list-button\"><h5 style=\"font-size: 16px\">Chọn bộ nhớ ram</h5>";

				for (String storage : ds) {
					outPut += "<button data-value=\"" + storage + "\" class=\"btn-ram\">" + storage + "</button>";
				}

				outPut += "</div></div>";

				out.print(outPut);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void getAllBoNho(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("product_id");
		String mau_sac = request.getParameter("mau_sac");
		String dung_luong = request.getParameter("dung_luong");
		try {
			if (productId != null && mau_sac != null) {
				int id = Integer.parseInt(productId);
				ChiTietSanPhamBO ctBO = new ChiTietSanPhamBO();
				ArrayList<String> ds = ctBO.getBoNhoBoiMauSac(mau_sac, id);

				PrintWriter out = response.getWriter();

				String outPut = "<div class=\"product-storage col-md-12\">\r\n"
						+ "<div class=\"list-button\"><h5 style=\"font-size: 16px\">Chọn dung lượng lưu trữ</h5>";

				for (String storage : ds) {
					outPut += "<button data-value=\"" + storage + "\" class=\"btn-storage\">" + storage + "</button>";
				}

				outPut += "</div></div>";

				out.print(outPut);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
