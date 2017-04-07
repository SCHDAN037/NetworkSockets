/*
*	The logical model of the server's image transfer side of the app
*	@author Masixole Ntshinga
*	@Version 01/04/2017
*/
package server;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.*;
import java.io.*;
import java.lang.Thread;
import java.net.Socket;

public class FileTransfer  extends Thread
{
  private String filePath;
  private String hostname;
  private int portnumber;

  public FileTransfer (String hostname, int portnumber) throws ClassNotFoundException, IOException, Exception
  {
    // this.username = username;
    this.filePath = filePath;
    this.hostname = hostname;
    this.portnumber = portnumber;
  }

  public void run()
  {
      try {
        InetAddress address = InetAddress.getByName( hostname );						// this converts the string ip into an InetAddress object
        Socket c_socket = new Socket (address, portnumber);

        // Get the file name in file_database/
        File f = new File("file_database/");
        String files[] = f.list();

        if ( files.length >0 ) {
          filePath = "file_database/"+files[0] ;

          Path path = Paths.get( filePath );

          FileInputStream fis = new FileInputStream ( path.toString() );

          byte[] data = Files.readAllBytes( path );

          byte [] buffer =  data; // new byte [fis.available()];   // available returns number of bytes
          fis.read(buffer);

          ObjectOutputStream oos = new ObjectOutputStream ( c_socket.getOutputStream());

          oos.writeObject(buffer);
          System.out.println(">>file sent!");
          oos.close();

        }
        else
          System.out.println(">>No file(s) to send!");

        c_socket.close();

      }

    catch(IOException ex)
    { ex.printStackTrace(); }
  }

}
