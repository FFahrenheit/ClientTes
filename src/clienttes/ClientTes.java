/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
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
            Socket socket = new Socket("127.0.0.1", 5000);
            String message=null;
            JsonObject pack = new JsonObject();
            pack.addProperty("type", "login");
            JsonObject arg = new JsonObject();
            arg.addProperty("username", "ivan");
            arg.addProperty("password", "ivan");
            pack.add("args",arg);
            
            Gson gson = new Gson();
            
            message = gson.toJson(pack);
            
            Thread.sleep(1000);
            socket.getOutputStream().write(message.getBytes());
            System.out.println("Mensaje enviado");
            Thread.sleep(1000);
            while(socket.getInputStream().available()<=0)
            {
                
            };
            byte[] resp = new byte[socket.getInputStream().available()];
            socket.getInputStream().read(resp);
                System.out.println(new String(resp));
            } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ClientTes.class.getName()).log(Level.SEVERE, ex.getMessage());
            }
    }
    
}
