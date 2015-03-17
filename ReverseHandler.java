import java.io.*;
import java.net.*;

/**
 * Author: Leonardo Schick
 * Date: 03/17/2015
 * Class: ReverseHandler
 * Thread that handles each connection to the server. Receives the String from the client
 * reverses it and sends it back. Thread stops if the String given is EXIT or the connection
 * closes.
 */
 
public class ReverseHandler implements Runnable{

    private Socket s;
	
    //************************************ReverseHandler()************************************//
    public ReverseHandler(Socket s)
    {
        this.s = s;
    }
    //************************************reverse()*******************************************//
    public static String reverse(String s)
    {
        StringBuffer sb = new StringBuffer(s);
        return new String(sb.reverse());
    }
    
    //************************************run()***********************************************//
    @Override
    public void run() {
        try{
            try{
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                //PrintWriter(OutputStream,[flush output?])
                PrintWriter pw = new PrintWriter(os,true);
                pw.println("Type EXIT to exit");
		String line;
		while((line = br.readLine())!= null)
                {
			if(line.trim().equalsIgnoreCase("EXIT")) break;		
			pw.println("> " + reverse(line));
                }       
            }catch(SocketException e){
                System.err.println(e);
            }finally{s.close();}
        }catch(IOException e){
            System.err.println(e);    
        }
    }
}
