package app.Models;

import java.util.ArrayList;

public class UserFactory
{

    /**
     * Creating the UserFactory that will handle all the user classes.
     */
    private Client Client = null;
    private CustomerService CustomerService = null;
    private Salesman Salesman = null;

    public UserFactory(AbstractUser User)
    {
        if (User instanceof Client)
        {
            Client = (app.Models.Client) User;
        }
        else if (User instanceof Salesman)
        {
            Salesman = (app.Models.Salesman) User;
        }
        else if (User instanceof CustomerService)
        {
            CustomerService = (app.Models.CustomerService) User;
        }
    }

    public AbstractUser GetCurrentAppUser()
    {
        if (Client!=null) return Client;

        if (CustomerService!=null) return CustomerService;

        if (Salesman!=null) return Salesman;

        return null;
    }


    public void SetClientInsurances(ArrayList<ClientInsurance> ClientInsurances)
    {
        if (Client!=null) Client.ClientInsurances = ClientInsurances;

        if (Salesman!=null) Salesman.ClientsInsurances = ClientInsurances;

    }

    public ArrayList<ClientInsurance> GetClientInsurances()
    {
        if (Client!=null) return Client.ClientInsurances;

        if (Salesman!=null) return Salesman.ClientsInsurances;

        return null;
    }

    public void SetClientInsuranceClaim(ArrayList<ClientInsuranceClaim> ClientInsuranceClaim)
    {
        if (Client!=null) Client.ClientInsuranceClaim = ClientInsuranceClaim;

    }

    public ArrayList<ClientInsuranceClaim> GetClientInsuranceClaim()
    {
        if (Client!=null) return Client.ClientInsuranceClaim;

        return null;
    }

    public void SetClaims(ArrayList<Claim> CustomerServicesClaims)
    {
        if (CustomerService!=null) CustomerService.Claims = CustomerServicesClaims;
    }

    public ArrayList<Claim> GetClaims()
    {
        if (CustomerService!=null) return CustomerService.Claims;

        return null;
    }

    public void SetClientsInsurances(ArrayList<ClientInsurance> SalesmanClientInsurances)
    {
        if (Client!=null) Client.ClientInsurances = SalesmanClientInsurances;

        if (Salesman!=null) Salesman.ClientsInsurances = SalesmanClientInsurances;
    }
}
