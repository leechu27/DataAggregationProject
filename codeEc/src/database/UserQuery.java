package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserQuery {
	/**
	 * Try to login with provided username and password
	 * @param username
	 * @param password
	 * @return type of user indicated by integer, -1 for failed login, 
	 * 1 for TEQ low level worker
	 * 2 for TEQ mid level worker
	 * 3 for TEQ high level worker
	 * 4 for Organization Owner
	 * @throws SQLException
	 */
	  public static int login(String username, String password) throws SQLException {
		  String sql = "select * from Users where username = '"+username+"' and password = '"+password+"';";
		  ResultSet rs = databaseSetup.runRawSQL(sql, "test.db");
		  int type=-1;
		  if (rs.next()) {
	          type=rs.getInt("type");
		  }
		  // 
		  return type;
	  }
	  
	  public static boolean addUser(String username, String password, int type) throws SQLException {
		  String sql="select * from Users where username = '"+username+"';";
		  ResultSet rs= databaseSetup.runRawSQL(sql,"test.db");
		  int rownum=0;
		  while(rs.next()) {
			  rownum+=1;
		  }
		  if(rownum!=0) {
			  return false;
		  }
		  sql="Insert INTO Users (username,password,type) values ('"+username+"','"+password+"',"+type+");";
		  databaseSetup.runRawSQL(sql,"test.db");
		  return true;
	  }
}
