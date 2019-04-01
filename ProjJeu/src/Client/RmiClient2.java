package Client;
import Server.ReceiveMessageInterface;

import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;
import java.util.Scanner;

public class RmiClient2{
    static public void main(String args[]){
        ReceiveMessageInterface rmiServer;
        Registry registry;
        String serverAddress="100.64.48.146";
        String serverPort="3232";
        String text="OLLLB";
        System.out.println
                ("sending " + text + " to " +serverAddress + ":" + serverPort);
        try{
            registry=LocateRegistry.getRegistry
                    (serverAddress,(new Integer(serverPort)).intValue());
            rmiServer=(ReceiveMessageInterface)(registry.lookup("rmiServer"));
            // call the remote method
            Scanner sc = new Scanner(System.in);
            while (true) {
                text = sc.nextLine();
                rmiServer.receiveMessage(text);
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