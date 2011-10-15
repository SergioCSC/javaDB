package ru.javaDB;

import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by IntelliJ IDEA.
 * User: ilya
 * Date: 15.10.11
 * Time: 23:18
 * To change this template use File | Settings | File Templates.
 */
public class MockWriter {
    private Connection conn;

    MockWriter(Connection connection) {
        conn = connection;
    }

    public int write(PrintWriter out) throws SQLException {
        Statement statement;
        ResultSet resultSet;
        statement = conn.createStatement();
        resultSet = statement.executeQuery("SELECT type_name FROM applications_types LIMIT 1;");
        if (resultSet.next()) {
            String typeName = resultSet.getString("type_name");
            PreparedStatement preparedStatement = conn.prepareStatement("" +
                    "SELECT a.* " +
                    "FROM applications AS a, applications_types AS at " +
                    "WHERE at.application_name = a.name AND at.type_name = ?;");
            preparedStatement.setString(1, typeName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String developer = resultSet.getString(2);
                out.printf("%s: %s\n", name, developer);
            }

        }
        out.flush();
        return 0;
    }
}
