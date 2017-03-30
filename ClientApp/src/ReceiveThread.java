import java.lang.Thread;
import java.net.Socket;


class ReceiveThread extends Thread {
	
	private Socket clientMessageSocket;
	private Socket serverRequestSocket;
	private Socket serverMessageSocket;
	public boolean cont = true;

	public ReceiveThread(Socket clientMessageSocket, Socket serverMessageSocket){

	}

	public void run(){
		System.out.println("Receive Thread Start");
		int i = 0;
		while(cont){
			System.out.println("Receive On");
			i++;
			if (i==10){
				cont=false;
			}
		}

	}

}