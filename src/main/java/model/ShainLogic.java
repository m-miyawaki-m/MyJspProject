package model;

import beans.ShainBean;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ShainLogic {
   public ShainLogic() {
   }

   public void deleteShain(int id) throws Throwable {
      String sql = "delete  from shain where id=?";
      Throwable var3 = null;

      try {
         Connection con = ConnectionBase.getConnection();

         try {
            PreparedStatement pstmt = con.prepareStatement(sql);

            try {
               pstmt.setInt(1, id);
               System.out.println(pstmt.toString());
               pstmt.executeUpdate();
            } finally {
               if (pstmt != null) {
                  pstmt.close();
               }

            }
         } catch (Throwable var17) {
            var3 = var17;

            if (con != null) {
               con.close();
            }

            throw var3;
         }

         if (con != null) {
            con.close();
         }

      } catch (Throwable var18) {
         if (var3 == null) {
            var3 = var18;
         } else if (var3 != var18) {
            var3.addSuppressed(var18);
         }

         throw var3;
      }
   }

   public void updateShain(ShainBean shainBean) throws Throwable {
      String sql = "update shain set name=?, sei=?, nen=?,  address=? where id=?";
      Throwable var3 = null;

      try {
         Connection con = ConnectionBase.getConnection();

         try {
            PreparedStatement pstmt = con.prepareStatement(sql);

            try {
               pstmt.setString(1, shainBean.getName());
               pstmt.setString(2, shainBean.getSei());
               pstmt.setInt(3, shainBean.getNen());
               pstmt.setString(4, shainBean.getAddress());
               pstmt.setInt(5, shainBean.getId());
               System.out.println(pstmt.toString());
               pstmt.executeUpdate();
            } finally {
               if (pstmt != null) {
                  pstmt.close();
               }

            }
         } catch (Throwable var17) {
            var3 = var17;

            if (con != null) {
               con.close();
            }

            throw var3;
         }

         if (con != null) {
            con.close();
         }

      } catch (Throwable var18) {
         if (var3 == null) {
            var3 = var18;
         } else if (var3 != var18) {
            var3.addSuppressed(var18);
         }

         throw var3;
      }
   }

   public ShainBean getShainBean(int id) throws Throwable {
      ShainBean shainBean = new ShainBean();
      String sql = "select id, name, sei, nen, address from shain where id=?";
      Throwable var4 = null;

      try {
         Connection con = ConnectionBase.getConnection();

         try {
            PreparedStatement pstmt = con.prepareStatement(sql);

            try {
               pstmt.setInt(1, id);
               System.out.println(pstmt.toString());
               ResultSet rs = pstmt.executeQuery();

               while(rs.next()) {
                  shainBean.setId(id);
                  shainBean.setName(rs.getString("name"));
                  shainBean.setSei(rs.getString("sei"));
                  shainBean.setNen(rs.getInt("nen"));
                  shainBean.setAddress(rs.getString("address"));
               }
            } finally {
               if (pstmt != null) {
                  pstmt.close();
               }

            }
         } catch (Throwable var19) {
            var4 = var19;

            if (con != null) {
               con.close();
            }

            throw var4;
         }

         if (con != null) {
            con.close();
         }

         return shainBean;
      } catch (Throwable var20) {
         if (var4 == null) {
            var4 = var20;
         } else if (var4 != var20) {
            var4.addSuppressed(var20);
         }

         throw var4;
      }
   }

   public ShainBean getShainBean(HttpServletRequest request) {
      ShainBean shainBean = new ShainBean();
      shainBean.setId(Integer.parseInt(request.getParameter("id")));
      shainBean.setName(request.getParameter("name"));
      shainBean.setSei(request.getParameter("sei"));
      shainBean.setNen(Integer.parseInt(request.getParameter("nen")));
      shainBean.setAddress(request.getParameter("address"));
      return shainBean;
   }

   public void insertShain(ShainBean shainBean) throws Throwable {
      String sql = "insert into shain(id, name, sei, nen, address) values(?,?,?,?,?);";
      Throwable var3 = null;

      try {
         Connection con = ConnectionBase.getConnection();

         try {
            PreparedStatement pstmt = con.prepareStatement(sql);

            try {
               pstmt.setInt(1, shainBean.getId());
               pstmt.setString(2, shainBean.getName());
               pstmt.setString(3, shainBean.getSei());
               pstmt.setInt(4, shainBean.getNen());
               pstmt.setString(5, shainBean.getAddress());
               System.out.println(pstmt.toString());
               pstmt.executeUpdate();
            } finally {
               if (pstmt != null) {
                  pstmt.close();
               }

            }
         } catch (Throwable var17) {
            var3 = var17;

            if (con != null) {
               con.close();
            }

            throw var3;
         }

         if (con != null) {
            con.close();
         }

      } catch (Throwable var18) {
         if (var3 == null) {
            var3 = var18;
         } else if (var3 != var18) {
            var3.addSuppressed(var18);
         }

         throw var3;
      }
   }

   public ArrayList<ShainBean> getAllShain() throws Throwable {
	ArrayList<ShainBean> shainList = new ArrayList<ShainBean>();
      String sql = "select id, name, sei, nen, address from shain";
      Throwable var3 = null;

      try {
         Connection con = ConnectionBase.getConnection();

         try {
            PreparedStatement pstmt = con.prepareStatement(sql);

            try {
               System.out.println(pstmt.toString());
               ResultSet rs = pstmt.executeQuery();

               while(rs.next()) {
                  ShainBean shainBean = new ShainBean();
                  shainBean.setId(rs.getInt("id"));
                  shainBean.setName(rs.getString("name"));
                  shainBean.setSei(rs.getString("sei"));
                  shainBean.setNen(rs.getInt("nen"));
                  shainBean.setAddress(rs.getString("address"));
                  shainList.add(shainBean);
               }
            } finally {
               if (pstmt != null) {
                  pstmt.close();
               }

            }
         } catch (Throwable var19) {
            var3 = var19;

            if (con != null) {
               con.close();
            }

            throw var3;
         }

         if (con != null) {
            con.close();
         }

         return shainList;
      } catch (Throwable var20) {
         if (var3 == null) {
            var3 = var20;
         } else if (var3 != var20) {
            var3.addSuppressed(var20);
         }

         throw var3;
      }
   }
}
