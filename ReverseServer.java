import java.io.IOException;
import java.net.*;

/**
 * Author: Leonardo Schick
 * Date: 03/17/2015
 * Class: ReverseServer
 * This class receives the connection and starts a thread to handle it.
 */
 
public class ReverseServer {

    //************************************main()**********************************************//
    public static void main(String[] args) {
    	final int port = 16001;
    	try(ServerSocket ss = new ServerSocket(port))
    	{
    		while(true)
        	{
			Socket s = ss.accept();
			Runnable r = new ReverseHandler(s);
			new Thread(r).start();
        	}
    	}catch(IOException e){
        	System.err.println(e.getMessage());
    	}
    }
}
