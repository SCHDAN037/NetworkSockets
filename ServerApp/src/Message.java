
/*
*	This class is an abstraction of the messages that are sent between sockets
*	@author Oliver Makins
*	@Version 29/03/2017
*/



import java.util.Date; 
import java.text.DateFormat;
import java.text.SimpleDateFormat;

class Message {
	// values are final since they should never change
	private final String data;
	private final  long time;
	private final String user;

	Message( String d, long t, String u) {
		data = d;
		assert( data != null );
		time = t;
		assert( time != 0 );
		user = u;
		assert( user != null ); 
	}

	/* get methods
	 */
	public String getUser(){
		return user;
	}

	public String getData(){
		return data;
	}

	public long getTime() {
		return time;
	}

	/** Method prints out <time> <user>: <data>
	 *
	 */
	public String toString() {
		Date date = new Date(time);
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateFormatted = formatter.format(date);
		return dateFormatted + " " + user + ": " + data;
	}

}
