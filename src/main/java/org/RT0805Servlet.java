package org;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/flash")
public class RT0805Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("flashMessage", "Bienvenue sur MyVideo");
        session.setAttribute("flashCategory", "info"); // You can use different categories like 'success', 'error', etc.
        response.sendRedirect("index.jsp"); // Redirect to the JSP page where you want to display the message
    }
}