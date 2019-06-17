package app.connection;

import app.Main;
import app.Models.*;

import java.io.Console;
import java.sql.*;
import java.util.ArrayList;


public class sqlConnection {

    private static sqlConnection ourInstance = new sqlConnection();
    private Connection connection;
    private String connectionString;
    private ResultSet resultSet = null;
    private ArrayList<ClientInsurance> ClientInsuranceResults = new ArrayList<>();
    private ArrayList<ClientInsuranceClaim> ClientInsuranceClaimResults = new ArrayList<>();


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
            System.out.println("Connect() - " + selectSql);
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                if (resultSet.next())
                {
                    switch(User.getTypeUser(resultSet.getByte("userType")))
                    {

                        case eClient:
                            user = new Client();
                            break;

                        case eSalesman:
                            user = new Salesman();
                            break;

                        case eCustomerService:
                            user = new CustomerService();
                            break;
                    }

                    if (user != null)
                    {
                        user.setId(resultSet.getString("userId"));
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

    public ArrayList<ClientInsurance> GetClientInsurances() {
        String sqlQuery = "SELECT * FROM [dbo].[insurances] WHERE [dbo].[insurances].[ucid] ='" + Main.AppUser.getId() + "'";
        System.out.println("GetClientInsurances() - " + sqlQuery);
        resultSet = null;
        ClientInsuranceResults.clear();

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next())
            {
                ClientInsurance clientInsurance = new ClientInsurance();
                clientInsurance.setInsuranceId(String.valueOf(resultSet.getInt("iid")));
                clientInsurance.setUcId(resultSet.getString("ucid"));
                clientInsurance.setUsId(resultSet.getString("usid"));
                clientInsurance.setInsuranceType(String.valueOf(resultSet.getInt("itype")));
                clientInsurance.setInsuranceName(resultSet.getString("iname"));
                clientInsurance.setInsuranceStatus(Insurance.getInsuranceStatus((resultSet.getByte("istatus"))));
                ClientInsuranceResults.add(clientInsurance);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return ClientInsuranceResults;
    }


    public ArrayList<ClientInsurance> GetDataClientInsurances(String sqlQuery)
    {
        System.out.println("GetDataClientInsurances() - " + sqlQuery);
        resultSet = null;
        ClientInsuranceResults.clear();

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next())
            {
                ClientInsurance clientInsurance = new ClientInsurance();
                clientInsurance.setInsuranceId(String.valueOf(resultSet.getInt("iid")));
                clientInsurance.setUcId(resultSet.getString("ucid"));
                clientInsurance.setUsId(resultSet.getString("usid"));

                clientInsurance.setUcId(resultSet.getString("userId"));
                clientInsurance.setUcFname(resultSet.getString("userFirstName"));
                clientInsurance.setUcLname(resultSet.getString("userLastName"));
                clientInsurance.setUcStatus(resultSet.getString("userStatus"));

                clientInsurance.setInsuranceType(String.valueOf(resultSet.getInt("itype")));
                clientInsurance.setInsuranceName(resultSet.getString("iname"));
                clientInsurance.setInsuranceStatus(Insurance.getInsuranceStatus((resultSet.getByte("istatus"))));


                ClientInsuranceResults.add(clientInsurance);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return ClientInsuranceResults;
    }


    public ArrayList<ClientInsuranceClaim> GetDataClientInsuranceClaim(String sqlQuery)
    {
        System.out.println("GetDataClientInsuranceClaim() - " + sqlQuery);
        resultSet = null;
        ClientInsuranceClaimResults.clear();

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next())
            {
                ClientInsuranceClaim clientInsuranceClaim = new ClientInsuranceClaim();
                clientInsuranceClaim.setInsuranceName(String.valueOf(resultSet.getInt("iname")));
                clientInsuranceClaim.setInsuranceStatus(resultSet.getString("ucid"));

                clientInsuranceClaim.setClientId(resultSet.getString("userId"));
                clientInsuranceClaim.setClientFirstName(resultSet.getString("userFirstName"));
                clientInsuranceClaim.setClientLastName(resultSet.getString("userLastName"));
                clientInsuranceClaim.setClaimStatus(resultSet.getString("userStatus"));

                clientInsuranceClaim.setClaimStatus(resultSet.getString("iname"));
                clientInsuranceClaim.setClaimId((resultSet.getString("cid")));


                ClientInsuranceClaimResults.add(clientInsuranceClaim);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return ClientInsuranceClaimResults;
    }
}

