package Client;
import Server.ReceiveMessageInterface;

import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class RmiClient{
    static public void main(String args[]) throws InterruptedException {
        ReceiveMessageInterface rmiServer;
        Registry registry;
        String serverAddress=args[0];
        String serverPort=args[1];
        String text=args[2];
        System.out.println
                ("sending " + text + " to " +serverAddress + ":" + serverPort);
        try{
            registry=LocateRegistry.getRegistry
                    (serverAddress,(new Integer(serverPort)).intValue());
            rmiServer=(ReceiveMessageInterface)(registry.lookup("rmiServer"));
            // call the remote method
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println(rmiServer.getMessage());
                System.out.println("loop\n");
                TimeUnit.SECONDS.sleep(20);
            }
        }
        catch(RemoteException e){
            e.printStackTrace();
        }
        catch(NotBoundException e){
            System.err.println(e);
        }
    }
}