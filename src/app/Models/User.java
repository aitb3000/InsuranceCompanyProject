package app.Models;

import javafx.beans.property.SimpleStringProperty;

public abstract class User {

    private SimpleStringProperty Id = new SimpleStringProperty(this, "id");
    private SimpleStringProperty FirstName = new SimpleStringProperty(this, "firstName");
    private SimpleStringProperty LastName = new SimpleStringProperty(this, "lastName");
    private SimpleStringProperty Address = new SimpleStringProperty(this, "address");
    private SimpleStringProperty Phone = new SimpleStringProperty(this, "phone");
    private SimpleStringProperty Status = new SimpleStringProperty(this, "status");



    public String getFirstName() {

        return FirstName.get();
    }

    public void setFirstName(String name)
    {
        FirstName.set(name);
    }

    public String getLastName() {

        return LastName.get();
    }

    public void setLastName(String name) {
        LastName.set(name);
    }

    public String getUserName() {
        return  (FirstName.get() + " " + LastName.get());
    }

    public String getAddress() {
        return Address.get();
    }

    public void setAddress(String address) {
        Address.set(address);
    }

    public String getPhone() {
        return Phone.get();
    }

    public void setPhone(String phone) {
        Phone.set(phone);
    }

    public String getStatus() {
        return Status.get();
    }

    public void setStatus(String status) {
        Status.set(status);
    }

    public String getId() {
        return Id.get();
    }

    public SimpleStringProperty idProperty() {
        return Id;
    }

    public void setId(String id) {
        this.Id.set(id);
    }
}
