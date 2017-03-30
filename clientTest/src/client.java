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
			Scanner s = new Scanner(System.in);
			System.out.println("Welcome to Proprietary Open Relay Network");
			System.out.println("Please enter your username: ");
			user = s.nextLine();
			System.out.println("Please enter the server IP: n.n.n.n");
			String ip = s.nextLine();
			InetAddress address = InetAddress.getByName(ip);
			Socket socket = new Socket(address, 2017);
			System.out.println("Connection open");
			PrintStream ps = new PrintStream(socket.getOutputStream());
			BufferedReader re = new BufferedReader(new InputStreamReader(socket.getInputStream()));

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
