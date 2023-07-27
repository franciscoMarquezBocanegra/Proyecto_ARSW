package edu.escuelaing.arsw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SubastaController {
    
    @Autowired
    SimpMessagingTemplate msgt;

    /**
     * It receives a list of strings, and depending on the size of the list, it sends a message to the
     * topic /topic/subasta + product
     * 
     * @param mensajes List of Strings
     */
    @MessageMapping("/subasta")
    public void handleSubastaState(List<String> mensajes) {
        String product = mensajes.get(1);
        if(mensajes.size() == 3 || mensajes.size() == 2) {    		
    		String respuesta =  product + "." + mensajes.get(0);
    		msgt.convertAndSend("/topic/subasta" + product, respuesta);
    	}else {
    		Integer mensaje = Integer.parseInt(mensajes.get(0));
            Integer precio = Integer.parseInt(mensajes.get(2));
            String username = mensajes.get(3);
            String respuesta = null;
            if(mensaje > precio) { 
            	
                respuesta= mensaje.toString() +"-"+ username;
                System.out.println(respuesta);
            }else {
            	respuesta= precio.toString();
            }
            msgt.convertAndSend("/topic/subasta" + product, respuesta);
    	}
    }
}
