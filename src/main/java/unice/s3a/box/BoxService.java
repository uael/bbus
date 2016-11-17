package unice.s3a.box;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unice.s3a.account.Account;
import unice.s3a.message.Message;
import unice.s3a.message.MessageService;

import java.util.Date;

/**
 * The type Box service.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BoxService {
    private final BoxRepository boxRepository;
    private final MessageService messageService;

    /**
     * Instantiates a new Box service.
     * @param boxRepository  the bus repository
     * @param messageService the message service
     */
    @Autowired
    public BoxService(final BoxRepository boxRepository, final MessageService messageService) {
        this.boxRepository = boxRepository;
        this.messageService = messageService;
    }

    /**
     * Delete box.
     * @param box the bus
     * @return the box
     */
    @Transactional
    public Box delete(Box box) {
        for (Message message : box.getMessages()) {
            messageService.delete(message);
        }
        boxRepository.delete(box);
        return box;
    }

    /**
     * Emit message.
     * @param box     the box
     * @param message the message
     * @return the message
     */
    public Message emit(final Box box, final String message) {
        Message m = messageService.save(message);
        box.getMessages().add(m);
        save(box);
        return m;
    }

    /**
     * Emit message.
     * @param box            the box
     * @param message        the message
     * @param expirationDate the expiration date
     * @return the message
     */
    public Message emit(final Box box, final String message, final Date expirationDate) {
        Message m = messageService.save(message, expirationDate);
        box.getMessages().add(m);
        save(box);
        return m;
    }

    /**
     * Emit message.
     * @param box      the box
     * @param producer the producer
     * @param content  the content
     * @return the message
     */
    public Message emit(final Box box, Account producer, String content) {
        Message m = messageService.save(producer, content);
        box.getMessages().add(m);
        save(box);
        return m;
    }

    /**
     * Emit message.
     * @param box            the box
     * @param producer       the producer
     * @param content        the content
     * @param expirationDate the expiration date
     * @return the message
     */
    public Message emit(final Box box, Account producer, String content, final Date expirationDate) {
        Message m = messageService.save(new Message(producer, content, expirationDate));
        box.getMessages().add(m);
        save(box);
        return m;
    }

    /**
     * Save box.
     * @param box the bus
     * @return the box
     */
    @Transactional
    public Box save(Box box) {
        boxRepository.save(box);
        return box;
    }
}
