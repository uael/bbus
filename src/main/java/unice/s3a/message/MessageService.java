package unice.s3a.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unice.s3a.account.Account;
import unice.s3a.account.AccountService;

import java.util.Date;

/**
 * The type Message service.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MessageService {
    private final AccountService accountService;
    private final MessageRepository messageRepository;

    /**
     * Instantiates a new Message service.
     * @param messageRepository the message repository
     * @param accountService the account service
     */
    @Autowired
    public MessageService(final MessageRepository messageRepository, final AccountService accountService) {
        this.messageRepository = messageRepository;
        this.accountService = accountService;
    }

    /**
     * Delete message.
     * @param message the message
     * @return the message
     */
    @Transactional
    public Message delete(Message message) {
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
    public Message save(final String content, final Date expirationDate) {
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
    public Message save(Account producer, String content, final Date expirationDate) {
        return save(new Message(producer, content, expirationDate));
    }
}
