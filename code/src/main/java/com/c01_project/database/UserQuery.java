package com.c01_project.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserQuery {
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
