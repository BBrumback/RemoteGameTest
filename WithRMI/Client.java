import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

	public static void main(String args[]) throws RemoteException {
		boolean isPlayerFound = false;
		Scanner scan = new Scanner(System.in);
		Registry reg;
		Player p1 = null;
		
		System.out.println("Please input the node number");
		String input = scan.nextLine();
		while(!input.equals("quit") && !isPlayerFound) {
			try {                               //CHANGE THIS
				reg = LocateRegistry.getRegistry("127.0.0.1" + input, 1099);
				p1 = (Player)reg.lookup("player");
				isPlayerFound = true;
			} catch (RemoteException | NotBoundException e) {
				//Let the user try to input another node number
				System.out.println("Please input the node number, or type \'quit\' to quit");
				input = scan.nextLine();
			}
		}
		
		//basically do the same thing as above but with player commands
		System.out.println("Please input command");
		input = scan.nextLine();
		while(!input.equals("quit") && isPlayerFound) {
			System.out.println(System.nanoTime());
			System.out.println((p1.action(input)));
			System.out.println(System.nanoTime());
			System.out.println("Please input command");
			input = scan.nextLine();
		}
		
		scan.close();
	}
}
