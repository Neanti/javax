package Server;
import java.rmi.*;

public interface ReceiveMessageInterface extends Remote{
    void receiveMessage(String x) throws RemoteException;
    String getMessage() throws RemoteException;
    int Allu(int x) throws RemoteException;
}