package unice.s3a.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;


@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @PostConstruct
    protected void initialize() {
        save(new Message("Nouveau rond point"));
        save(new Message("Embouteillage"));
    }

    @Transactional
    public Message save(Message message) {
        messageRepository.save(message);
        return message;
    }

    @Transactional
    public Message delete(Message message) {
        messageRepository.delete(message);
        return message;
    }
}
