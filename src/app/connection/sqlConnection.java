package app.connection;

import app.LoaderScreen;
import app.Main;
import app.Models.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import java.sql.*;
import java.util.ArrayList;


public class sqlConnection {

    private static sqlConnection ourInstance = new sqlConnection();
    private Connection connection;
    private String connectionString;
    private ResultSet resultSet = null;
    private ArrayList<ClientInsurance> ClientInsuranceResults = new ArrayList<>();
    private ArrayList<ClientInsuranceClaim> ClientInsuranceClaimResults = new ArrayList<>();

    private boolean tryingToConnect = false;
    private boolean active = true;


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


    public void connectToServer(String Username, String Password){
        if (tryingToConnect) {return;}
        tryingToConnect = true;
        final Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                Platform.runLater(()-> LoaderScreen.showLoadingScreen());
                Main.AppUser = Connect(Username,Password);
                while(active){}
                return null;
            }
        };

        task.setOnSucceeded(event -> {
            try
            {
                Platform.runLater(()->LoaderScreen.hideLoadingScreen());

            } catch (Exception e){}

        });

        Thread t = new Thread(task);
        t.start();
    }


    private User Connect(String Username, String Password)
    {
        User user = null;

        try {
            connection = DriverManager.getConnection(connectionString);
            String schema = connection.getSchema();
            String selectSql = "SELECT * FROM users WHERE users.userId ='" + Username + "' AND users.userPassword ='"+Password+"'";
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

        active = false;
        tryingToConnect = false;
        return user;
    }


    public void SendQuery(String sqlQuery) {
        try {
            System.out.println("SendQuery: " + sqlQuery);
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

    /**
     *
     * @return All login client insurances.
     */
    public ArrayList<ClientInsurance> GetClientInsurances() {
        String sqlQuery = "SELECT * FROM insurances WHERE insurances.clientId ='" + Main.AppUser.getId() + "'";
        System.out.println("GetClientInsurances() - " + sqlQuery);
        resultSet = null;
        ClientInsuranceResults.clear();

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next())
            {
                ClientInsurance clientInsurance = new ClientInsurance();
                clientInsurance.setInsuranceId(String.valueOf(resultSet.getInt("insuranceId")));
                clientInsurance.setUcId(resultSet.getString("clientId"));
                clientInsurance.setUsId(resultSet.getString("salesmanId"));
                clientInsurance.setInsuranceType(String.valueOf(resultSet.getInt("insuranceType")));
                clientInsurance.setInsuranceName(resultSet.getString("insuranceName"));
                clientInsurance.setInsuranceStatus(Insurance.getInsuranceStatus((resultSet.getByte("insuranceStatus"))));
                ClientInsuranceResults.add(clientInsurance);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return ClientInsuranceResults;
    }

    /**
     *
     * @param sqlQuery
     * @return All client insurances.
     */
    public ArrayList<ClientInsurance> GetSalesmanClientInsurances(String sqlQuery)
    {
        System.out.println("GetSalesmanClientInsurances() - " + sqlQuery);
        resultSet = null;
        ClientInsuranceResults.clear();

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next())
            {
                ClientInsurance clientInsurance = new ClientInsurance();
                clientInsurance.setInsuranceId(String.valueOf(resultSet.getInt("insuranceId")));
                clientInsurance.setUcId(resultSet.getString("clientId"));
                clientInsurance.setUsId(resultSet.getString("salesmanId"));

                clientInsurance.setUcFname(resultSet.getString("clientFname"));
                clientInsurance.setUcLname(resultSet.getString("clientLname"));

                clientInsurance.setInsuranceType(String.valueOf(resultSet.getInt("insuranceType")));
                clientInsurance.setInsuranceName(resultSet.getString("insuranceName"));
                clientInsurance.setInsuranceStatus(Insurance.getInsuranceStatus((resultSet.getByte("insuranceStatus"))));

                ClientInsuranceResults.add(clientInsurance);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return ClientInsuranceResults;
    }


    /**
     *
     * @param sqlQuery
     * @return All client insurances claims.
     */
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
                clientInsuranceClaim.setInsuranceId(String.valueOf(resultSet.getInt("insuranceId")));
                clientInsuranceClaim.setInsuranceName(String.valueOf(resultSet.getInt("insuranceName")));
                clientInsuranceClaim.setInsuranceStatus(resultSet.getString("insuranceStatus"));

                clientInsuranceClaim.setClientId(resultSet.getString("clientId"));
                clientInsuranceClaim.setClientFirstName(resultSet.getString("clientFname"));
                clientInsuranceClaim.setClientLastName(resultSet.getString("clientLname"));

                clientInsuranceClaim.setClaimStatus(String.valueOf(resultSet.getString("claimStatus")));
                clientInsuranceClaim.setClaimName(resultSet.getString("claimStatus"));
                clientInsuranceClaim.setClaimId((resultSet.getString("claimId")));


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

