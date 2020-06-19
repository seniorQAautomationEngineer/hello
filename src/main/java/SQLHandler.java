import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

public class SQLHandler {
    public static Connection connection;
    public static Statement stmt;

    public static void connect(String driverName, String url, String user, String password) throws Exception {
        Class.forName(driverName);
        if (user != null && password != null) {
            connection = DriverManager.getConnection(url, user, password);
        } else {
            connection = DriverManager.getConnection(url);
        }
        stmt = connection.createStatement();
    }

    public static ArrayList<String> getColumnData(String columnName, String tableName, String[] databasesName) {
        ArrayList<String> out = new ArrayList<>(200);
        for (int i = 0; i < databasesName.length; i++) {
            try {
                SQLHandler.connect("org.postgresql.Driver", "jdbc:postgresql://IPaddress:5432/" + databasesName[i], "username", "password");
                ResultSet rs = SQLHandler.stmt.executeQuery("SELECT " + columnName + " FROM " + tableName);
                while (rs.next()) {
                    String s = rs.getString(1);
                    if (s != null) {
                        out.add(s.trim());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                SQLHandler.disconnect();
            }
        }
        return out;
    }
    public static ArrayList<String> getMultipleData(String columnName, String tableName, String[] databasesName, String secondColumnName, HashSet<String> secondData) {
        ArrayList<String> out = new ArrayList<>(200);
        for (int i = 0; i < databasesName.length; i++) {
            try {
                SQLHandler.connect("org.postgresql.Driver", "jdbc:postgresql://IPaddress:5432/" + databasesName[i], "username", "password");
                ResultSet rs = SQLHandler.stmt.executeQuery("SELECT " + columnName + ", "+ secondColumnName+ " FROM " + tableName);
                while (rs.next()) {
                    String s = rs.getString(1);
                    if (s != null && secondData.contains(rs.getString(2).trim())) {
                        out.add(s.trim());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                SQLHandler.disconnect();
            }
        }
        return out;
    }

    public static ArrayList<String> getColumnDataFromQuery(String[] databasesName, String query) {
        ArrayList<String> out = new ArrayList<>();
        for (int j = 0; j < databasesName.length; j++) {
            try {
                SQLHandler.connect("org.postgresql.Driver", "jdbc:postgresql://IPaddress:5432/" + databasesName[j], "username", "password");
                ResultSet rs = SQLHandler.stmt.executeQuery(query);
                while (rs.next()) {
                    String s = rs.getString(1);
                    if (s != null) {
                        out.add(s.trim());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                SQLHandler.disconnect();
            }
        }
        return out;
    }

    public static void disconnect() {
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
