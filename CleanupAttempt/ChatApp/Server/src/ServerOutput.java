import java.io.PrintStream;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class ServerOutput extends Thread{

	private PrintStream outputStream;
	private ArrayBlockingQueue <Message> messagesQueue;
	
	ServerOutput(ArrayBlockingQueue<Message> messagesQueue, PrintStream outputStream){
		this.outputStream = outputStream;
		this.messagesQueue = messagesQueue;
	}

	public void run(){
		String line = "" ;
		Message message; 
		while (true) {
			if(line.equals("q")) { System.out.println("bye") ; break; }
			try { 
				message = messagesQueue.take(); 
			} 
			catch (Exception e) { System.out.println(e); break;}
			outputStream.println(message.toString()); 
		}
	}
}