import java.io.IOException;
import java.net.*;

/**
 * Author: Leonardo Schick
 * Data: 17/03/2015
 * Classe: ReverseClient
 * Esta classe recebe uma conexão via socket do cliente, e dispara uma Thread
 * que gerencia essa conexão.
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
