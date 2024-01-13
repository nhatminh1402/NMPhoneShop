package controllerForAdmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import bean.HangSanXuatBEAN;
import bo.HangSanXuatBO;

/**
 * Servlet implementation class ManageBrandController
 */
@WebServlet("/Admin/Quan-ly-hang-san-xuat")
public class ManageBrandController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageBrandController() {
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

		try {

			String requestReceived = request.getParameter("action");

			// Trường hợp chạy lần đầu
			if (requestReceived == null) {
				// Trường hợp client gởi request lần đầu tiên
				HangSanXuatBO hBO = new HangSanXuatBO();
				request.setAttribute("ListBrand", hBO.getListPaginate(5, 1));
				request.setAttribute("totalPage", hBO.getTotalPage(5));
				request.getRequestDispatcher("ManageBrand.jsp").forward(request, response);
				return;
			}

			if (requestReceived != null) {

				switch (requestReceived) {
				case "pagination":
					pagination(request, response);
					break;
				case "check-brand-exist":
					isBrandExist(request, response);
					break;
				case "insert":
					insert(request, response);
					break;
				case "delete":
					deleteById(request, response);
					break;
				case "update":
					update(request, response);
					break;
				default:
					break;
				}
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

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("brandId");
		String brandName = request.getParameter("brandName");

		if (id != null) {
			HangSanXuatBO hBO = new HangSanXuatBO();
			try {
				int IdBrand = Integer.parseInt(id);
				hBO.updateById(IdBrand, brandName);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	protected void deleteById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("brandId");

		if (id != null) {
			HangSanXuatBO hBO = new HangSanXuatBO();
			try {
				int IdBrand = Integer.parseInt(id);
				hBO.deleteById(IdBrand);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	protected void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String brandName = request.getParameter("brandName");

		try {
			if (brandName != null) {
				HangSanXuatBO hBO = new HangSanXuatBO();
				hBO.insert(brandName);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	protected void isBrandExist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String brandName = request.getParameter("brandName");

			JsonObject jsonObject = new JsonObject();

			HangSanXuatBO hBo = new HangSanXuatBO();

			jsonObject.addProperty("result", hBo.isBrandExist(brandName));
			System.out.println(hBo.isBrandExist(brandName));

			String json = jsonObject.toString();

			PrintWriter out = response.getWriter();

			out.print(json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	protected void pagination(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageNumber = request.getParameter("pageNumber");
		ArrayList<HangSanXuatBEAN> listBrand = new ArrayList<HangSanXuatBEAN>();
		int totalPage = 0;
		int pageNum = 0;
		PrintWriter out = response.getWriter();

		if (pageNumber != null) {

			try {
				pageNum = Integer.parseInt(pageNumber);
				HangSanXuatBO hBO = new HangSanXuatBO();
				listBrand = hBO.getListPaginate(5, pageNum);
				totalPage = hBO.getTotalPage(5);

			} catch (NumberFormatException e) {
				pageNum = 1;
			} catch (Exception e) {
				listBrand = null;
			}

			if (listBrand.size() == 0) {
				out.println("");
			} else {
				String output = "<div id=\"data-table-brand\" class=\"card-body table-responsive p-0\">\r\n"
						+ "          <table class=\"table table-hover text-nowrap\">\r\n" + "            <thead>\r\n"
						+ "              <tr>\r\n" + "                <th>ID</th>\r\n"
						+ "                <th>HÃNG SẢN XUẤT</th>\r\n" + "                <th>HÀNH ĐỘNG</th>\r\n"
						+ "              </tr>\r\n" + "            </thead>\r\n" + "            <tbody>";
				for (HangSanXuatBEAN item : listBrand) {
					output += "<tr>\r\n" + "                  <td>" + item.getId_hang() + "</td>\r\n"
							+ "                  <td>" + item.getHang_san_xuat() + "</td>\r\n"
							+ "                  <td data-id=\"" + item.getId_hang() + "\">\r\n"
							+ "                    <button\r\n" + "                      type=\"button\"\r\n"
							+ "                      class=\"brand-button brand-button-update btn btn-primary\"\r\n"
							+ "                    >\r\n"
							+ "                      <i class=\"bi bi-pencil-square\"></i> CHỈNH SỬA\r\n"
							+ "                    </button>\r\n" + "                    <button\r\n"
							+ "                      type=\"button\"\r\n"
							+ "                      class=\"brand-button-delete btn btn-danger\"\r\n"
							+ "                      data-id=\"37\"\r\n" + "                    >\r\n"
							+ "                      <i class=\"bi bi-trash3\"></i> XOÁ\r\n"
							+ "                    </button>\r\n" + "                  </td>\r\n"
							+ "                </tr>";

				}

				output += "</tbody>\r\n" + "          </table>\r\n" + "        </div>";

				if (totalPage > 1) {
					output += " <div class=\"card-footer clearfix\">\r\n"
							+ " <ul class=\"pagination pagination-sm m-0 float-right\">";
					for (int i = 1; i <= totalPage; i++) {
						if (i == pageNum) {
							output += "<li data-page=\"" + i + "\" class=\"page-item active\">\r\n"
									+ "              <a style=\"font-size: 17px\" class=\"page-link\">" + i + "</a>\r\n"
									+ "            </li>";
						} else {
							output += "<li data-page=\"" + i + "\" class=\"page-item\">\r\n"
									+ "              <a style=\"font-size: 17px\" class=\"page-link\">" + i + "</a>\r\n"
									+ "            </li>";
						}

					}
					output += "</ul></div>";

				}
				out.print(output);
			}
		}
	}

}
