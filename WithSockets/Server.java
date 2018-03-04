import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class Server {

	public static void main(String[] args) throws IOException {
		Player player1 = new Player(0, 0);
		ServerSocket serverSocket;
		Socket clientSocket;
		BufferedReader clientIn;
		DataOutputStream clientOut;
		
		//Start the server
		serverSocket = new ServerSocket(4000);
		System.out.println("Server started waiting on client");
		
		//Wait for the client to connect
		clientSocket = serverSocket.accept();
		System.out.println("Client connected!");
		
		//This allows me to read data from the client
		clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		clientOut = new DataOutputStream(clientSocket.getOutputStream());
		
		
		String readIn = "";
		String data = "";
		while(!readIn.equals("quit")) {
			
			//Read input
			data = "";
			readIn = clientIn.readLine();
			String[] parts = readIn.split(" ");
			
			//Moving
			if(parts[0].equals("move")) {
				if(parts.length > 1)
					player1.move(Integer.parseInt(parts[1]));
				else
					player1.move(1);
			}
			//Turns
			else if(parts[0].equals("turn")) {
				if(parts.length > 1) {
					if(parts[1].equals("left"))
						player1.turn(1);
					else if(parts[1].equals("right"))
						player1.turn(-1);
					else if(parts[1].equals("around"))
						player1.turn(2);
				}
			}
			//Pick up
			else if(parts[0].equals("pickup")) {
				for(int i = 1; i < parts.length; i++)
					player1.pickUp(parts[i]);
			}
			//Put down
			else if(parts[0].equals("putdown")) {
				for(int i = 1; i < parts.length; i++)
					player1.putDown(parts[i]);
			}
			//View player
			else if(parts[0].equals("view")) {
				data = player1.view();
			} 
			else if(parts[0].equals("quit")) {
				data = "Exiting game";
			}
			else {
				data = "invalid command";
			}
			
			System.out.println(System.nanoTime());
			clientOut.writeUTF(data + "\n");
			clientOut.flush();
		}
		
		System.out.println("Server shutting down");
		clientIn.close();
		clientSocket.close();
		serverSocket.close();
	}

}
