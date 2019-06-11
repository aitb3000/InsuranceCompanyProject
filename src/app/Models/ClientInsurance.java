package app.Models;

import javafx.beans.property.SimpleStringProperty;

public class ClientInsurance
{
    private SimpleStringProperty Id = new SimpleStringProperty(this, "id");
    private SimpleStringProperty FirstName = new SimpleStringProperty(this, "firstName");
    private SimpleStringProperty LastName = new SimpleStringProperty(this, "lastName");
    private SimpleStringProperty Status = new SimpleStringProperty(this, "lastName");

    private SimpleStringProperty insuranceType = new SimpleStringProperty(this, "insuranceName");
    private SimpleStringProperty insuranceName = new SimpleStringProperty(this, "insuranceName");
    private SimpleStringProperty insuranceStatus = new SimpleStringProperty(this, "insuranceName");

    public String getId() {
        return Id.get();
    }

    public SimpleStringProperty idProperty() {
        return Id;
    }

    public void setId(String id) {
        this.Id.set(id);
    }

    public String getFirstName() {
        return FirstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName.set(firstName);
    }

    public String getLastName() {
        return LastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName.set(lastName);
    }

    public String getInsuranceName() {
        return insuranceName.get();
    }

    public SimpleStringProperty insuranceNameProperty() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName.set(insuranceName);
    }

    public String getInsuranceStatus() {
        return insuranceStatus.get();
    }

    public SimpleStringProperty insuranceStatusProperty() {
        return insuranceStatus;
    }

    public void setInsuranceStatus(String insuranceStatus) {
        this.insuranceStatus.set(insuranceStatus);
    }

    public String getStatus() {
        return Status.get();
    }

    public SimpleStringProperty statusProperty() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status.set(status);
    }

    public String getInsuranceType() {
        return insuranceType.get();
    }

    public SimpleStringProperty insuranceTypeProperty() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType.set(insuranceType);
    }
}
