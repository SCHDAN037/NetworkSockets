/*
*	The logical model of the client's image receiver side of the app
*	@author Masixole Ntshinga
*	@Version 01/04/2017
*/

import java.io.*;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.net.UnknownHostException;
import java.net.Socket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.*;
import java.lang.Thread;

public class FileReceiver  extends Thread
{
  private String file_location;
  private String usename;
  private ServerSocket receiverSocket ;
  private int fR_port;

  public FileReceiver (String file_location,int fR_port) throws ClassNotFoundException, IOException, Exception
  {
      this.file_location = file_location ;
      this.fR_port = fR_port ;
  }

  public void run ()
  {
    try {
      receiverSocket = new ServerSocket (fR_port);
      // System.out.println("\nFile receiver thread running on port 2020... ");
      // System.out.println("Waiting to receive file");

      boolean file_received = false;

      while ( true ) {
        Socket c_Socket = receiverSocket.accept();

        ObjectInputStream ois = new ObjectInputStream( c_Socket.getInputStream() );
        //System.out.println("Client connected to server!");

        try {
          byte[] buffer = (byte[]) ois.readObject();

          // @file_location: location where image will be saved with its name included
          // FileOutputStream fos = new FileOutputStream ( file_location );
          FileOutputStream fos = new FileOutputStream ( new File ( file_location ) );

          fos.write(buffer);
          System.out.println( ">>File saved as '" + file_location +"'");

          ois.close();
          c_Socket.close();
          file_received = true;
        }
        catch ( ClassNotFoundException ex)
        { ex.printStackTrace(); }

        if ( file_received )
        {
          receiverSocket.close();
          break;
        }

      }
    }
    catch (UnknownHostException ex)
    { ex.printStackTrace(); }

    catch (IOException ex)
    { ex.printStackTrace(); }
  }

}
