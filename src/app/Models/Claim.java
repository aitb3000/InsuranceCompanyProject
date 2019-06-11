package app.Models;

import javafx.beans.property.SimpleStringProperty;

public class Claim {

    private SimpleStringProperty ClaimType = new SimpleStringProperty(this, "claimType");
    private SimpleStringProperty ClaimName = new SimpleStringProperty(this, "claimName");
    private SimpleStringProperty ClaimStatus = new SimpleStringProperty(this, "claimName");


    public String getClaimType() {
        return ClaimType.get();
    }

    public SimpleStringProperty claimTypeProperty() {
        return ClaimType;
    }

    public void setClaimType(String claimType) {
        this.ClaimType.set(claimType);
    }

    public String getClaimName() {
        return ClaimName.get();
    }

    public SimpleStringProperty claimNameProperty() {
        return ClaimName;
    }

    public void setClaimName(String claimName) {
        this.ClaimName.set(claimName);
    }

    public String getClaimStatus() {
        return ClaimStatus.get();
    }

    public SimpleStringProperty claimStatusProperty() {
        return ClaimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.ClaimStatus.set(claimStatus);
    }
}
