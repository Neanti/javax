package application;

public class Menu {

    private Applic applic;


    public void setApplic(Applic applic)
    {
        this.applic = applic;
    }

    public void showMorp()
    {
        applic.showMorpion();
    }


}
