/**
*	@author Daniel Schwartz
*	29/4/2017
*
*/

import java.net.Socket;

public class ClientAPI{

	private Socket socket;
	public String username;
	public String clientIP;

	public ClientAPI(){

		this.socket = new Socket();
		
		
	}

	public String getUsername(){
		return username;
	}

	public String getClientIP(){
		return clientIP;
	}

}