package app.Models;

import java.util.ArrayList;

public class Client extends  User
{
    public ArrayList<ClientInsurance> ClientInsurances = new ArrayList<ClientInsurance>();
    public ArrayList<ClientInsuranceClaim> ClientInsuranceClaim = new ArrayList<ClientInsuranceClaim>();
}
