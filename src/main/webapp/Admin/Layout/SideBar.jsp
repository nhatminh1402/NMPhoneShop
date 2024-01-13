<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<!-- Brand Logo -->
	<a class="brand-link"> <img src="public/dist/img/AdminLTELogo.png"
		alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
		style="opacity: .8"> <span class="brand-text font-weight-light">NMphoneShop</span>
	</a>

	<!-- Sidebar -->
	<div class="sidebar">



		<!-- Sidebar Menu -->
		<nav class="mt-2">
			<ul class="nav nav-pills nav-sidebar flex-column"
				data-widget="treeview" role="menu" data-accordion="false">
				<!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->

				<li class="nav-item"><a href="Admin/Quan-ly-hang-san-xuat"
					class="nav-link"> <i class="nav-icon fas fa-th"></i>
						<p>QUẢN LÝ HÃNG</p>
				</a></li>
				<li class="nav-item"><a href="Admin/Quan-ly-san-pham"
					class="nav-link"> <i class="bi bi-telephone"></i>
						<p>QUẢN LÝ SẢN PHẨM</p>
				</a></li>
				<li class="nav-item"><a 
					class="nav-link"> <i class="bi bi-person-circle"></i>
						<p>QUẢN LÝ TÀI KHOẢN</p>
				</a></li>
				<li class="nav-item"><a 
					class="nav-link"> <i class="bi bi-receipt"></i>
						<p>QUẢN LÝ ĐƠN HÀNG</p>
				</a></li>

				<li class="nav-item"><a href="#" class="nav-link"> <i class="bi bi-graph-up-arrow"></i>
						<p>
							THỐNG KÊ <i class="fas fa-angle-left right"></i> 
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="Admin/ThongKeHangTon"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>THỐNG KÊ HÀNG TỒN KHO</p>
						</a></li>
					</ul></li>

				<li class="nav-item"><a href="Admin/Logout" class="nav-link"> <i
						class="nav-icon far fa-circle text-danger"></i>
						<p class="text">Đăng xuất</p>
				</a></li>

			</ul>
		</nav>
		<!-- /.sidebar-menu -->
	</div>
	<!-- /.sidebar -->
</aside>