package model;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionBase {
   public ConnectionBase() {
   }

   public static Connection getConnection() throws SQLException, NamingException {
      String localName = "java:comp/env/jdbc/myproject";
      Context context = new InitialContext();
      DataSource ds = (DataSource)context.lookup(localName);
      Connection con = ds.getConnection();
      return con;
   }
}
