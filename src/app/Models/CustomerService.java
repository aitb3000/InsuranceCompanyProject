package app.Models;

import java.util.ArrayList;

public class CustomerService extends AbstractUser
{
    public ArrayList<Claim> Claims = new ArrayList<>();

    @Override
    public String toString()
    {
        return getId();
    }

}
