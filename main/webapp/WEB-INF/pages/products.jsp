<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>HamroGadi | Products</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
</head>

<body>
    <!-- Header -->
    <header>
        <div class="logo">
            <img src="${pageContext.request.contextPath}/image/logo.png" alt="HamroGadi Logo" />
            <span>HamroGadi</span>
        </div>
        <nav>
             <ul>
        <li><a href="${pageContext.request.contextPath}/home" >Home</a></li>
        <li><a href="${pageContext.request.contextPath}/CarServlet" class="active">Products</a></li>
        <li><a href="${pageContext.request.contextPath}/aboutServlet">About Us</a></li>
        <li><a href="${pageContext.request.contextPath}/ContactusServelt">Contact</a></li>
        <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
        <li><a href="${pageContext.request.contextPath}/UserProfileServlet">My Profile</a></li>
      
      </ul>
      </ul>
        </nav>
    </header>

    <!-- Featured Cars Section -->
 <section class="featured">
    <h2>Available Cars</h2>
    <div class="car-grid">
        <!-- Loop through each car in the carList -->
        <c:forEach var="car" items="${carList}">
            <div class="car-card">
                <img src="${car.url}" alt="${car.brand} ${car.model}" style="width: 100%; height: auto; object-fit: contain;" />
                <h3>${car.brand} ${car.model}</h3>
                <p>Price: NPR ${car.price}</p>
                <a href="CarDetailsServlet?id=${car.carID}">
                    <button>View Details</button>
                </a>
            </div>
        </c:forEach>
    </div>
</section>

    <!-- Why Choose Us -->
    <section class="why-choose-us">
        <h2>Why Choose HamroGadi?</h2>
        <ul>
            <li>✅ Trusted by thousands across Nepal</li>
            <li>✅ Verified Listings and Best Offers</li>
            <li>✅ Responsive across all devices</li>
        </ul>
    </section>

    <!-- Footer -->
    <footer>
        <p>© 2025 HamroGadi. All rights reserved.</p>
        <div class="footer-links">
            <a href="${pageContext.request.contextPath}/aboutServlet">About Us</a>
            <a href="contact.jsp">Contact</a>
        </div>
    </footer>

</body>
</html>