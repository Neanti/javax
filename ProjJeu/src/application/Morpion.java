package application;

import Server.ReceiveMessageInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Morpion {
    @FXML
    public GridPane Grille;
    @FXML
    public Button b00;
    @FXML
    public Button b01;
    @FXML
    public Button b02;
    @FXML
    public Button b10;
    @FXML
    public Button b11;
    @FXML
    public Button b12;
    @FXML
    public Button b20;
    @FXML
    public Button b21;
    @FXML
    public Button b22;
    public int[][] jeu;


    private Applic applic;
    private ReceiveMessageInterface rmi;
    private int joue;

    public void setApplic(Applic applic) {
        this.applic = applic;
    }
    public void initMorp()
    {

        ReceiveMessageInterface rmiServer;
        Registry registry;
        String serverAddress="100.64.48.146";
        String serverPort="3232";
        System.out.println
                (serverAddress + ":" + serverPort);
        try{
            registry= LocateRegistry.getRegistry
                    (serverAddress,(new Integer(serverPort)).intValue());
            rmiServer=(ReceiveMessageInterface)(registry.lookup("rmiServer"));
            this.rmi = rmiServer;

        }
        catch(RemoteException e){
            e.printStackTrace();
        }
        catch(NotBoundException e){
            System.err.println(e);
        }

        Grille.setStyle("-fx-grid-lines-visible: true");
        b00.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        b01.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        b02.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        b10.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        b11.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        b12.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        b20.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        b21.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        b22.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.jeu = new  int[3][3];
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                jeu[i][j] = 0;
            }
        }
    }

    public void ClickB00()
    {
        play(0,0);
    }

    public void ClickB01()
    {
        play(0,1);
    }

    public void ClickB02()
    {
        play(0,2);
    }

    public void ClickB10()
    {
        play(1,0);
    }

    public void ClickB11()
    {
        play(1,1);
    }

    public void ClickB12()
    {
        play(1,2);
    }

    public void ClickB20()
    {
        play(2,0);
    }

    public void ClickB21()
    {
        play(2,1);
    }

    public void ClickB22()
    {
        play(2,2);
    }

    public void play(int x, int y)
    {
        joue++;
        if(jeu[x][y] != 0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information de jeu");
            alert.setHeaderText(null);
            alert.setContentText("Vous ne pouvez pas jouer ici !");
            alert.showAndWait();
        }
            jeu[x][y] = 1;

        refresh();
        if(check_win(1) || joue == 9)
            System.out.println("fin");
        try {
            refresh();
            jeu = rmi.Morp2(jeu);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        refresh();
        check_win(2);
        joue++;
    }

    public boolean check_win(int j)
    {
        System.out.println("joue="+joue);
        int i = 0;
        i = check_ligne(0) + check_ligne(1) + check_ligne(2) + check_col(0) + check_col(1) + check_col(2) + check_diag();
        if(i != 0)
        {
            if(j==1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information de jeu");
                alert.setHeaderText(null);
                alert.setContentText("Victoire de X");
                alert.showAndWait();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information de jeu");
                alert.setHeaderText(null);
                alert.setContentText("Victoire de O");
                alert.showAndWait();
            }
            for(int n = 0;n < 3;n++)
            {
                for(int m = 0;m < 3;m++)
                {
                    jeu[n][m]  = 0;
                }
            }
            joue = 0;
            refresh();
            return true;
        }
        else if(joue == 9)
        {
            for(int n = 0;n < 3;n++)
            {
                for(int m = 0;m < 3;m++)
                {
                    jeu[n][m] = 0;
                }
            }
            joue = 0;
            refresh();
        }
        return false;
    }

    public int check_ligne(int l)
    {
        if(jeu[l][0] == jeu[l][1] && jeu[l][1] == jeu[l][2] && jeu[l][1] != 0)
            return 1;
        return 0;
    }

    public int check_col(int c)
    {
        if(jeu[0][c] == jeu[1][c] && jeu[1][c] == jeu[2][c] && jeu[1][c] != 0)
            return 1;
        return 0;
    }

    public int check_diag()
    {
        if(jeu[0][0] == jeu[1][1] && jeu[1][1] == jeu[2][2] && jeu[1][1] != 0)
            return 1;
        else if(jeu[0][2] == jeu[1][1] && jeu[1][1] == jeu[2][0] && jeu[1][1] != 0)
            return 1;
        return 0;
    }

    public void refresh()
    {
        if(jeu[0][0] == 1)
            b00.setText("X");
        else if(jeu[0][0] == 2)
            b00.setText("O");
        else
            b00.setText("");
        if(jeu[0][1] == 1)
            b01.setText("X");
        else if(jeu[0][1] == 2)
            b01.setText("O");
        else
            b01.setText("");
        if(jeu[0][2] == 1)
            b02.setText("X");
        else if(jeu[0][2] == 2)
            b02.setText("O");
        else
            b02.setText("");
        if(jeu[1][0] == 1)
            b10.setText("X");
        else if(jeu[1][0] == 2)
            b10.setText("O");
        else
            b10.setText("");
        if(jeu[1][1] == 1)
            b11.setText("X");
        else if(jeu[1][1] == 2)
            b11.setText("O");
        else
            b11.setText("");
        if(jeu[1][2] == 1)
            b12.setText("X");
        else if(jeu[1][2] == 2)
            b12.setText("O");
        else
            b12.setText("");
        if(jeu[2][0] == 1)
            b20.setText("X");
        else if(jeu[2][0] == 2)
            b20.setText("O");
        else
            b20.setText("");
        if(jeu[2][1] == 1)
            b21.setText("X");
        else if(jeu[2][1] == 2)
            b21.setText("O");
        else
            b21.setText("");
        if(jeu[2][2] == 1)
            b22.setText("X");
        else if(jeu[2][2] == 2)
            b22.setText("O");
        else
            b22.setText("");
    }

}
