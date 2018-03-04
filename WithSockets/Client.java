import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String args[]) throws UnknownHostException, IOException {
		Socket server = new Socket("127.0.0.1",4000);
		DataInputStream input = new DataInputStream(server.getInputStream());
		PrintWriter out = new PrintWriter(server.getOutputStream(), true);
		Scanner localInput = new Scanner(System.in);
		
		String line = ""; //localInput.nextLine();
		while(!line.equals("quit")) {
			line = localInput.nextLine();
			//System.out.println(System.nanoTime());
			out.println(line); //Sends data to the server
			System.out.print(input.readUTF()); // read data from the server
			//System.out.println(System.nanoTime());
			
		}
	}
}
