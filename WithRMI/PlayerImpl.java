import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PlayerImpl extends UnicastRemoteObject implements Player {
	int xPos;
	int yPos;
	int direction;
	List<String> items;
	
	private String ip;
	
	public PlayerImpl(int _xPos, int _yPos) throws UnknownHostException, RemoteException, AlreadyBoundException {
		super();
		
		ip =  "127.0.0.1";//InetAddress.getLocalHost().getHostAddress();
		//LocateRegistry.createRegistry(1099);
		Registry reg = LocateRegistry.getRegistry(ip, 1099);
		reg.bind("player", this);
		
		this.xPos = _xPos;
		this.yPos = _yPos;
		this.direction = 0;
		items = new ArrayList<String>();
	}
	
	private void move(int steps) {
		this.xPos += (int)Math.sin(Math.PI*direction/2) * steps;
		this.yPos += (int)Math.cos(Math.PI*direction/2) * steps;
	}

	private void turn(int rotation) {
		direction = Math.abs(direction + rotation % 4);
	}
	
	private void pickUp(String item) {
		items.add(item);
	}
	
	private void putDown(String item) {
		if(items.contains(item))
			items.remove(item);
	}
	
	private String view() {
		StringBuilder sb = new StringBuilder();
		sb.append("Position: " + xPos + ", " + yPos + "\n");
		sb.append("Items: " + items.toString());
		
		return sb.toString();
	}

	@Override
	public String action(String input) throws RemoteException {
		// TODO Auto-generated method stub
		//Read input
		String data = "";
		String[] parts = input.split(" ");
		
		//Moving
		if(parts[0].equals("move")) {
			if(parts.length > 1)
				move(Integer.parseInt(parts[1]));
			else
				move(1);
		}
		//Turns
		else if(parts[0].equals("turn")) {
			if(parts.length > 1) {
				if(parts[1].equals("left"))
					turn(1);
				else if(parts[1].equals("right"))
					turn(-1);
				else if(parts[1].equals("around"))
					turn(2);
			}
		}
		//Pick up
		else if(parts[0].equals("pickup")) {
			for(int i = 1; i < parts.length; i++)
				pickUp(parts[i]);
		}
		//Put down
		else if(parts[0].equals("putdown")) {
			for(int i = 1; i < parts.length; i++)
				putDown(parts[i]);
		}
		//View player
		else if(parts[0].equals("view")) {
			data = view();
		} 
		else if(parts[0].equals("quit")) {
			data = "Exiting game";
		}
		else {
			data = "invalid command";
		}
		
		System.out.println(System.nanoTime());
		
		return data + "\n";
	}
}
