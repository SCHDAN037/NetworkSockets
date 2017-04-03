/*
*	The logical model of the server side of the app
*	@author Masixole Ntshinga
*	@Version 01/04/2017
*/

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.*;

/*
 * Send an image from server to a connected client.
 *
 */

public class Server_FileTransfer extends Thread
{
  private String file_location ="";

  public Server_FileTransfer (String file_location) throws ClassNotFoundException, IOException, Exception
  {
    this.file_location = file_location ;
  }

  public void run()
  {
    try {
      ServerSocket serverSocket = new ServerSocket (2020);
      System.out.println("Server running ...");
      System.out.println("Waiting for client tcp connection requests ... ");

      while( true ) {
        Socket c_socket = serverSocket.accept();
        System.out.println("Client Accepted!");

        Path path = Paths.get( file_location );

        FileInputStream fis = new FileInputStream ( path.toString() ) ;

        byte[] data = Files.readAllBytes(path);

        byte [] buffer =  data; // new byte [fis.available()];   // available returns number of bytes
        fis.read(buffer);

        ObjectOutputStream oos = new ObjectOutputStream ( c_socket.getOutputStream());

        oos.writeObject(buffer);
        System.out.println("file sent!");
        oos.close();

        c_socket.close();
      }
    }
    catch(IOException ex)
    { ex.printStackTrace(); }

  }

  public static void transfer_File_Clients()
  {
      System.out.print("Please Enter filename with path to send to connected client: ");
      java.util.Scanner sc = new java.util.Scanner (System.in) ;
      String filename = sc.nextLine() ;

      try {
        Thread t = new Server_FileTransfer ( filename );
        t.start();
      }
      catch (ClassNotFoundException ex) { ex.printStackTrace(); }
      catch (IOException ex) { ex.printStackTrace(); }
      catch (Exception ex) { ex.printStackTrace(); }
  }

  public static void main(String[] args)
  {
      transfer_File_Clients();
  }

}
