package controller;

import beans.ShainBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.ShainLogic;

@WebServlet({"/ShainDelete"})
public class ShainDelete extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public ShainDelete() {
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      ShainLogic shainLogic = new ShainLogic();

      try {
         ShainBean shainBean = shainLogic.getShainBean(Integer.parseInt(request.getParameter("id")));
         request.setAttribute("shainBean", shainBean);
         request.getRequestDispatcher("/WEB-INF/view/delete.jsp").forward(request, response);
      } catch (Throwable var5) {
         var5.printStackTrace();
         request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
      }

   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doGet(request, response);
   }
}