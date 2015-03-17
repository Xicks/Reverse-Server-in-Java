import java.io.*;
import java.net.*;
import java.util.*;
/**
 * Author: Leonardo Schick
 * Data: 17/03/2015
 * Classe: ReverseClient
 * Esta classe abre uma conexão vai socket com o servidor. Ela lê um texto do usuário,
 * manda para o servidor e mostra a resposta.
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
				String computerName = InetAddress.getLocalHost().getHostName();
				s = new Socket(computerName,16001);
				os = s.getOutputStream();
				in = s.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in));
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

