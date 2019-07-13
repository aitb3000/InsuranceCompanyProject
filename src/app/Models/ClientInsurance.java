package app.Models;

import javafx.beans.property.SimpleStringProperty;

public class ClientInsurance
{

    private SimpleStringProperty insuranceId = new SimpleStringProperty(this, "insuranceId");
    private SimpleStringProperty insuranceType = new SimpleStringProperty(this, "insuranceName");
    private SimpleStringProperty insuranceName = new SimpleStringProperty(this, "insuranceName");
    private SimpleStringProperty insuranceStatus = new SimpleStringProperty(this, "insuranceName");
    // User Salesman Id
    private SimpleStringProperty salesmanId = new SimpleStringProperty(this, "salesmanId");
    // User Client Id
    private SimpleStringProperty clientId = new SimpleStringProperty(this, "ucid");
    private SimpleStringProperty clientFirstName = new SimpleStringProperty(this, "ucid");
    private SimpleStringProperty clientLastName = new SimpleStringProperty(this, "ucid");

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

    public String getInsuranceType() { return insuranceType.get(); }

    public SimpleStringProperty insuranceTypeProperty() { return insuranceType;  }

    public void setInsuranceType(String insuranceType) { this.insuranceType.set(insuranceType); }

    public String getSalesmanId() {        return salesmanId.get();    }

    public SimpleStringProperty salesmanIdProperty() {       return salesmanId;    }

    public void setSalesmanId(String salesmanId) {        this.salesmanId.set(salesmanId);    }

    public String getInsuranceId() {        return insuranceId.get();    }

    public SimpleStringProperty insuranceIdProperty() {        return insuranceId;    }

    public void setInsuranceId(String insuranceId) {        this.insuranceId.set(insuranceId);    }

    public String getClientId() {        return clientId.get();    }

    public SimpleStringProperty clientIdProperty() {        return clientId;    }

    public void setClientId(String clientId) {        this.clientId.set(clientId);    }

    public String getClientFirstName() {        return clientFirstName.get();    }

    public SimpleStringProperty clientFirstNameProperty() {        return clientFirstName;    }

    public void setClientFirstName(String clientFirstName) {        this.clientFirstName.set(clientFirstName);    }

    public String getClientLastName() {        return clientLastName.get();    }

    public SimpleStringProperty clientLastNameProperty() {        return clientLastName;    }

    public void setClientLastName(String clientLastName) {        this.clientLastName.set(clientLastName);    }

}
