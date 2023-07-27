package edu.escuelaing.arsw.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer{
    /**
     * This function tells the server to send messages to the /topic endpoint when a message is
     * received from the client.
     * 
     * @param config This is the MessageBrokerRegistry object that is used to configure the message
     * broker.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    /**
     * This function registers a websocket endpoint at /stompendpoint that allows any origin to connect
     * to it.
     * 
     * @param registry The registry to register the endpoints with.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stompendpoint").setAllowedOriginPatterns("*").withSockJS();

    }
}
