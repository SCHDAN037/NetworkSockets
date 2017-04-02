import java.util.Scanner;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class ClientApp{

	public static String username, ip;

	public static void main(String[] args) {
		
		try{

			System.out.println("Starting client...");
			
			Scanner scanner = new Scanner(System.in);

			System.out.println("Please enter your username: ");						
			username = scanner.nextLine();
			
			System.out.println("Please enter the server IP: n.n.n.n");				
			ip = scanner.nextLine();
			
			System.out.println("Please enter the server Socket:");
			int socket = Integer.parseInt(scanner.nextLine());
			InetAddress address = InetAddress.getByName(ip);						
			
			Socket userConnectSocket = new Socket(address, socket);					
			
			System.out.println("Connection open");
			
			PrintStream outputStream = new PrintStream(socket.getOutputStream());				
			BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			ClientInput read = new ClientInput(inputStreamReader, outputStream);
			ClientOutput write = new ClientOutput(outputStream, username);
			
			read.start();
			write.start();
		}

		catch(IOException e){
			e.printStackTrace();
		}
	}
}