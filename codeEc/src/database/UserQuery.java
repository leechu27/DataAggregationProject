package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserQuery {
	public static final int TEQLOW= 1;
	public static final int TEQMID	=2;
	public static final int TEQHIGH	=3;
	public static final int ORGANIZATION=4;
	public static final int USERNOTFOUND=-1;
	/**
	 * Try to login with provided username and password
	 * @param username
	 * @param password
	 * @return type of user indicated by integer, -1 for failed login, 
	 * @throws SQLException
	 */
	  public static int login(String username, String password) throws SQLException {
		  DatabaseQuery dq = new DatabaseQuery("test.db");
		  String sql = "select * from Users where username = '"+username+"' and password = '"+password+"';";
		  ResultSet rs = dq.queryWithSQL(sql);
		  int type=USERNOTFOUND;
		  if (rs.next()) {
	          type=rs.getInt("type");
		  }
		  // 
		  return type;
	  }
	  /**
	   * Add user based on username, password and usertype that's imported
	   * @param username
	   * @param password
	   * @param type
	   * @return
	   * @throws SQLException
	   */
	  public static boolean addUser(String username, String password, int type) throws SQLException {
		  DatabaseQuery dq = new DatabaseQuery("test.db");
		  String sql="select * from Users where username = '"+username+"';";
		  ResultSet rs= dq.queryWithSQL(sql);
		  int rownum=0;
		  while(rs.next()) {
			  rownum+=1;
		  }
		  if(rownum!=0) {
			  return false;
		  }
		  sql="Insert INTO Users (username,password,type) values ('"+username+"','"+password+"',"+type+");";
		  dq.updateWithSQL(sql);
		  return true;
	  }
}
