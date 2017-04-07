import java.io.PrintStream;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;
import java.nio.file.*;
import java.io.File;

/** this class writes everything it sees to a printstream
 * @Author Oliver Makins
 * @Version 29/03/2017
 */

public class write extends Thread {

	private PrintStream printStream;
	private Scanner scanner ;
	private String user;
	public static boolean quit;
	public static BufferedReader reader;
	public static PrintStream stream;
	public static Socket userSocket;
	public static Thread fileTransfer_Thread;
	public static Thread fileReceiver_Thread;

	write(PrintStream p, String user, Socket userSocket, BufferedReader reader) {
		this.printStream = p;
		this.scanner = new Scanner(System.in);
		this.user = user;
		this.userSocket = userSocket;
		this.reader = reader;
		printStream.println(user); // send the server the name
	}

	public void quit(){
		try{
			reader.close();
			printStream.close();
			userSocket.close();
			System.out.println("Quitting client");
			System.exit(0);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void startFileReceiverConnection (String filePath,int fR_port)
	{
	  // ":send /media/image.png"
		Path fR_filePath = Paths.get( filePath );

		try {
			fileReceiver_Thread = new FileReceiver ( fR_filePath.toString(), fR_port );
			fileReceiver_Thread.start();
		}
		catch (ClassNotFoundException ex) { ex.printStackTrace(); }
		catch (IOException ex) { ex.printStackTrace(); }
		catch (Exception ex) { ex.printStackTrace(); }
	}

	public boolean transferFileToServer( String filename, String hostname,int portnumber)
	{
		boolean sent = false;
		try {
			fileTransfer_Thread = new FileTransfer ( filename, hostname, portnumber );
			fileTransfer_Thread.start();
			sent = true;
		}
		catch (ClassNotFoundException ex) { ex.printStackTrace(); }
		catch (IOException ex) { ex.printStackTrace(); }
		catch (Exception ex) { ex.printStackTrace(); }
		return sent;
	}

	public void run() {
		String line = "";
		this.quit = false;
		while (true) { // keep looping
			line = scanner.nextLine(); // keep reading what the user says

			if(line.equals(":q") || line.equals(":quit")) { // this doesn't work 100%, it is meant to allow the user to quit
				printStream.println(line); // print as normal so that the server can process the quit
				System.out.println("Closing connection, exiting");
				break; // leaves the infinite loop
			}

			// Client request file tranfer,
			// Server starts RUNNING fileReceiver_Thread,
			// Client Sends file to Server
			else if ( line.toUpperCase().contains(":SEND") ) {
				System.out.println(">Requesting file transfer ...");

				int fR_portnumber = 2021;
				String filename = line.substring( line.lastIndexOf(" ")+1 ) ;
				String fR_hostname = userSocket.getInetAddress().getHostName();
				boolean sent_to_server = transferFileToServer( filename, fR_hostname, fR_portnumber );

				if ( sent_to_server ){
					printStream.println(user + ": " + line); // write it to the chat
				}
				else {
					System.out.println("File not sent! please enter the correct file path!");
				}
			}

			// Client accepts offer.
			// Send Client connection details to Server to receive file.
			else if ( line.toUpperCase().contains(":Y") )
			{
				String fR_hostname = userSocket.getInetAddress().getHostName();
				int fR_port = 2016;

				int files_count=0;
				{
					File f = new File("file_database/");
					String files[] = f.list();

					for (int i=0; i<files.length; i++)
						files_count++;
				}
				this.startFileReceiverConnection("file_database/receivedfile"+files_count, fR_port);

				// Write message to chat with ( hostname + portnumber )
				printStream.println(user + ": " + line + " @port:"+ fR_port + ",hostname:" +fR_hostname );
			}

			else {
				// Format the user's input.
				printStream.println(user + ": " + line); // write it to the chat
			}
		}
		quit(); // we have left the thread so we can quit
	}
}
