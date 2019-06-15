package app.Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Insurance
{


    public static String getInsuranceStatus(byte id)
    {
        if (id == 1)        return "Pending";
        else if (id == 2)   return "Approved";
        else if (id == 3)   return "Disapproved";
        return "None";
    }





    private SimpleStringProperty insuranceId = new SimpleStringProperty(this, "insuranceId");
    private SimpleStringProperty insuranceName = new SimpleStringProperty(this, "insuranceName");
    private SimpleStringProperty insuranceStatus = new SimpleStringProperty(this, "insuranceName");
    private SimpleStringProperty insuranceType = new SimpleStringProperty(this, "insuranceType");




    public String getInsuranceName() {  return insuranceName.get();  }

    public SimpleStringProperty insuranceNameProperty() { return insuranceName; }

    public void setInsuranceName(String insuranceName) { this.insuranceName.set(insuranceName);   }

    public String getInsuranceId() {return insuranceId.get();   }

    public SimpleStringProperty insuranceIdProperty() { return insuranceId; }

    public void setInsuranceId(String insuranceId) { this.insuranceId.set(insuranceId);}

    public String getInsuranceStatus() {  return insuranceStatus.get(); }

    public SimpleStringProperty insuranceStatusProperty() {return insuranceStatus; }

    public void setInsuranceStatus(String insuranceStatus) { this.insuranceStatus.set(insuranceStatus);  }

    public String getInsuranceType() { return insuranceType.get();}

    public SimpleStringProperty insuranceTypeProperty() {return insuranceType; }

    public void setInsuranceType(String insuranceType) {this.insuranceType.set(insuranceType); }
}
