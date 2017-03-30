import java.lang.Thread;
import java.net.Socket;

class SendThread extends Thread{

	private Socket clientMessageSocket;
	private Socket serverMessageSocket;
	public boolean cont = true;
	
	public SendThread(Socket clientMessageSocket,  Socket serverRequestSocket, Socket serverMessageSocket){
		this.clientMessageSocket = clientMessageSocket;
		this.serverMessageSocket = serverMessageSocket;
	}

	public void run(){

		System.out.println("Send Thread Start");
		int i = 0;
		while(cont){
			System.out.println("Send On");
			i++;
			if (i==10){
				cont=false;
			}
		}

	}
}