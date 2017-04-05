/*
*	The logical model of the server's image transfer side of the app
*	@author Masixole Ntshinga
*	@Version 01/04/2017
*/

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.*;
import java.lang.Thread;
import java.net.Socket;
import java.net.URL;

public class FileTransfer  extends Thread
{
  private String filePath;
  private String hostname;
  private int portnumber;

  public FileTransfer (String filePath, String hostname, int portnumber) throws ClassNotFoundException, IOException, Exception
  {
    // this.username = username;
    this.filePath = filePath;
    this.hostname = hostname;
    this.portnumber = portnumber  ;
  }

  public void run()
  {
      try {
        InetAddress address = InetAddress.getByName( hostname );  // this converts the string ip into an InetAddress object
        // Socket c_socket = new Socket (hostname, portnumber);
        Socket c_socket = new Socket ( address , portnumber);

        // Path path = Paths.get( filePath );

        URL path = FileTransfer.class.getResource( filePath );
        FileInputStream fis = new FileInputStream ( new File(path.getFile() ) ); //new File (path.toString()) );

        byte[] data = Files.readAllBytes( Paths.get(path.toString()) );

        byte [] buffer =  data; // new byte [fis.available()];   // available returns number of bytes
        fis.read(buffer);

        ObjectOutputStream oos = new ObjectOutputStream ( c_socket.getOutputStream());

        oos.writeObject(buffer);
        System.out.println("file sent!");
        oos.close();
        c_socket.close();
      }
      catch(UnknownHostException ex)
      {
        System.out.println( "\nError - Unknown Host : "+ex.toString() );
        // ex.printStackTrace();
      }
      catch(IOException ex)
      {
        ex.printStackTrace();
      }
  }

}
