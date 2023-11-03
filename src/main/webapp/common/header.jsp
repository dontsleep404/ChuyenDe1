<%-- <%@page import="java.util.List"%>
<%@page import="entity.Product"%>
<%@page import="dao.DAO"%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="main-content-wrapper d-flex clearfix">
	<!-- Mobile Nav (max width 767px)-->
	<div class="mobile-nav">
		<!-- Navbar Brand -->
		<div class="amado-navbar-brand">
			<a href="Home.jsp"><img src="img/core-img/logo3.jpg" alt=""></a>
		</div>
		<!-- Navbar Toggler -->
		<div class="amado-navbar-toggler">
			<span></span><span></span><span></span>
		</div>
	</div>

	<!-- Header Area Start -->
	<header class="header-area clearfix">
		<!-- Close Icon -->
		<div class="nav-close">
			<i class="fa fa-close" aria-hidden="true"></i>
		</div>
		<!-- Logo -->
		<div class="logo">
			<a href="index.html"><img src="img/core-img/logo3.jpg" alt=""></a>
		</div>
		<!-- Amado Nav -->
		<nav class="amado-nav">
			<ul>
				<li><a href="ListController">Home</a></li>

                    <c:if test="${sessionScope.acc.role == 1}">
                    <li><a href="ManagerControl">Management</a></li>
                    </c:if>
				<li><a href="#">Contact Us</a></li>
				<c:if test="${sessionScope.acc == null}">
					<li><a href="Login.jsp">Login</a></li>
				</c:if>
				<c:if test="${sessionScope.acc != null}">
					<li><a href="LogoutServlet">Logout</a></li>
				</c:if>
			</ul>
		</nav>
		<div class="amado-btn-group mt-30 mb-100">

		</div>
		<!-- Cart Menu -->
		<div class="cart-fav-search mb-100">
			<a href="#" class="fav-nav"><img src="img/core-img/favorites.png"
				alt=""> Favourite</a>
		</div>
		<!-- Social Button -->
		<div class="social-info d-flex justify-content-between">
			<a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a> <a
				href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a> <a
				href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a> <a
				href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
		</div>
	</header>