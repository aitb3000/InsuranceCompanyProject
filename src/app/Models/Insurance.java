package app.Models;

import javafx.beans.property.SimpleStringProperty;

public class Insurance
{
    private SimpleStringProperty insuranceId = new SimpleStringProperty(this, "insuranceId");
    private SimpleStringProperty insuranceName = new SimpleStringProperty(this, "insuranceName");
    private SimpleStringProperty insuranceStatus = new SimpleStringProperty(this, "insuranceName");




    public String getInsuranceName() {
        return insuranceName.get();
    }

    public SimpleStringProperty insuranceNameProperty() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName.set(insuranceName);
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

    public String getInsuranceStatus() {
        return insuranceStatus.get();
    }

    public SimpleStringProperty insuranceStatusProperty() {
        return insuranceStatus;
    }

    public void setInsuranceStatus(String insuranceStatus) {
        this.insuranceStatus.set(insuranceStatus);
    }
}
