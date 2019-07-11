package app.Models;

import javafx.beans.property.SimpleStringProperty;

public abstract class AbstractUser
{

    private SimpleStringProperty Id = new SimpleStringProperty(this, "id");
    private SimpleStringProperty FirstName = new SimpleStringProperty(this, "firstName");
    private SimpleStringProperty LastName = new SimpleStringProperty(this, "lastName");
    private SimpleStringProperty Address = new SimpleStringProperty(this, "address");
    private SimpleStringProperty Phone = new SimpleStringProperty(this, "phone");
    private SimpleStringProperty Status = new SimpleStringProperty(this, "status");
    private SimpleStringProperty UserType = new SimpleStringProperty(this, "userType");

    public String getId() {        return Id.get();    }

    public SimpleStringProperty idProperty() {        return Id;    }

    public void setId(String id) {        this.Id.set(id);    }

    public String getFirstName() {        return FirstName.get();    }

    public SimpleStringProperty firstNameProperty() {        return FirstName;    }

    public void setFirstName(String firstName) {        this.FirstName.set(firstName);    }

    public String getLastName() {        return LastName.get();    }

    public SimpleStringProperty lastNameProperty() {        return LastName;    }

    public void setLastName(String lastName) {        this.LastName.set(lastName);    }

    public String getAddress() {        return Address.get();    }

    public SimpleStringProperty addressProperty() {        return Address;    }

    public void setAddress(String address) {        this.Address.set(address);    }

    public String getPhone() {        return Phone.get(); }

    public SimpleStringProperty phoneProperty() {        return Phone;    }

    public void setPhone(String phone) {        this.Phone.set(phone);    }

    public String getStatus() {        return Status.get();    }

    public SimpleStringProperty statusProperty() {        return Status;    }

    public void setStatus(String status) {        this.Status.set(status);  }

    public String getUserName()    {        return getFirstName() + " " + getLastName();    }

    public String getUserType() {        return UserType.get();    }

}
