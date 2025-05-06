<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="hamrogadi12345.controller.models.CarModel" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>HamroGadi | Home</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet" />
</head>

<body>

  <!-- Navbar -->
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
    </nav>
  </header>

  <!-- Main Banner with Search Bar -->
  <section class="main-banner">
    <div class="search-section">
      <h1>Find Your Right Car</h1>
      <p>Buy your next vehicle at the best value</p>
      <form>
        <select required>
          <option value="" disabled selected>Select Brand</option>
          <option value="Toyota">Toyota</option>
          <option value="Hyundai">Hyundai</option>
          <option value="Suzuki">Suzuki</option>
          <!-- Add more car brands as required -->
        </select>
        <select required>
          <option value="" disabled selected>Select Model</option>
          <option value="Sedan">Sedan</option>
          <option value="SUV">SUV</option>
          <option value="Hatchback">Hatchback</option>
          <!-- Add more models as required -->
        </select>
        <button type="submit">Search</button>
      </form>
    </div>
  </section>

  <!-- Featured Cars -->
  <section class="featured">
    <h2>Featured Cars</h2>
    <div class="car-grid">
     
   <%
List<CarModel> carList = (List<CarModel>) request.getAttribute("carList");
if (carList != null && !carList.isEmpty()) {
  int count = 0;
  for (CarModel car : carList) {
    if (count >= 3) break;
%>
    <div class="car-card">
      <img src="<%= car.getUrl() %>" alt="Car Image" style="width: 100%; height: auto; object-fit: contain;">
      <h3><%= car.getModel() %></h3>
      <p>NPR <%= car.getPrice() %></p>
      <a href="CarDetailsServlet?id=<%= car.getCarID() %>">
        <button>View Details</button>
      </a>
    </div>
<%
    count++;
  }
} else {
%>
  <p>No featured cars available at the moment.</p>
<%
}
%>
    </div>
  </section>

  <!-- Footer -->
  <footer>
    <p>&copy; 2025 HamroGadi. All Rights Reserved.</p>
    <div class="footer-links">
      <a href="privacy.jsp">Privacy Policy</a>
      <a href="terms.jsp">Terms of Service</a>
      <a href="contact.jsp">Contact Us</a>
    </div>
  </footer>

</body>

</html>