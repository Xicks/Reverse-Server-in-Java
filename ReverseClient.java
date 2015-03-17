import java.io.*;
import java.net.*;
import java.util.*;
/**
 * Author: Leonardo Schick
 * Date: 03/17/2015
 * Classe: ReverseClient
 * This class opens a connection with the server using a socket.
 * Reads the input from the user, sends it to the server and shows the server's response.
 */
 
public class ReverseClient {
	
    //************************************main()**********************************************//
    public static void main(String[] args)
    {
       	Socket s = null;  
        OutputStream os = null;
	InputStream in = null;
        BufferedReader reader = null;
        PrintWriter pw = null;
        try{
            try{
            		//Gets the machine name
			String computerName = InetAddress.getLocalHost().getHostName();
			//Creates a socket using the port given by argument
			s = new Socket(computerName,16001);
			os = s.getOutputStream();
			in = s.getInputStream();
			//Object responsible to read from the server
			reader = new BufferedReader(new InputStreamReader(in));
			//Object responsible to writes to the server
			pw = new PrintWriter(os,true);
            }catch(UnknownHostException e)
            {
                System.err.println("Trying to connect to unknown host: " + e);
            }
        }catch(IOException e){
            System.err.println(e);
        }   
        if (s != null && os != null && reader != null && pw != null) {
            try {
                Scanner sc = new Scanner(System.in);
			String responseLine = reader.readLine();
			do{
				System.out.println(responseLine);
				String str = sc.nextLine();
				pw.println(str);
			}while((responseLine = reader.readLine()) != null);
                os.close();
                reader.close();
                s.close();   
            } catch (IOException e) {
		System.err.println(e);
            }
        }
    }		
}

