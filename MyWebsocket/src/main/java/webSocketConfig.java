import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Component
@EnableWebSocket
public class webSocketConfig implements WebSocketConfigurer {

    @Autowired
//    private ChatWebSocketHandler chatWebSocketHandler;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

//        registry.addHandler(chatWebSocketHandler,"/ws");
//        registry.addHandler(chatWebSocketHandler,"/ws/sockjs").withSockJS();
    }
}
