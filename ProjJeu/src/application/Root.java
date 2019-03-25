package application;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class Root {
    public Applic applic;

    public Applic getApplic() {
        return applic;
    }

    public void setApplic(Applic applic) {
        this.applic = applic;
    }


    @FXML
    private void AffMorpion(){
        applic.showMorpion();
    }

    @FXML
    private void AffPr(){
        applic.showProd();
    }

}
