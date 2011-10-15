package ru.javaDB;

import java.io.PrintWriter;
import java.sql.*;

public class MainApp {
    
    public static void main(String[] args) {
        Connection connection = null;
        PrintWriter out = new PrintWriter(System.out);
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/enotsdb?user=enotsname&password=enotspass");
            MockFiller filler = new MockFiller(connection);
            filler.truncate();
            filler.fill(0, 1000, 20000);

            MockWriter writer = new MockWriter(connection);
            // writer.write(out);

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
    
}
