import java.io.*;
import java.net.*;

/**
 * Author: Leonardo Schick
 * Data: 17/03/2015
 * Classe: ReverseHandler
 * Esta classe é uma Thread responsável por uma conexão. Ela recebe textos do cliente e devolve
 * o mesmo texto ao contrário. A Thread termina quando o cliente desconecta ou digita EXIT.
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
                pw.println("Digite EXIT para sair");
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
