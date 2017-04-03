/*
*	The logical model of the client side of the app
*	@author Masixole Ntshinga
*	@Version 01/04/2017
*/

import java.io.*;
import java.net.UnknownHostException;
import java.net.Socket;
import java.net.InetAddress;

public class Client_FileTransfer extends Thread{

  private String hostname = "";
  private int port_number = 2020;
  private String file_location = "";

  public Client_FileTransfer (String hostname, String file_location)
  {
    this.file_location = file_location ;
    this.hostname = hostname ;
  }

  public void run () {
    try {
      InetAddress address = InetAddress.getByName( hostname );						// this converts the string ip into an InetAddress object
      Socket socket = new Socket ( address , 2020 );

      ObjectInputStream ois = new ObjectInputStream( socket.getInputStream() );
      System.out.println("Client connected to server!");

      try {
        byte[] buffer = (byte[]) ois.readObject();
        //file_location = "/media/image.png";
        // @file_location: location where image will be saved with its name included

        FileOutputStream fos = new FileOutputStream ( file_location );

        fos.write(buffer);
        System.out.println( "File saved as " + file_location);

      }
      catch ( ClassNotFoundException ex)
      { ex.printStackTrace(); }

      socket.close();
    }
    catch (UnknownHostException ex)
    { ex.printStackTrace(); }

    catch (IOException ex)
    { ex.printStackTrace(); }
  }

  public static void connect_To_Server()
  {
      java.util.Scanner sc = new java.util.Scanner (System.in) ;

      System.out.print("Please enter hostname: ");
      String hostname = sc.nextLine();
      System.out.print("");
      String imagePath =  "receivedfile" ;// sc.nextLine();

      Thread cThread = new Client_FileTransfer ( hostname, imagePath );

      cThread.start();
  }

  public static void main(String[] args)
  {
      connect_To_Server();
  }

}
