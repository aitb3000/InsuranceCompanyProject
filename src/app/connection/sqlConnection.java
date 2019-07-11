package app.connection;

import app.LoaderScreen;
import app.Main;
import app.Models.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import java.sql.*;
import java.util.ArrayList;

import static app.Models.UserInterface.TypeUserToInteger;
import static app.Models.UserInterface.GetTypeUser;


public class sqlConnection {

    private static sqlConnection ourInstance = new sqlConnection();
    private Connection connection;
    private String connectionString;
    private ResultSet resultSet = null;

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
                Main.AppUser = new UserFactory(Connect(Username,Password));
                while(active){}
                return null;
            }
        };

        task.setOnSucceeded(event -> {
            try
            {
                Platform.runLater(()->LoaderScreen.hideLoadingScreen());
                Platform.runLater(()->Main.LoginController.showUserHome());
            } catch (Exception e){}

        });

        Thread t = new Thread(task);
        t.start();
    }


    private AbstractUser Connect(String Username, String Password)
    {
        AbstractUser user = null;

        try {
            connection = DriverManager.getConnection(connectionString);
            String schema = connection.getSchema();
            String selectSql = "SELECT * FROM users WHERE users.userId ='" + Username + "' AND users.userPassword ='"+Password+"'";
            System.out.println("Connect() - " + selectSql);
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectSql)) {

                if (resultSet.next())
                {
                    switch(GetTypeUser(resultSet.getByte("userType")))
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

    /**
     * SendQueryExecute for data manipulation like: insert, update and delete.
     * @param sqlQuery
     */
    public boolean SendQueryExecute(String sqlQuery)
    {
        boolean pass = false;

        try
        {
            System.out.println("SendQuery: " + sqlQuery);
            Statement statement = connection.createStatement();
            pass  = statement.execute(sqlQuery);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return !pass;
    }

    /**
     * SendQueryExecuteQuery for data retrieval like: select.
     * @param sqlQuery
     */
    public void SendQueryExecuteQuery(String sqlQuery) {
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
        ArrayList<ClientInsurance> ClientInsuranceResults = new ArrayList<>();

        String sqlQuery = "SELECT * FROM insurances WHERE insurances.clientId ='" + Main.AppUser.GetCurrentAppUser().getId() + "'";
        System.out.println("GetClientInsurances() - " + sqlQuery);
        resultSet = null;

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next())
            {
                ClientInsurance clientInsurance = new ClientInsurance();

                clientInsurance.setInsuranceId(String.valueOf(resultSet.getInt("insuranceId")));
                clientInsurance.setUcId(resultSet.getString("clientId"));
                clientInsurance.setUcLname(resultSet.getString("clientLname"));
                clientInsurance.setUcFname(resultSet.getString("clientFname"));
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
        ArrayList<ClientInsurance> ClientInsuranceResults = new ArrayList<>();

        System.out.println("GetSalesmanClientInsurances() - " + sqlQuery);
        resultSet = null;

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
        ArrayList<ClientInsuranceClaim> ClientInsuranceClaimResults = new ArrayList<>();
        System.out.println("GetDataClientInsuranceClaim() - " + sqlQuery);
        resultSet = null;

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next())
            {
                ClientInsuranceClaim clientInsuranceClaim = new ClientInsuranceClaim();

                clientInsuranceClaim.setClaimId(String.valueOf(resultSet.getInt("claimId")));
                clientInsuranceClaim.setClaimStatus(resultSet.getString("claimStatus"));

                clientInsuranceClaim.setInsuranceId(String.valueOf(resultSet.getInt("insuranceId")));
                clientInsuranceClaim.setInsuranceName(resultSet.getString("insuranceName"));
                clientInsuranceClaim.setInsuranceStatus(resultSet.getString("insuranceStatus"));

                clientInsuranceClaim.setClientId(resultSet.getString("clientId"));
                clientInsuranceClaim.setClientFirstName(resultSet.getString("clientFname"));
                clientInsuranceClaim.setClientLastName(resultSet.getString("clientLname"));

                ClientInsuranceClaimResults.add(clientInsuranceClaim);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return ClientInsuranceClaimResults;
    }

    public ArrayList<Claim> GetDataCustomerServicesClaims(String sqlQuery)
    {
        ArrayList<Claim> CustomerServicesClaims = new ArrayList<>();
        System.out.println("GetDataCustomerServicesClaims() - " + sqlQuery);
        resultSet = null;

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next())
            {
                Claim claim = new Claim();

                claim.setClaimId(String.valueOf(resultSet.getInt("claimId")));
                claim.setClaimStatus(resultSet.getString("claimStatus"));

                claim.setClientId(resultSet.getString("clientId"));
                claim.setClientFirstName(resultSet.getString("clientFname"));
                claim.setClientLastName(resultSet.getString("clientLname"));

                claim.setInsuranceId(String.valueOf(resultSet.getInt("insuranceId")));
                claim.setInsuranceName(resultSet.getString("insuranceName"));
                claim.setInsuranceStatus(resultSet.getString("insuranceStatus"));

                claim.setCustomerServiceFirstName(resultSet.getString("customerServiceId"));
                claim.setCustomerServiceId(resultSet.getString("customerServiceFname"));
                claim.setCustomerServiceLastName(resultSet.getString("customerServiceLname"));

                CustomerServicesClaims.add(claim);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return CustomerServicesClaims;
    }

    public ArrayList<Client> GetAllUserClients()
    {

        String sqlQuery = "SELECT * FROM users WHERE userType='" + TypeUserToInteger(TypeUser.eClient)+ "'";
        ArrayList<Client> UserClients = new ArrayList<>();
        System.out.println("GetAllUserClients() - " + sqlQuery);
        resultSet = null;

        try
        {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next())
            {
                Client client = new Client();

                client.setId(resultSet.getString("userId"));
                client.setPhone(resultSet.getString("userPhone"));
                client.setStatus(resultSet.getString("userStatus"));
                client.setFirstName(resultSet.getString("userFirstName"));
                client.setLastName(resultSet.getString("userLastName"));
                client.setAddress(resultSet.getString("userAddress"));

                UserClients.add(client);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return UserClients;
    }
}

