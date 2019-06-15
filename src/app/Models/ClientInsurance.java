package app.Models;

import javafx.beans.property.SimpleStringProperty;

public class ClientInsurance
{

    public Insurance ClineInsuranceInformation;
    public Client ClientInformation;

    private SimpleStringProperty insuranceId = new SimpleStringProperty(this, "insuranceId");
    private SimpleStringProperty insuranceType = new SimpleStringProperty(this, "insuranceName");
    private SimpleStringProperty insuranceName = new SimpleStringProperty(this, "insuranceName");
    private SimpleStringProperty insuranceStatus = new SimpleStringProperty(this, "insuranceName");


    // User Salesman Id
    private SimpleStringProperty usId = new SimpleStringProperty(this, "usId");
    // User Client Id
    private SimpleStringProperty ucId = new SimpleStringProperty(this, "ucid");
    private SimpleStringProperty ucFname = new SimpleStringProperty(this, "ucid");
    private SimpleStringProperty ucLname = new SimpleStringProperty(this, "ucid");
    private SimpleStringProperty ucStatus = new SimpleStringProperty(this, "ucStatus");

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

    public String getUsId() {        return usId.get();    }

    public SimpleStringProperty usIdProperty() {       return usId;    }

    public void setUsId(String usId) {        this.usId.set(usId);    }

    public String getInsuranceId() {        return insuranceId.get();    }

    public SimpleStringProperty insuranceIdProperty() {        return insuranceId;    }

    public void setInsuranceId(String insuranceId) {        this.insuranceId.set(insuranceId);    }

    public String getUcId() {        return ucId.get();    }

    public SimpleStringProperty ucIdProperty() {        return ucId;    }

    public void setUcId(String ucId) {        this.ucId.set(ucId);    }

    public String getUcFname() {        return ucFname.get();    }

    public SimpleStringProperty ucFnameProperty() {        return ucFname;    }

    public void setUcFname(String ucFname) {        this.ucFname.set(ucFname);    }

    public String getUcLname() {        return ucLname.get();    }

    public SimpleStringProperty ucLnameProperty() {        return ucLname;    }

    public void setUcLname(String ucLname) {        this.ucLname.set(ucLname);    }

    public String getUcStatus() {
        return ucStatus.get();
    }

    public SimpleStringProperty ucStatusProperty() {
        return ucStatus;
    }

    public void setUcStatus(String ucStatus) {
        this.ucStatus.set(ucStatus);
    }
}
