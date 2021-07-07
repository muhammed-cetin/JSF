package dao;

import java.sql.Connection;

public class Dao {

    private DBConnection dbc;
    private Connection conn;

    public DBConnection getDbc() {
        if (dbc == null) {
            dbc = new DBConnection();
        }
        return dbc;
    }

    public Connection getConn() {
        if (conn == null) {
            conn = getDbc().connect();
        }
        return conn;
    }
}
