package app.Models;

import java.util.ArrayList;

public class Salesman extends AbstractUser
{
    public ArrayList<ClientInsurance> ClientsInsurances = new ArrayList<>();

    @Override
    public String toString()
    {
        return getId();
    }

}
