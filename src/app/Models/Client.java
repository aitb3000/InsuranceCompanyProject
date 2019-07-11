package app.Models;

import java.util.ArrayList;

public class Client extends AbstractUser  implements UserInterface
{
    public ArrayList<ClientInsurance> ClientInsurances = new ArrayList<ClientInsurance>();
    public ArrayList<ClientInsuranceClaim> ClientInsuranceClaim = new ArrayList<ClientInsuranceClaim>();

    @Override
    public String toString()
    {
        return getId();
    }

}
