<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>HamroGadi | Admin Dashboard</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-dashboard.css" />
</head>
<body>
  <!-- Sidebar -->
  <aside class="sidebar">
    <div class="logo">
      <img src="${pageContext.request.contextPath}/image/logo.png" alt="HamroGadi Logo" />
      <span>HamroGadi - Admin</span>
    </div>
    <ul>
      <li><a href="${pageContext.request.contextPath}/AdminDashboardServlet">Dashboard</a></li>
      <li><a href="${pageContext.request.contextPath}/AddCarServlet">Add Car Listing</a></li>
      <li><a href="${pageContext.request.contextPath}/RemoveServlet">Remove Listing</a></li>
      <li><a class="active" href="${pageContext.request.contextPath}/managetestServlet">Manage Test Rides</a></li>
      <li><a href="${pageContext.request.contextPath}/manageuserServlet">Manage Users</a></li>
      <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
    </ul>
  </aside>

  <!-- Main Content -->
  <main class="admin-dashboard">
    <div class="welcome-section">
      <div class="welcome-icon">👋</div>
      <h2>Welcome to Admin Dashboard</h2>
      <p>Manage your car listings, test rides, and users from the sidebar.</p>
    </div>
  </main>

  <!-- Footer -->
  <footer>
    <p>© 2025 HamroGadi. Admin Panel</p>
  </footer>
</body>
</html>