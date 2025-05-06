package hamrogadi12345.controller.Servlets;

import hamrogadi12345.controller.Dao.UserDao;
import hamrogadi12345.controller.models.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/UserProfileServlet")
public class UserProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var session = request.getSession(false);
        String email = (session != null) ? (String) session.getAttribute("userEmail") : null;

        // If not found in session, check cookies
        if (email == null) {
            var cookies = request.getCookies();
            if (cookies != null) {
                for (var cookie : cookies) {
                    if ("userEmail".equals(cookie.getName())) {
                        email = cookie.getValue();
                        break;
                    }
                }
            }
        }

        if (email == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            UserModel user = UserDao.getUserByEmail(email);
            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/WEB-INF/pages/updateprofile.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "User not found.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);  
    }
}