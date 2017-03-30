/** this class is a very stipped down client that allows me to send messages
 * it is purely for testing
 *
 * @Author: Oliver Makins
 * @Version: 29/03/2017
 */

import java.util.Scanner;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class client {

	public static String user;

	public static void main (String [] args) {
		try{ 
			Scanner s = new Scanner(System.in); 									// scanner for user input
			System.out.println("Welcome to Proprietary Open Relay Network");
			System.out.println("Please enter your username: ");						// figure out the user's name
			user = s.nextLine();
			System.out.println("Please enter the server IP: n.n.n.n");				// prompt for an IP in string format
			// note: 127.0.0.1 will always connect you to your machine
			String ip = s.nextLine();
			InetAddress address = InetAddress.getByName(ip);						// this converts the string ip into an InetAddress object
			Socket socket = new Socket(address, 2017);								// this opens a Socket on port 2017 with the specified ip address
			System.out.println("Connection open");
			PrintStream ps = new PrintStream(socket.getOutputStream());				// this prinstream just allows the user to write to the socket
			BufferedReader re = new BufferedReader(new InputStreamReader(socket.getInputStream()));	// this reads everything that the is written to the socket

			// create and start threads to read what the user types and write messages to std.out
			write w = new write(ps, user);
			read r = new read(re);
			w.start();
			r.start();


		}
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
	}

}
