package unice.s3a.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unice.s3a.model.Account;
import unice.s3a.model.Box;
import unice.s3a.model.Message;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The type Message service.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MessageService {
    private final AccountService accountService;
    private final MessageRepository messageRepository;
    private final BoxService boxService;
    private final BusService busService;
    private ScheduledExecutorService exec;

    /**
     * Instantiates a new Message service.
     * @param messageRepository the message repository
     * @param accountService the account service
     * @param boxService
     * @param busService
     */
    @Autowired
    public MessageService(final MessageRepository messageRepository, final AccountService accountService, BoxService boxService, BusService busService) {
        this.messageRepository = messageRepository;
        this.accountService = accountService;
        this.boxService = boxService;
        this.busService = busService;
    }

    /**
     * Delete message.
     * @param message the message
     * @return the message
     */
    @Transactional
    public Message delete(Message message) {
        Box b = boxService.findOneByMessage(message);
        if (b != null) {
            b.getMessages().remove(message);
            boxService.save(b);
        }
        messageRepository.delete(message);
        return message;
    }

    /**
     * Save message.
     * @param message the message
     * @return the message
     */
    @Transactional
    public Message save(Message message) {
        messageRepository.save(message);
        return message;
    }

    /**
     * Save message.
     * @param content the content
     * @return the message
     */
    public Message save(final String content) {
        return save(new Message(accountService.current(), content));
    }

    /**
     * Save message.
     * @param content        the content
     * @param expirationDate the expiration date
     * @return the message
     */
    public Message save(final String content, final LocalDateTime expirationDate) {
        return save(new Message(accountService.current(), content, expirationDate));
    }

    /**
     * Save message.
     * @param producer the producer
     * @param content  the content
     * @return the message
     */
    public Message save(final Account producer, final String content) {
        return save(new Message(producer, content));
    }

    /**
     * Save message.
     * @param producer       the producer
     * @param content        the content
     * @param expirationDate the expiration date
     * @return the message
     */
    public Message save(Account producer, String content, final LocalDateTime expirationDate) {
        return save(new Message(producer, content, expirationDate));
    }

    @PostConstruct
    private void init() {
        exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(() -> {
            for (Message m : messageRepository.findAll()) {
                if (!m.isActive()) {
                    delete(m);
                }
            }
        }, 0, 2, TimeUnit.SECONDS);
        exec.scheduleAtFixedRate(() -> {
            busService.emit("Worker", "Hello form worker !");
        }, 0, 15, TimeUnit.SECONDS);
    }

    @PreDestroy
    public void dismantle() throws InterruptedException {
        exec.shutdown();
    }
}
