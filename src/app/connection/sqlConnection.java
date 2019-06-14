package app.connection;

import app.Models.Client;
import app.Models.Salesman;
import app.Models.User;

import java.sql.*;
import java.util.ArrayList;


public class sqlConnection {
    private static sqlConnection ourInstance = new sqlConnection();
    private Connection connection;
    private String connectionString;

    public static sqlConnection getInstance() {
        return ourInstance;
    }

    private sqlConnection() {
        // Connect to database
        String hostName = "leadsbezeq.database.windows.net";
        String dbName = "leadsBezeq";
        String user = "ilcattivo";
        String password = "zaq!2wsx";
        connectionString = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
                + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);

    }


    public User Connect(String Username, String Password)
    {
        User user = null;

        try {
            connection = DriverManager.getConnection(connectionString);
            String schema = connection.getSchema();
            String selectSql = "SELECT * FROM [dbo].[users] WHERE [dbo].[users].[userId] ='" + Username + "' AND [dbo].[users].[userPassword] ='"+Password+"'";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                if (resultSet.next())
                {
                    if (resultSet.getBoolean("insurance"))
                    {
                        user = new Salesman();
                        user.setId(Username);
                        user.setFirstName(resultSet.getString("userFirstName"));
                        user.setLastName(resultSet.getString("userLastName"));
                        user.setAddress(resultSet.getString("userAddress"));
                        user.setPhone(resultSet.getString("userPhone"));
                        user.setStatus(resultSet.getString("userStatus"));
                    }
                    else
                    {
                        user = new Client();
                        user.setId(Username);
                        user.setFirstName(resultSet.getString("userFirstName"));
                        user.setLastName(resultSet.getString("userLastName"));
                        user.setAddress(resultSet.getString("userAddress"));
                        user.setPhone(resultSet.getString("userPhone"));
                        user.setStatus(resultSet.getString("userStatus"));
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }


    public void SendQuery(String sqlQuery) {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


        public void CloseConnection()
    {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Object[]> GetData(String sqlQuery) {
        ResultSet resultSet = null;
        ArrayList<Object[]> results = new ArrayList<Object[]>();
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            int nCol = resultSet.getMetaData().getColumnCount();
            while (resultSet.next())
            {
                Object[] row = new Object[nCol];
                for(int i = 0 ; i < nCol ; i++)
                {
                    Object obj = resultSet.getObject(i);
                    row[i] = obj;
                }
                results.add(row);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return results;
    }
}
