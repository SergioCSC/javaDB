package ru.javaDB;

import java.sql.*;

public class MockFiller {

    private Connection conn;

      MockFiller(Connection connection) {
          conn = connection;
      }

    public void truncate() throws SQLException {
        Statement statement = conn.createStatement();
        statement.executeQuery("TRUNCATE applications;");
        statement.executeQuery("TRUNCATE types;");
        statement.executeQuery("TRUNCATE applications_types;");
    }

    public void fill(int start, int end, int count) throws SQLException {
        PreparedStatement statement;
//        statement = conn.createStatement();
        statement = conn.prepareStatement("INSERT IGNORE INTO applications (name, developer) VALUES (?,?);");
        for (int i=start; i<end; i++) {
            statement.setString(1, "starcraft_" + i);
            statement.setString(2, "blizzard_" + i);
            statement.executeUpdate();
        }

        statement = conn.prepareStatement("INSERT IGNORE INTO types (name, description) VALUES (?,?);");
        for (int i=start; i<end; i++) {
            statement.setString(1, "quest_" + i);
            statement.setString(2, "brodilka_" + i);
            statement.executeUpdate();
        }

        statement = conn.prepareStatement("INSERT IGNORE INTO applications_types (application_name, type_name) VALUES (?,?);");
        for (int i=0; i<count; i++) {
            int appN = start + (int)Math.floor(Math.random() * (end - start));
            int typeN = start + (int)Math.floor(Math.random() * (end - start));
            statement.setString(1, "starcraft_" + appN);
            statement.setString(2, "quest_" + typeN);
            statement.executeUpdate();
        }

//        statement.executeQuery("INSERT INTO applications (name, developer) VALUES ("nginx","sysoev")");
//        statement.executeQuery("INSERT INTO types (name) VALUES ("arcada")");
//        statement.executeQuery("INSERT INTO types (name) VALUES ("strategy")");
    }
}
