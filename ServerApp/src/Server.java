/*
*	The logical model of the Server side of the project
*	@author Oliver Makins
*	@Version 29/03/2017
*/

import java.util.LinkedList;
import java.util.List;


public class Server {
	
	private Message [] messages; // array of the messages in the chat
	private List <int[]> backup; // previous arrays of messages

	Server() {
		messages = new Message[1000];
		backup = new LinkedList<int[]>();
	}





} 
