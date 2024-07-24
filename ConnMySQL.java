package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnMySQL {
	// Informazioni di connessione al database MySQL
    private static String url = "jdbc:mysql://localhost:3306/"; // URL del database
    private static String dbName = "StorePc"; // Nome del database
    private static String user = "root"; // Nome utente
    private static String password = "1234miao"; // Password
    
    static {
        try {
            // Caricamento del driver JDBC per MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Impossibile trovare il driver JDBC.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url + dbName, user, password);
    }

	public static String getUrl() {
		return url + dbName;
	}

	public static String getUser() {
		return user;
	}

	public static String getPwd() {
		return password;
	}

}
