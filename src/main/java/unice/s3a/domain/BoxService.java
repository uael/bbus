package unice.s3a.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unice.s3a.model.Account;
import unice.s3a.model.Box;
import unice.s3a.model.Message;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * The type Box service.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BoxService {
    private final BoxRepository boxRepository;
    private final BusService busService;
    private final MessageService messageService;

    /**
     * LocalDateTimeiates a new Box service.
     * @param boxRepository  the bus repository
     * @param busService     the bus service
     * @param messageService the message service
     */
    @Autowired
    public BoxService(final BoxRepository boxRepository, final BusService busService, final MessageService messageService) {
        this.boxRepository = boxRepository;
        this.busService = busService;
        this.messageService = messageService;
    }

    /**
     * Delete box.
     * @param box the bus
     * @return the box
     */
    @Transactional
    public Box delete(Box box) {
        busService.delete(box);
        boxRepository.delete(box);
        return box;
    }

    /**
     * Delete box.
     * @param box the bus
     * @return the box
     */
    @Transactional
    public Box delete(String box) {
        return delete(boxRepository.findOneByName(box));
    }

    /**
     * Emit message.
     * @param id      the box id
     * @param message the message
     * @return the message
     */
    public Message emit(final Long id, final String message) {
        Message m =new Message(message);
        Box box = boxRepository.findOne(id);
        box.getMessages().add(m);
        save(box);
        return m;
    }

    /**
     * Emit message.
     * @param id             the box id
     * @param message        the message
     * @param expirationDate the expiration date
     * @return the message
     */
    public Message emit(final Long id, final String message, final LocalDateTime expirationDate) {
        Message m = new Message(message, expirationDate);
        Box box = boxRepository.findOne(id);
        box.getMessages().add(m);
        save(box);
        return m;
    }

    /**
     * Emit message.
     * @param id       the box id
     * @param producer the producer
     * @param content  the content
     * @return the message
     */
    public Message emit(final Long id, Account producer, String content) {
        Message m = new Message(producer, content);
        Box box = boxRepository.findOne(id);
        box.getMessages().add(m);
        save(box);
        return m;
    }

    @Transactional
    public Box findOneByMessage(Message m) {
        for (Box b : boxRepository.findAll()) {
            if (b.getMessages().contains(m)) {
                return b;
            }
        }
        return null;
    }

    /**
     * Emit message.
     * @param id             the box id
     * @param producer       the producer
     * @param content        the content
     * @param expirationDate the expiration date
     * @return the message
     */
    public Message emit(final Long id, Account producer, String content, final LocalDateTime expirationDate) {
        Message m = new Message(producer, content, expirationDate);
        Box box = boxRepository.findOne(id);
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

    @Transactional
    public Box findOne(Long id) {
        return boxRepository.findOne(id);
    }

    /**
     * Save box.
     * @param name the name
     * @return the box
     */
    public Box save(final String name) {
        return save(new Box(name));
    }
}
