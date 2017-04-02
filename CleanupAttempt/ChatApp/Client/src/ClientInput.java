import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClientInput extends Thread{
	BufferedReader inputStreamReader;

	ClientInput(BufferedReader iSR){
		this.inputStreamReader = iSR;
	}

	public void run(){
		String line = "";
		try{
			while (true) {
				synchronized(this) {
					while (!reader.ready()) {
						wait(1000);
					}

					if (reader.ready()){
					  line = reader.readLine(); // read what was sent to the client
					  String userFormatColor = (char)27 + "[36m";
					  String clearColor  = (char)27 + "[37m" ;
					  System.out.println(userFormatColor + line + clearColor); // print it to the terminal
					}
			}

			if(line.equals("q")) break;

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}