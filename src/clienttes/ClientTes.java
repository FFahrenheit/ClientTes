/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ivan_
 */
public class ClientTes {

    /**
     * @param args the command line arguments 
     */
    public static void main(String[] args) 
    { 
            try {    
            Socket socket = new Socket("127.0.0.1", 1000);
            String message=null;
            JsonObject pack = new JsonObject();
            pack.addProperty("type", "login");
            JsonObject arg = new JsonObject();
            arg.addProperty("username", "issi");
            arg.addProperty("password", "ivan");
            pack.add("args",arg);
            
            Gson gson = new Gson();
            
            message = gson.toJson(pack);
            
            socket.getOutputStream().write(message.getBytes());
            System.out.println("Mensaje enviado");
                            InputStream in = socket.getInputStream();
                            int av;
                            
            while(true)
            {
                av = in.available();
                if(av>0)
                {                    byte[] resp = new byte[av];
                    in.read(resp);
                    if(!new String(resp).equals("p"))
                    {
                        System.out.println("Mensaje: "+new String(resp));
                    }   
                }
            }
            } catch (IOException  ex) {
            Logger.getLogger(ClientTes.class.getName()).log(Level.SEVERE, ex.getMessage());
            }
    }
    
}
