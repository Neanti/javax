package application;

import Server.ReceiveMessageInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import javax.swing.text.View;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Allumette {

    @FXML
    public Button b9;
    @FXML
    public Button b1;
    @FXML
    public Button b2;
    @FXML
    public Button b3;
    @FXML
    public Button b4;
    @FXML
    public Button b5;
    @FXML
    public Button b6;
    @FXML
    public Button b7;
    @FXML
    public Button b8;

    private Applic applic;
    private int nb = 0;
    private int joue = 0;
    public ReceiveMessageInterface rmi;

    public void init()
    {

        b1.setPrefHeight(200);
        b2.setPrefHeight(200);
        b3.setPrefHeight(200);
        b4.setPrefHeight(200);
        b5.setPrefHeight(200);
        b6.setPrefHeight(200);
        b7.setPrefHeight(200);
        b8.setPrefHeight(200);
        b9.setPrefHeight(200);


        ReceiveMessageInterface rmiServer;
        Registry registry;
        String serverAddress="100.64.48.146";
        String serverPort="3232";
        String text="BLlllo";
        System.out.println
                ("sending " + text + " to " +serverAddress + ":" + serverPort);
        try{
            registry= LocateRegistry.getRegistry
                    (serverAddress,(new Integer(serverPort)).intValue());
            rmiServer=(ReceiveMessageInterface)(registry.lookup("rmiServer"));
            this.rmi = rmiServer;
            // call the remote method

        }
        catch(RemoteException e){
            e.printStackTrace();
        }
        catch(NotBoundException e){
            System.err.println(e);
        }
    }

    public void setApplic(Applic applic) {
        this.applic = applic;
    }

    public void ClickB1()
    {
        b1.setVisible(false);
        joue++;
        nb++;
        check_win();
    }

    public void ClickB2()
    {
        b2.setVisible(false);
        joue++;
        nb++;
    }

    public void ClickB3()
    {
        b3.setVisible(false);
        joue++;
        nb++;
    }

    public void ClickB4()
    {
        b4.setVisible(false);
        joue++;
        nb++;
    }

    public void ClickB5()
    {
        b5.setVisible(false);
        joue++;
        nb++;
    }

    public void ClickB6()
    {
        b6.setVisible(false);
        joue++;
        nb++;
    }

    public void ClickB7()
    {
        b7.setVisible(false);
        joue++;
        nb++;    }

    public void ClickB8()
    {
        b8.setVisible(false);
        joue++;
        nb++;
    }

    public void ClickB9()
    {
        b9.setVisible(false);
        joue++;
        nb++;
    }

    public void Play() {
        try {
            play();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void play() throws RemoteException {
       int i = this.rmi.Allu(9-nb) %2 + 1;
        System.out.println(i + "to hide " + nb);
        if(nb ==1 )
        {
            b8.setVisible(false);
            if(i==2)
                b7.setVisible(false);
        }
        if(nb ==2 )
        {
            b7.setVisible(false);
            if(i==2)
                b6.setVisible(false);
        }
        if(nb ==3 )
        {
            b6.setVisible(false);
            if(i==2)
                b5.setVisible(false);
        }
        if(nb ==4 )
        {
            b5.setVisible(false);
            if(i==2)
                b4.setVisible(false);
        }
        if(nb ==5 )
        {
            b4.setVisible(false);
            if(i==2)
                b3.setVisible(false);
        }
        if(nb ==6 )
        {
            b3.setVisible(false);
            if(i==2)
                b2.setVisible(false);
        }
        if(nb ==7 )
        {
            b2.setVisible(false);
            if(i==2) {
                b1.setVisible(false);
                check_win();
            }
        }
        if(nb ==8 )
        {
            b1.setVisible(false);
            check_win();
        }
        if(nb!=0)
        nb = nb + i;

    }

    public void check_win()
    {
        nb = 0;
        joue = 0;
        b1.setVisible(true);
        b2.setVisible(true);
        b3.setVisible(true);
        b4.setVisible(true);
        b5.setVisible(true);
        b6.setVisible(true);
        b7.setVisible(true);
        b8.setVisible(true);
        b9.setVisible(true);

        if(joue%2 == 1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information de jeu");
            alert.setHeaderText(null);
            alert.setContentText("Victoire");
            alert.showAndWait();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information de jeu");
            alert.setHeaderText(null);
            alert.setContentText("Défaite");
            alert.showAndWait();
        }
    }



    public void refresh() {

    }

}
