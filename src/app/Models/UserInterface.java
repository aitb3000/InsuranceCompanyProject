package app.Models;


public interface UserInterface
{

//    enum TypeUser
//    {
//        eUser,
//        eClient,
//        eSalesman,
//        eCustomerService
//    }

    static TypeUser GetTypeUser(byte id)
    {
        if (id == 1)        return TypeUser.eClient;
        else if (id == 2)   return TypeUser.eSalesman;
        else if (id == 3)   return TypeUser.eCustomerService;
        return TypeUser.eUser;
    }

    static int TypeUserToInteger(TypeUser typeUser)
    {
        if (typeUser == TypeUser.eUser) return 0;
        if (typeUser == TypeUser.eClient) return 1;
        if (typeUser == TypeUser.eSalesman) return 2;
        // if (typeUser == TypeUser.eCustomerService)
        return 3;
    }

    static int TypeUserToInteger(AbstractUser User)
    {
        if (User instanceof Client) return 1;
        if (User instanceof CustomerService) return 2;
        if (User instanceof Salesman) return 3;
        // if (typeUser == TypeUser.eCustomerService)
        return 0;
    }

    String toString();

}
