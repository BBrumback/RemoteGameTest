import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Server{
	public static void main(String args[]) throws RemoteException, UnknownHostException, AlreadyBoundException {
		Player player1 = new PlayerImpl(0,0);
		
		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine(); 
		while(!line.equals("quit")) {
			System.out.println("type quit to exit the server");
		}
		scan.close();
	}
}
