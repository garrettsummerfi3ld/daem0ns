package io.garrettsummerfi3ld.daem0ns.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {
    static final String username = "";
    static final String password = "";
    static final String url = "";

    static Connection connection;

    public static void databaseConnect() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            databaseInit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void databaseDisconnect() {
        try {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void databaseInit() {

    }
}
