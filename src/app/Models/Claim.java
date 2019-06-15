package app.Models;

import javafx.beans.property.SimpleStringProperty;

public class Claim {

    private SimpleStringProperty ClaimType = new SimpleStringProperty(this, "claimType");
    private SimpleStringProperty ClaimName = new SimpleStringProperty(this, "claimName");
    private SimpleStringProperty ClaimStatus = new SimpleStringProperty(this, "claimName");
    private SimpleStringProperty CliamId = new SimpleStringProperty(this, "cliamId");

    // User Client Id
    private SimpleStringProperty ucId = new SimpleStringProperty(this, "ucId");
    // User CustomerService Id
    private SimpleStringProperty ucsId = new SimpleStringProperty(this, "usId");






    public String getClaimType() { return ClaimType.get(); }

    public SimpleStringProperty claimTypeProperty() { return ClaimType;}

    public void setClaimType(String claimType) { this.ClaimType.set(claimType); }

    public String getClaimName() { return ClaimName.get(); }

    public SimpleStringProperty claimNameProperty() { return ClaimName;}

    public void setClaimName(String claimName) { this.ClaimName.set(claimName);}

    public String getClaimStatus() { return ClaimStatus.get(); }

    public SimpleStringProperty claimStatusProperty() { return ClaimStatus;  }

    public void setClaimStatus(String claimStatus) { this.ClaimStatus.set(claimStatus);  }

    public String getUcId() {  return ucId.get();   }

    public SimpleStringProperty ucIdProperty() {        return ucId;    }

    public void setUcId(String ucId) {        this.ucId.set(ucId);    }

    public String getUcsId() {        return ucsId.get();    }

    public SimpleStringProperty ucsIdProperty() {        return ucsId;    }

    public void setUcsId(String ucsId) {        this.ucsId.set(ucsId);    }

    public String getCliamId() {
        return CliamId.get();
    }

    public SimpleStringProperty cliamIdProperty() {
        return CliamId;
    }

    public void setCliamId(String cliamId) {
        this.CliamId.set(cliamId);
    }
}
