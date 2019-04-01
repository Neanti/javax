package Server;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.net.*;
import java.util.Random;

public class RmiServer extends
        java.rmi.server.UnicastRemoteObject implements ReceiveMessageInterface {
    String address;
    Registry registry;
    private int[][] jeu;
    public int tour;

    @Override
    public int Allu(int x) throws RemoteException {
        Random random = new Random();
        if (x == 1) {
            return 1;
        } else
            return random.nextInt(5);
    }

    @Override
    public int[][] Morp(int[][] jeu) throws RemoteException {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (jeu[i][j] == 0) {
                    jeu[i][j] = 2;
                    return jeu;
                }
            }
        }
        return jeu;
    }

    public int[][] arrange(int[][] j) {
        for (int i = 0; i < 3; i++) {
            for (int n = 0; n < 3; n++) {
                if (j[i][n] == 1)
                    j[i][n] = 2;
                else if (j[i][n] == 2)
                    j[i][n] = 1;
            }
        }
        return j;
    }

    @Override
    public int[][] Morp2(int[][] jeu) throws RemoteException {
        if (tour == 0) {
            this.jeu = jeu;
            tour = 1;
            System.out.println("CALL tour=0");
            while (true) {
                System.out.println("TOUR0 = " + this.tour);
                if (this.tour == 0) {
                    return arrange(this.jeu);
                }
            }
        } else if (tour == 1) {
            System.out.println("CALL tour=1");
            this.jeu = jeu;
            tour = 0;
            while (true) {
                System.out.println("TOUR1 = " + this.tour);
                if (this.tour == 1) {
                    return this.jeu;
                }
            }
        }
        return null;
    }


    public RmiServer() throws RemoteException {
        tour = 0;
        try {
            address = (InetAddress.getLocalHost()).toString();
        } catch (Exception e) {
            System.out.println("Adresse pas dispo");
        }
        int port = 3232;
        System.out.println("Adresse=" + address + ",port=" + port);
        try {
            registry = LocateRegistry.createRegistry(port);
            registry.rebind("rmiServer", this);
        } catch (RemoteException e) {
            System.out.println("Erreur" + e);
        }
    }

    static public void main(String args[]) {
        try {

            RmiServer server = new RmiServer();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}