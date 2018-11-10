package c01_project.gui;

import java.sql.SQLException;

import c01_project.database.databaseSetup;

public class GUIManager {
	public static void main(String[] args) throws SQLException {
		databaseSetup.main(null);
		Login();
	}
	public static void Login() {
		LoginGUI.main(null);
	}
	public static void Register(){
		RegistrationGUI.main(null);
	}
	public static void TEQHighLevel() {
		TEQHighLevelGUI.main(null);
	}
	public static void TEQMidLevel() {
		TEQMidLevelGUI.main(null);
	}
	public static void TEQLowLevel() {
		TEQLowLevelGUI.main(null);
	}

}
