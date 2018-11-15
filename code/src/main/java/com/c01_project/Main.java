package com.c01_project;

import com.c01_project.database.databaseSetup;

import java.sql.SQLException;

public class Main {

  public static void main(String[] args) {
    try {
      databaseSetup.main(args);
    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      c01_project.gui.LoginGUI.main(args);
    }
  }

}
