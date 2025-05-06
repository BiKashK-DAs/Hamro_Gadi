<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <title>Update Profile | HamroGadi</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
    <link rel="stylesheet" href="updateuser.css" />
    <style>
        /* Additional CSS for max-width of the form */
        .update-form-wrapper {
            display: flex;
            justify-content: center;
            padding: 2rem 0;
        }

        .form-container {
            width: 100%;
            max-width: 600px; /* Set max width for the form */
            padding: 2rem;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        /* Margin applied to the submit button */
        button[type="submit"] {
            margin-top: 20px;
            padding: 10px 20px;
           
            cursor: pointer;
        }
    </style>
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
        <li><a href="${pageContext.request.contextPath}/home" class="active">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/CarServlet">Products</a></li>
        <li><a href="${pageContext.request.contextPath}/aboutServlet">About Us</a></li>
        <li><a href="${pageContext.request.contextPath}/ContactusServelt">Contact</a></li>
        <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
        <li><a href="${pageContext.request.contextPath}/UserProfileServlet">My Profile</a></li>
      
      </ul>
        </nav>
    </header>

    <!-- Update Profile Form -->
    <section class="update-form-wrapper">
        <div class="form-container">
            <h2>Update Your Profile</h2>
            <%@ page import="hamrogadi12345.controller.models.UserModel" %>
            <%
                UserModel user = (UserModel) request.getAttribute("user");
            %>
            <form action="UpdateUserServlet" method="POST">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" value="<%= user != null ? user.getName() : "" %>" required />

                <label for="email">Email</label>
                <input type="email" id="email" name="email" value="<%= user != null ? user.getEmail() : "" %>" readonly required />

                <label for="phone">Phone</label>
                <input type="tel" id="phone" name="phone" value="<%= user != null ? user.getPhoneNumber() : "" %>" required />

                <label for="address">Address</label>
                <input type="text" id="address" name="address" value="<%= user != null ? user.getAddress() : "" %>" required />

                <label for="password">Password</label>
                <input type="password" id="password" name="password" value="<%= user != null ? user.getPassword() : "" %>" required />

         

                <button type="submit">Save Changes</button>
            </form>
        </div>
    </section>

    <!-- Footer -->
    <footer>
        <p>© 2025 HamroGadi. All rights reserved.</p>
    </footer>

</body>

</html>