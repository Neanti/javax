package Server;
import java.rmi.*;

public interface ReceiveMessageInterface extends Remote{
    int Allu(int x) throws RemoteException;
    int[][] Morp(int[][] jeu) throws RemoteException;
    int[][] Morp2(int[][] jeu) throws RemoteException;
}