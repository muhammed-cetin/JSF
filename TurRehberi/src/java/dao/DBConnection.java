package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {

    private final String url = "jdbc:postgresql://localhost:5432/turdb";
    private final String user = "postgres";
    private final String password = "123";

    private Connection connector = null;

    public Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conn;
    }

    public Connection getConnector() {
        if (this.connector == null) {
            this.connector = connect();
        }
        return this.connector;
    }

    public void setConnector(Connection connector) {
        this.connector = connector;
    }
}
