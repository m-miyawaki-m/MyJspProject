package controller;

import beans.ShainBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.ShainLogic;

@WebServlet({"/ShainUpdateComplete"})
public class ShainUpdateComplete extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public ShainUpdateComplete() {
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      ShainLogic shainLogic = new ShainLogic();

      try {
         ShainBean shainBean = shainLogic.getShainBean(request);
         shainLogic.updateShain(shainBean);
         response.sendRedirect("ShainIndex");
      } catch (Throwable var5) {
         var5.printStackTrace();
         request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
      }

   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doGet(request, response);
   }
}
