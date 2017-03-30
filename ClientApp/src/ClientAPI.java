/**
*	@author Daniel Schwartz
*	29/4/2017
*
*/

import java.net.Socket;
import java.util.concurrent.*;


public class ClientAPI{

	private Socket serverRequestSocket;
	private Socket serverMessageSocket;
	private Socket clientMessageSocket;
	private final ExecutorService pool;
	public String username;
	public String clientIP;

	public ClientAPI(){

		this.clientMessageSocket = new Socket();
		this.serverMessageSocket = new Socket();
		this.serverRequestSocket = new Socket();
		ReceiveThread recieveT = new ReceiveThread(clientMessageSocket, serverMessageSocket);
		SendThread sendT = new SendThread(clientMessageSocket, serverRequestSocket, serverMessageSocket);
		ExecutorService pool = new Executors.newFixedThreadPool(2);
		pool.execute(recieveT);
		pool.execute(sendT);
	}

	public String getUsername(){
		return username;
	}

	public String getClientIP(){
		return clientIP;
	}

}