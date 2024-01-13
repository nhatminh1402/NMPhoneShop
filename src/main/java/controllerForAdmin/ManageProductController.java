package controllerForAdmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import bean.SanPhamBEAN;
import bo.HangSanXuatBO;
import bo.SanPhamBO;
import common.PageManager;

/**
 * Servlet implementation class ManageProductController
 */
@WebServlet("/Admin/Quan-ly-san-pham")
public class ManageProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageProductController() {
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

		String action = request.getParameter("action");

		try {
			if (action == null) {
				SanPhamBO spBO = new SanPhamBO();
				request.setAttribute("listSP", spBO.pagination(1, 5));
				request.setAttribute("listBrand", new HangSanXuatBO().getAll());
				int totalRecord = PageManager.getTotalRecord("San_Pham", "id_san_pham");
				request.setAttribute("listPage", PageManager.getTotalPage(5, totalRecord));
				request.getRequestDispatcher("ManageProducts.jsp").forward(request, response);
				return;
			}

			switch (action) {
			case "pagination":
				pagination(request, response);
				break;
			case "delete":
				delete(request, response);
				break;
			case "insert":
				insert(request, response);
				break;
			case "selectById":
				selectById(request, response);
				break;
			case "update":
				update(request, response);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
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

	protected void selectById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		try {
			if (id != null) {
				int id_product = Integer.parseInt(id);
				SanPhamBEAN sp = new SanPhamBO().selectById(id_product);

				Gson json = new Gson();
				String stringJson = json.toJson(sp);
				PrintWriter out = response.getWriter();
				out.print(stringJson);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ten_san_pham = request.getParameter("ten_san_pham");
		String he_dieu_hanh = request.getParameter("he_dieu_hanh");
		String tong_thang_bao_hanh = request.getParameter("tong_thang_bao_hanh");
		String id_hang = request.getParameter("id_hang");
		System.out.println(id_hang);

		try {
			if (ten_san_pham != null && he_dieu_hanh != null && tong_thang_bao_hanh != null && id_hang != null) {
				int month = Integer.parseInt(tong_thang_bao_hanh);
				int idBrand = Integer.parseInt(id_hang);
				SanPhamBO spBO = new SanPhamBO();
				spBO.insert(ten_san_pham, he_dieu_hanh, month, idBrand);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ten_san_pham = request.getParameter("ten_san_pham");
		String he_dieu_hanh = request.getParameter("he_dieu_hanh");
		String tong_thang_bao_hanh = request.getParameter("tong_thang_bao_hanh");
		String id_hang = request.getParameter("id_hang");
		String id_sp = request.getParameter("id_sp");
		System.out.println(id_hang);

		try {
			if (ten_san_pham != null && he_dieu_hanh != null && tong_thang_bao_hanh != null && id_hang != null) {
				int month = Integer.parseInt(tong_thang_bao_hanh);
				int idBrand = Integer.parseInt(id_hang);
				int id_product = Integer.parseInt(id_sp);
				SanPhamBO spBO = new SanPhamBO();

				spBO.update(id_product, ten_san_pham, he_dieu_hanh, month, idBrand);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String productId = request.getParameter("productId");
			if (productId != null) {
				int pId = Integer.parseInt(productId);
				SanPhamBO spBO = new SanPhamBO();
				spBO.delate(pId);
				// Tạo đối tượng JSON
				JsonObject jsonObject = new JsonObject();

				// Thêm dữ liệu vào đối tượng JSON
				jsonObject.addProperty("result", "success");

				// Chuyển đối tượng JSON thành chuỗi JSON
				String jsonString = jsonObject.toString();

				PrintWriter out = response.getWriter();
				out.print(jsonString);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void pagination(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String pageNumber = request.getParameter("pageNumber");
			int page = Integer.parseInt(pageNumber);
			SanPhamBO spBo = new SanPhamBO();

			ArrayList<SanPhamBEAN> ds = spBo.pagination(page, 5);
			int totalRecord = PageManager.getTotalRecord("San_Pham", "id_san_pham");
			int totalPage = PageManager.getTotalPage(5, totalRecord);

			PrintWriter out = response.getWriter();

			if (ds.size() == 0) {
				out.print("");
				return;
			}

			String outPut = "<div class=\"card-body table-responsive p-0\">\r\n"
					+ "							<table class=\"table table-hover text-nowrap\">\r\n"
					+ "								<thead>\r\n" + "									<tr>\r\n"
					+ "										<th>MÃ SẢN PHẨM</th>\r\n"
					+ "										<th>TÊN SẢN PHẨM</th>\r\n"
					+ "										<th>HỆ ĐIỀU HÀNH</th>\r\n"
					+ "										<th>NGÀY NHẬP KHO</th>\r\n"
					+ "										<th>BẢO HÀNH</th>\r\n"
					+ "										<th>THƯƠNG HIỆU</th>\r\n"
					+ "										<th>THAO TÁC</th>\r\n"
					+ "									</tr>\r\n" + "								</thead>\r\n"
					+ "								<tbody>";

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			for (SanPhamBEAN item : ds) {
				System.out.println(formatter.format(item.getNgay_nhap_hang()));
				outPut += "<tr>\r\n" + "	<td>" + item.getId_san_pham() + "</td>\r\n" + "	<td>"
						+ item.getTen_san_pham() + "</td>\r\n" + "<td>" + item.getHe_dieu_hanh() + "</td>\r\n"
						+ "	<td>" + formatter.format(item.getNgay_nhap_hang()) + "</td>\r\n" + "	<td>"
						+ item.getTong_thang_bao_hanh() + " tháng</span></td>\r\n" + "	<td>" + item.getTen_hang()
						+ "</td>\r\n" + "	<td data-id=" + item.getId_san_pham() + "><a\r\n"
						+ "												href=\"Admin/ProductDetail?id="+item.getId_san_pham()+"\"> <i\r\n"
						+ "													style=\"font-size: 18px; padding-right: 5px;\"\r\n"
						+ "													class=\"bi bi-eye see-picture text-primary\"></i>\r\n"
						+ "											</a> <i\r\n"
						+ "	style=\"font-size: 18px; padding-right: 5px\"\r\n"
						+ "	class=\"bi bi-pencil-square text-warning btn-update\"></i> <i\r\n"
						+ "	style=\"font-size: 18px; padding-right: 5px\"\r\n"
						+ "	class=\"bi bi-trash text-danger btn-delete\"></i></td>\r\n" + "	</tr>";
			}

			outPut += "</c:forEach>\r\n" + "</tbody>\r\n" + "	</table>\r\n" + "</div>";

			outPut += "<!-- /.card-body -->\r\n" + "						<div class=\"card-footer clearfix\">\r\n"
					+ "							<ul id=\"product-pagination\" class=\"pagination pagination-sm m-0 float-right\">";

			for (int i = 1; i <= totalPage; i++) {
				if (i == page) {

					outPut += "<li data-id=\"" + i
							+ "\" class=\"page-item active page-product-item\"><a class=\"page-link\">" + i
							+ "</a></li>";

				} else {
					outPut += "<li data-id=\"" + i + "\" class=\"page-item page-product-item\"><a class=\"page-link\">"
							+ i + "</a></li>";
				}
			}

			outPut += "</ul>\r\n" + "						</div>\r\n"
					+ "						<!--end card footer-->\r\n" + "						</div>\r\n"
					+ "					</div>";
			out.print(outPut);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
