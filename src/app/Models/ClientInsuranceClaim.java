package app.Models;

import javafx.beans.property.SimpleStringProperty;

public class ClientInsuranceClaim {

    public Insurance ClientInsuranceInformation;
    public Client ClientInformation;
    public Salesman SalesmanInformation;


    private SimpleStringProperty clientId = new SimpleStringProperty(this, "id");
    private SimpleStringProperty clientFirstName = new SimpleStringProperty(this, "firstName");
    private SimpleStringProperty clientLastName = new SimpleStringProperty(this, "lastName");
    private SimpleStringProperty clientStatus = new SimpleStringProperty(this, "status");

    private SimpleStringProperty claimId = new SimpleStringProperty(this, "claimId");
    private SimpleStringProperty claimName = new SimpleStringProperty(this, "claimName");
    private SimpleStringProperty ClaimStatus = new SimpleStringProperty(this, "claimName");

    private SimpleStringProperty insuranceId = new SimpleStringProperty(this, "insuranceId");
    private SimpleStringProperty insuranceName = new SimpleStringProperty(this, "insuranceName");
    private SimpleStringProperty insuranceStatus = new SimpleStringProperty(this, "claimName");


    public String getClientId() {
        return clientId.get();
    }

    public SimpleStringProperty clientIdProperty() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId.set(clientId);
    }

    public String getClientFirstName() {
        return clientFirstName.get();
    }

    public SimpleStringProperty clientFirstNameProperty() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName.set(clientFirstName);
    }

    public String getClientLastName() {
        return clientLastName.get();
    }

    public SimpleStringProperty clientLastNameProperty() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName.set(clientLastName);
    }

    public String getClientStatus() {
        return clientStatus.get();
    }

    public SimpleStringProperty clientStatusProperty() {
        return clientStatus;
    }

    public void setClientStatus(String clientStatus) {
        this.clientStatus.set(clientStatus);
    }

    public String getClaimId() {
        return claimId.get();
    }

    public SimpleStringProperty claimIdProperty() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId.set(claimId);
    }

    public String getClaimName() {
        return claimName.get();
    }

    public SimpleStringProperty claimNameProperty() {
        return claimName;
    }

    public void setClaimName(String claimName) {
        this.claimName.set(claimName);
    }

    public String getInsuranceId() {
        return insuranceId.get();
    }

    public SimpleStringProperty insuranceIdProperty() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId.set(insuranceId);
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

    public String getClaimStatus() { return ClaimStatus.get(); }

    public SimpleStringProperty claimStatusProperty() {
        return ClaimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.ClaimStatus.set(claimStatus);
    }

    public String getInsuranceStatus() { return insuranceStatus.get();  }

    public SimpleStringProperty insuranceStatusProperty() { return insuranceStatus;  }

    public void setInsuranceStatus(String insuranceStatus) {  this.insuranceStatus.set(insuranceStatus); }
}
