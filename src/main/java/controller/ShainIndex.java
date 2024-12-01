package controller;

import beans.ShainBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.ShainLogic;

@WebServlet({"/ShainIndex"})
public class ShainIndex extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public ShainIndex() {
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      ShainLogic shainLogic = new ShainLogic();

      try {
         ArrayList<ShainBean> shainList = shainLogic.getAllShain();
         request.setAttribute("shainList", shainList);
         request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
      } catch (Throwable var5) {
         var5.printStackTrace();
         request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
      }

   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doGet(request, response);
   }
}
