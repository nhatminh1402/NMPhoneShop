package controllerForAdmin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;

import bean.AnhSanPhamBEAN;
import bean.ChiTietSanPhamBEAN;
import bo.AnhSanPhamBO;
import bo.ChiTietSanPhamBO;
import bo.SanPhamBO;

/**
 * Servlet implementation class ProductDeatilController
 */
@WebServlet("/Admin/ProductDetail")
public class ProductDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductDetailController() {
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

		if (action == null) {
			try {
				AnhSanPhamBO aBO = new AnhSanPhamBO();
				ChiTietSanPhamBO ctBO = new ChiTietSanPhamBO();
				String id = request.getParameter("id");

				if (id == null) {
					response.sendRedirect("http://localhost:8080/NMPhoneShop/Admin/Quan-ly-san-pham");
					return;
				}

				request.setAttribute("listDeatil", ctBO.getAll(Integer.parseInt(id)));
				request.setAttribute("listAnh", aBO.listAllIMGProduct(Integer.parseInt(id)));
				request.setAttribute("product_describe", new SanPhamBO().selectDescribeProduct(Integer.parseInt(id)));
			
				request.getRequestDispatcher("ProductDetail.jsp").forward(request, response);

				return;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		switch (action) {
		case "insertIMG":
			insertIMG(request, response);
			break;
		case "updateIMG":
			UpdateThumbnailImg(request, response);
			break;
		case "deleteIMG":
			deleteIMG(request, response);
			break;
		case "updateDescribe":
			updateDescribe(request, response);
			break;
		case "insertDetailProduct":
			insertDetailProduct(request, response);
			break;
		case "deleteDescribe":
			deleteDescribe(request, response);
			break;
		case "selectDescribe":
			selectDescribe(request, response);
			break;
		case "UpdateChiTietSanPham":
			UpdateChiTietSanPham(request, response);
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

	protected void selectDescribe(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id_describe = request.getParameter("id_descibe");

		try {
			if (id_describe != null) {
				int id = Integer.parseInt(id_describe);
				ChiTietSanPhamBO ctBO = new ChiTietSanPhamBO();
				ChiTietSanPhamBEAN ct = ctBO.showDescribe(id);

				Gson json = new Gson();// chúng ta dùng thư viện Gson để chuyển đối tượng sang json
				String information = json.toJson(ct);
				PrintWriter out = response.getWriter();
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

	protected void deleteDescribe(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id_describe = request.getParameter("id_descibe");
		String id_product = request.getParameter("id_san_pham");

		try {
			if (id_describe != null) {
				int id_Des = Integer.parseInt(id_describe);
				int id_pro = Integer.parseInt(id_product);

				ChiTietSanPhamBO ctBO = new ChiTietSanPhamBO();
				ctBO.delete(id_Des);

				PrintWriter out = response.getWriter();
				out.print(renderChiTietSanPham(ctBO.getAll(id_pro)));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void insertDetailProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ramInput = request.getParameter("ramInput");
		String dungLuongInput = request.getParameter("dungLuongInput");
		String cameraTruocInput = request.getParameter("cameraTruocInput");
		String cameraSauInput = request.getParameter("cameraSauInput");
		String mauSacInput = request.getParameter("mauSacInput");
		String manHinhInput = request.getParameter("manHinhInput");
		String chipXuLyInput = request.getParameter("chipXuLyInput");
		String soLuongInput = request.getParameter("soLuongInput");
		String giaNhapVaoInput = request.getParameter("giaNhapVaoInput");
		String giaBanRaInput = request.getParameter("giaBanRaInput");
		String id_san_pham = request.getParameter("id_san_pham");

		try {
			float camera_Truoc = Float.parseFloat(cameraTruocInput);
			float camera_Sau = Float.parseFloat(cameraSauInput);
			float man_hinh = Float.parseFloat(manHinhInput);
			long gia_nhap = Long.parseLong(giaNhapVaoInput);
			long gia_ban = Long.parseLong(giaBanRaInput);
			int sl = Integer.parseInt(soLuongInput);
			int id_sp = Integer.parseInt(id_san_pham);

			ChiTietSanPhamBO ctBO = new ChiTietSanPhamBO();
			ctBO.insert(new ChiTietSanPhamBEAN(ramInput, dungLuongInput, camera_Truoc, camera_Sau, mauSacInput,
					man_hinh, chipXuLyInput, gia_nhap, gia_ban, sl, id_sp));
			PrintWriter out = response.getWriter();
			out.print(renderChiTietSanPham(ctBO.getAll(id_sp)));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void UpdateChiTietSanPham(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ramInput = request.getParameter("ramInput");
		String dungLuongInput = request.getParameter("dungLuongInput");
		String cameraTruocInput = request.getParameter("cameraTruocInput");
		String cameraSauInput = request.getParameter("cameraSauInput");
		String mauSacInput = request.getParameter("mauSacInput");
		String manHinhInput = request.getParameter("manHinhInput");
		String chipXuLyInput = request.getParameter("chipXuLyInput");
		String soLuongInput = request.getParameter("soLuongInput");
		String giaNhapVaoInput = request.getParameter("giaNhapVaoInput");
		String giaBanRaInput = request.getParameter("giaBanRaInput");
		String id_chi_tiet = request.getParameter("id_chi_tiet");
		String id_san_pham = request.getParameter("id_san_pham");

		try {
			float camera_Truoc = Float.parseFloat(cameraTruocInput);
			float camera_Sau = Float.parseFloat(cameraSauInput);
			float man_hinh = Float.parseFloat(manHinhInput);
			long gia_nhap = Long.parseLong(giaNhapVaoInput);
			long gia_ban = Long.parseLong(giaBanRaInput);
			int sl = Integer.parseInt(soLuongInput);
			int id_des = Integer.parseInt(id_chi_tiet);

			System.out.println(id_san_pham);
			ChiTietSanPhamBO ctBO = new ChiTietSanPhamBO();
			ctBO.Update(new ChiTietSanPhamBEAN(id_des, ramInput, dungLuongInput, camera_Truoc, camera_Sau, mauSacInput,
					man_hinh, chipXuLyInput, gia_nhap, gia_ban, sl));
			PrintWriter out = response.getWriter();
			out.print(renderChiTietSanPham(ctBO.getAll(Integer.parseInt(id_san_pham))));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void updateDescribe(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mo_ta = request.getParameter("describe");
		String id_sp = request.getParameter("id_san_pham");
		try {
			if (mo_ta != null && id_sp != null) {
				int ma_sp = Integer.parseInt(id_sp);
				SanPhamBO sBO = new SanPhamBO();
				sBO.updateDescribe(mo_ta, ma_sp);

				PrintWriter out = response.getWriter();
				out.print(sBO.selectDescribeProduct(ma_sp));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void DeleteLocalFileProductIMG(HttpServletRequest request, String fileName) {
		try {
			Files.deleteIfExists(Paths.get(request.getServletContext().getRealPath("") + File.separator + fileName));
		} catch (NoSuchFileException e) {
			System.out.println("No such file/directory exists");
		} catch (DirectoryNotEmptyException e) {
			System.out.println("Directory is not empty.");
		} catch (IOException e) {
			System.out.println("Invalid permissions.");
		}
	}

	protected void deleteIMG(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id_img = request.getParameter("id_img");
		String img_name = request.getParameter("img_name");
		String id_product = request.getParameter("id_san_pham");

		if (id_img != null && img_name != null && id_product != null) {
			try {
				int id_anh = Integer.parseInt(id_img);
				int id_san_pham = Integer.parseInt(id_product);
				AnhSanPhamBO aBO = new AnhSanPhamBO();
				aBO.deleteIMG(id_anh);
				DeleteLocalFileProductIMG(request, img_name);
				System.out.println("Delete sucessfull");

				// Trả lại response cho request ajax
				String output = renderOutput(new AnhSanPhamBO().listAllIMGProduct(id_san_pham));
				PrintWriter out = response.getWriter();
				out.print(output);

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	protected void UpdateThumbnailImg(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id_img = request.getParameter("idImg");
		String id_product = request.getParameter("id_san_pham");

		if (id_img != null && id_product != null) {
			try {
				int id_anh = Integer.parseInt(id_img);
				int id_san_pham = Integer.parseInt(id_product);
				AnhSanPhamBO aBO = new AnhSanPhamBO();
				
				aBO.ResetThumbnail(id_san_pham);
				aBO.updateThumbnail(id_anh);
				
				
				// Trả lại response cho request ajax
				String output = renderOutput(new AnhSanPhamBO().listAllIMGProduct(id_san_pham));
				PrintWriter out = response.getWriter();
				out.print(output);

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void insertIMG(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fileItemFactory);

		String nameimg = null; // Lưu tên file
		int id_product = 0;

		try {
			List<FileItem> fileItems = upload.parseRequest(request);// Lấy về các đối tượng gửi lên
			// duyệt qua các đối tượng gửi lên từ client gồm file và các control
			for (FileItem fileItem : fileItems) {
				if (!fileItem.isFormField()) { // nếu không phải là trường form bình thường thì xử lý file
					// Lấy ra tên file
					nameimg = fileItem.getName();

					if (!nameimg.equals("")) {
						// Lấy đường dẫn hiện tại, chủ ý xử lý trên dirUrl để có đường dẫn đúng
						String dirUrl = request.getServletContext().getRealPath("") + File.separator + "ProductFile";
						// Nếu thư mục đến đường dẫn chưa có folder thì tiến hành tạo
						File dir = new File(dirUrl);
						if (!dir.exists()) {// nếu ko có thư mục thì tạo ra
							dir.mkdir();
						}

						String fileImg = dirUrl + File.separator + nameimg;
						File file = new File(fileImg);// tạo file
						try {
							fileItem.write(file);// lưu file
							System.out.println("UPLOAD THÀNH CÔNG...!");
							System.out.println("Đường dẫn lưu file là: " + dirUrl);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} else// Neu la control
				{
					String fieldNameRequest = fileItem.getFieldName();
					if (fieldNameRequest.equals("id_san_pham")) {
						String id = fileItem.getString();
						id_product = Integer.parseInt(id);
					}
				}
			}

			// Tiến hành lưu ảnh vào CSDL
			if (id_product != 0 && nameimg != null) {
				AnhSanPhamBO aBO = new AnhSanPhamBO();
				aBO.insert(nameimg, id_product);
			}

			// Trả lại response cho request ajax
			String output = renderOutput(new AnhSanPhamBO().listAllIMGProduct(id_product));
			PrintWriter out = response.getWriter();
			out.print(output);

		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String renderOutput(ArrayList<AnhSanPhamBEAN> listImg) {
		String output = null;

		if (listImg.size() == 0) {
			output = "<h1\r\n" + "style=\"font-size: 17px; text-align: center; padding: 15px; font-weight: bold;\"\r\n"
					+ "	class=\"\">SẢN PHẨM CHƯA CÓ ẢNH ĐỂ HIỂN THỊ!</h1>";
		} else {
			output = "<table class=\"table table-hover text-nowrap\"><thead>\r\n"
					+ "										<tr>\r\n"
					+ "											<th>STT</th>\r\n"
					+ "											<th>HÌNH ẢNH</th><th>THUMBNAIL</th>\r\n"
					+ "											<th>THAO TÁC</th>\r\n"
					+ "										</tr>\r\n"
					+ "									</thead>\r\n" + "									<tbody>";
			int i = 1;
			for (AnhSanPhamBEAN img : listImg) {
				output += "<tr>\r\n" + "												<td>" + i + "\r\n"
						+ "												</td>\r\n"
						+ "												<td><img id=\"show-full-img\"\r\n"
						+ "													style=\"width: 150px; height: 150px\" alt=\"\"\r\n"
						+ "													src=\"" + img.getLink_anh() + "\"></td>\r\n"
						+ "	<td class=\"checked-thumbnail\"><input data-id=\""+img.getId_anh()+"\" type=\"checkbox\" name=\"checked-thumbnail\"";
				if(img.isThumbnail())  {
				 output +=	"<input data-id=\"${item.id_anh}\" type=\"checkbox\" name=\"checked-thumbnail\" checked=\"checked\"";
				} else {
					 output +=	"<input data-id=\"${item.id_anh}\" type=\"checkbox\" name=\"checked-thumbnail\"";
				}
				output+= "></td><td style=\"\" <i id=\"btn-delete-img\" data-id=\""
						+ img.getId_anh() + "\"\r\n"
						+ "													style=\"font-size: 18px; padding-right: 5px\"\r\n"
						+ "													class=\"bi bi-trash text-danger\"></i></td>\r\n"
						+ "											</tr>";
				i++;
			}

			output += "</tbody>\r\n" + "</table>";
		}

		return output;
	}

	public String renderChiTietSanPham(ArrayList<ChiTietSanPhamBEAN> ct) {
		String output = "";

		if (ct.size() == 0) {
			output = "<h1\r\n" + "style=\"font-size: 17px; text-align: center; padding: 15px; font-weight: bold;\"\r\n"
					+ "	class=\"\">SẢN PHẨM CHƯA CÓ ẢNH ĐỂ HIỂN THỊ!</h1>";
		} else {
			output += "<table class=\"table table-hover text-nowrap\">\r\n" + "			<thead>\r\n"
					+ "				<tr>\r\n" + "					<th>STT</th>\r\n"
					+ "					<th>RAM</th>\r\n" + "					<th>DUNG LƯỢNG</th>\r\n"
					+ "					<th>CAMERA-TRƯỚC</th>\r\n" + "					<th>CAMERA-SAU</th>\r\n"
					+ "					<th>MÀU SẮC</th>\r\n" + "					<th>MÀN HÌNH</th>\r\n"
					+ "					<th>CHIP</th>\r\n" + "					<th>GIÁ NHẬP VÀO</th>\r\n"
					+ "					<th>GIÁ BÁN RA</th>\r\n" + "					<th>SỐ LƯỢNG</th>\r\n"
					+ "					<th>THAO TÁC</th>\r\n" + "				</tr>\r\n" + "			</thead>\r\n"
					+ "			<tbody>";
			int stt = 1;

			Locale localeVN = new Locale("vi", "VN");
			NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

			for (ChiTietSanPhamBEAN item : ct) {
				String str1 = currencyVN.format(item.getGia_nhap_vao());
				String x1 = str1.replaceAll("[₫]", "VND");

				String str2 = currencyVN.format(item.getGia_ban_ra());
				String x2 = str2.replaceAll("[₫]", "VND");

				output += "<tr>\r\n" + "						<td>" + stt + "</td>\r\n"
						+ "						<td>" + item.getRam() + " GB</td>\r\n" + "						<td>"
						+ item.getDung_luong_luu_tru() + " GB</td>\r\n" + "						<td>"
						+ item.getCamera_truoc() + " MB</td>\r\n" + "						<td>" + item.getCamera_sau()
						+ " MB</td>\r\n" + "						<td>" + item.getMau_sac() + "</td>\r\n"
						+ "						<td>" + item.getMan_hinh() + " Inch</td>\r\n"
						+ "						<td>" + item.getChip() + "</td>\r\n"
						+ "						<td class=\"price\">" + x1 + "</td>\r\n"
						+ "						<td class=\"price\">" + x2 + "</td>\r\n"
						+ "						<td>" + item.getSo_luong() + "</td>\r\n"
						+ "						<td><i data-id=\"" + item.getId_thong_tin_chi_tiet()
						+ "\" style=\"font-size: 18px; padding-right: 5px\"\r\n"
						+ "							class=\"bi bi-pencil-square btn-update-product-describe text-warning\"></i> <i\r\n"
						+ "							style=\"font-size: 18px; padding-right: 5px\"\r\n"
						+ "							class=\"bi bi-trash btn-delete-describe text-danger\" data-id=\""
						+ item.getId_thong_tin_chi_tiet() + "\"></i></td>\r\n" + "					</tr>";
				stt++;
			}
			output += "</tbody>\r\n" + "		</table>";
		}

		return output;
	}
}
