package app.Models;

import javafx.beans.property.SimpleStringProperty;

public class Claim {

    private SimpleStringProperty ClaimId = new SimpleStringProperty(this, "claimId");
    private SimpleStringProperty ClaimStatus = new SimpleStringProperty(this, "claimName");


    private SimpleStringProperty ClientId = new SimpleStringProperty(this, "id");
    private SimpleStringProperty ClientFirstName = new SimpleStringProperty(this, "firstName");
    private SimpleStringProperty ClientLastName = new SimpleStringProperty(this, "lastName");

    private SimpleStringProperty InsuranceId = new SimpleStringProperty(this, "insuranceId");
    private SimpleStringProperty InsuranceName = new SimpleStringProperty(this, "insuranceName");
    private SimpleStringProperty InsuranceStatus = new SimpleStringProperty(this, "insuranceName");

    private SimpleStringProperty CustomerServiceId = new SimpleStringProperty(this, "id");
    private SimpleStringProperty CustomerServiceFirstName = new SimpleStringProperty(this, "firstName");
    private SimpleStringProperty CustomerServiceLastName = new SimpleStringProperty(this, "lastName");



    public String getClaimStatus() { return ClaimStatus.get(); }

    public SimpleStringProperty claimStatusProperty() { return ClaimStatus;  }

    public void setClaimStatus(String claimStatus) { this.ClaimStatus.set(claimStatus);  }

    public String getClaimId() {
        return ClaimId.get();
    }

    public SimpleStringProperty claimIdProperty() {
        return ClaimId;
    }

    public void setClaimId(String claimId) {
        this.ClaimId.set(claimId);
    }

    public String getClientId() {
        return ClientId.get();
    }

    public SimpleStringProperty clientIdProperty() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        this.ClientId.set(clientId);
    }

    public String getClientFirstName() {
        return ClientFirstName.get();
    }

    public SimpleStringProperty clientFirstNameProperty() {
        return ClientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.ClientFirstName.set(clientFirstName);
    }

    public String getClientLastName() {
        return ClientLastName.get();
    }

    public SimpleStringProperty clientLastNameProperty() {
        return ClientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.ClientLastName.set(clientLastName);
    }

    public String getInsuranceId() {
        return InsuranceId.get();
    }

    public SimpleStringProperty insuranceIdProperty() {
        return InsuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.InsuranceId.set(insuranceId);
    }

    public String getInsuranceName() {
        return InsuranceName.get();
    }

    public SimpleStringProperty insuranceNameProperty() {
        return InsuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.InsuranceName.set(insuranceName);
    }

    public String getInsuranceStatus() {
        return InsuranceStatus.get();
    }

    public SimpleStringProperty insuranceStatusProperty() {
        return InsuranceStatus;
    }

    public void setInsuranceStatus(String insuranceStatus) {
        this.InsuranceStatus.set(insuranceStatus);
    }

    public String getCustomerServiceId() {
        return CustomerServiceId.get();
    }

    public SimpleStringProperty customerServiceIdProperty() {
        return CustomerServiceId;
    }

    public void setCustomerServiceId(String customerServiceId) {
        this.CustomerServiceId.set(customerServiceId);
    }

    public String getCustomerServiceFirstName() {
        return CustomerServiceFirstName.get();
    }

    public SimpleStringProperty customerServiceFirstNameProperty() {
        return CustomerServiceFirstName;
    }

    public void setCustomerServiceFirstName(String customerServiceFirstName) {
        this.CustomerServiceFirstName.set(customerServiceFirstName);
    }

    public String getCustomerServiceLastName() {
        return CustomerServiceLastName.get();
    }

    public SimpleStringProperty customerServiceLastNameProperty() {
        return CustomerServiceLastName;
    }

    public void setCustomerServiceLastName(String customerServiceLastName) {
        this.CustomerServiceLastName.set(customerServiceLastName);
    }
}
