package controllerForUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.HangSanXuatBO;
import bo.SanPhamHomeBO;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String PaginationLink = "";
		String pageNumber = request.getParameter("pageNumber");
		int page = 1;
		if (pageNumber != null) {
			page = Integer.parseInt(pageNumber);
		}

		String searchValue = request.getParameter("searchKey");
		// Truy vấn theo hãng sản xuất
		String[] listBrand = request.getParameterValues("brand");
		Cookie CBrandCheck = null;
		if (listBrand != null) {
			if (listBrand[0].equals("all")) {
				listBrand = null;
				CBrandCheck = new Cookie("ListBrandChecked", "");
				CBrandCheck.setMaxAge(0);
				response.addCookie(CBrandCheck);
			} else {
				String ListBrandChecked = "";

				for (String checkedItem : listBrand) {
					ListBrandChecked += checkedItem + "-";
					PaginationLink += "brand=" + checkedItem + "&";
				}

				ListBrandChecked = ListBrandChecked.substring(0, ListBrandChecked.length() - 1);
				PaginationLink = PaginationLink.substring(0, PaginationLink.length() - 1);

				CBrandCheck = new Cookie("ListBrandChecked", ListBrandChecked);
				CBrandCheck.setMaxAge(60);
				response.addCookie(CBrandCheck);
			}
		}

		// Truy vấn theo giá tiền chọn vào
		String[] prices = request.getParameterValues("price");

		// Truy vấn theo giá tiền
		ArrayList<Long> listprice = new ArrayList<Long>();
		ArrayList<String> listpriceString = new ArrayList<String>();

		// Xử lý Cookie
		String listPriceCheck = "";
		Cookie cListPriceCheked = null;
		if (prices != null) {
			if (!prices[0].equals("all")) {
				for (String gia : prices) {
					String[] tachLaySo = gia.split("[-]");
					listpriceString.addAll(Arrays.asList(tachLaySo));
					// Xử lý tạo ra file Cookie lưu giá trị đã checked
					listPriceCheck += gia + "/";
					PaginationLink += "&price=" + gia;
				}
				if (listPriceCheck.length() > 0) {
					listPriceCheck = listPriceCheck.substring(0, listPriceCheck.length() - 1);
					cListPriceCheked = new Cookie("listPriceCheck", listPriceCheck);
					cListPriceCheked.setMaxAge(60);
				} else if (listPriceCheck.length() == 0) {
					cListPriceCheked = new Cookie("listPriceCheck", "");
					cListPriceCheked.setMaxAge(0);
				}
				response.addCookie(cListPriceCheked);

				for (String item : listpriceString) {
					listprice.add(Long.parseLong(item));
				}
				// Tìm min - max để truyền vào
				Long min = listprice.get(0);
				Long max = listprice.get(0);
				for (int i = 1; i < listprice.size(); i++) {
					if (listprice.get(i) < min) {
						min = listprice.get(i);
					}

					if (listprice.get(i) > max) {
						max = listprice.get(i);
					}
				}
				listprice.clear();
				listprice.add(min);
				listprice.add(max);
			} else {
				listprice = null;
				cListPriceCheked = new Cookie("listPriceCheck", "");
				cListPriceCheked.setMaxAge(0);
				response.addCookie(cListPriceCheked);
			}
		} else {
			listprice = null;
			cListPriceCheked = new Cookie("listPriceCheck", "");
			cListPriceCheked.setMaxAge(0);
			response.addCookie(cListPriceCheked);
		}

		String[] operation = request.getParameterValues("operation");
		String listOperationChecked = "";
		Cookie COperation = null;
		if (operation != null) {
			if (operation[0].equals("all")) {
				COperation = new Cookie("listOperationCheck", "");
				COperation.setMaxAge(0);
				response.addCookie(COperation);
				operation = null;
			} else {
				for (String item : operation) {
					listOperationChecked += item + '/';
					PaginationLink += "&operation=" + item;
				}
				listOperationChecked = listOperationChecked.substring(0, listOperationChecked.length() - 1);
				COperation = new Cookie("listOperationCheck", listOperationChecked);
				COperation.setMaxAge(60);
				response.addCookie(COperation);
			}
		} else {
			COperation = new Cookie("listOperationCheck", "");
			COperation.setMaxAge(0);
			response.addCookie(COperation);
		}

		if (searchValue != null) {
			PaginationLink += "&searchKey=" + searchValue;
		}

		try {

			HangSanXuatBO hBO = new HangSanXuatBO();
			SanPhamHomeBO spBO = new SanPhamHomeBO();

			request.setAttribute("listProduct", spBO.filter(listBrand, listprice, operation, searchValue, page, 5));
			request.setAttribute("listBrand", hBO.getAll());

			// Phân trang
			request.setAttribute("ListPage", spBO.getTotalPage(listBrand, listprice, operation, searchValue, 5));
			request.setAttribute("PaginationLink", PaginationLink);
			request.setAttribute("currentPage", page);
			request.getRequestDispatcher("Product.jsp").forward(request, response);
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

}
