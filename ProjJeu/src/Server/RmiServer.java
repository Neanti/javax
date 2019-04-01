package Server;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.net.*;
import java.util.Random;

public class RmiServer extends
        java.rmi.server.UnicastRemoteObject implements ReceiveMessageInterface{
    String address;
    Registry registry;
    public boolean toread = false;
    public String msg;

    @Override
    public void receiveMessage(String x) throws RemoteException{
        this.msg = x;
        this.toread = true;
    }

    @Override
    public String getMessage() throws RemoteException {
        while (true)
        {
            if(this.toread)
            {
                this.toread = false;
                return this.msg;
            }
            System.out.println("loop\n");
        }
    }

    @Override
    public int Allu(int x) throws RemoteException {
        Random random = new Random();
        if(x == 1)
        {
            return 1;
        }
        else
            return random.nextInt(5);
    }

    public RmiServer() throws RemoteException{
        try{
            address = (InetAddress.getLocalHost()).toString();
        }
        catch(Exception e){
            System.out.println("can't get inet address.");
        }
        int port=3232;
        System.out.println("this address=" + address +  ",port=" + port);
        try{
            registry = LocateRegistry.createRegistry(port);
            registry.rebind("rmiServer", this);
        }
        catch(RemoteException e){
            System.out.println("remote exception"+ e);
        }
    }
    static public void main(String args[]){
        try{
            RmiServer server = new RmiServer();
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }
}