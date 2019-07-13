package app.Models;

import java.util.ArrayList;

public class CustomerService extends AbstractUser  implements UserInterface
{
    public ArrayList<Claim> Claims = new ArrayList<>();
    public ArrayList<ClientInsurance> ClientInsurances = new ArrayList<>();
    public ArrayList<app.Models.ClientInsuranceClaim> ClientInsuranceClaim = new ArrayList<>();

    @Override
    public String toString()
    {
        return getId();
    }

}
