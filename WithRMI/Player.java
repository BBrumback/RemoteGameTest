import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Player extends Remote{
	
	public String action(String input) throws RemoteException;
}
