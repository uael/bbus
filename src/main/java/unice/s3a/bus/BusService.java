package unice.s3a.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unice.s3a.account.Account;
import unice.s3a.box.Box;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * The type Bus service.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BusService {
    @Autowired private BusRepository busRepository;

    /**
     * Add box box.
     * @param busName the bus name
     * @param b       the b
     * @return the box
     */
    @Transactional
    public Box addBox(String busName, Box b) {
        Bus bus = busRepository.findOne(busName);
        bus.getBoxMap().put(b.getName(), b);
        busRepository.save(bus);
        return b;
    }

    /**
     * Delete bus.
     * @param bus the bus
     * @return the bus
     */
    @Transactional
    public Bus delete(Bus bus) {
        busRepository.delete(bus);
        return bus;
    }

    /**
     * Emit boolean.
     * @param busName the bus name
     * @param message the message
     * @return the boolean
     */
    public boolean emit(final String busName, final String message) {
        return busRepository.findOne(busName).emit(message);
    }

    /**
     * Emit boolean.
     * @param busName        the bus name
     * @param message        the message
     * @param expirationDate the expiration date
     * @return the boolean
     */
    public boolean emit(final String busName, final String message, final Date expirationDate) {
        return busRepository.findOne(busName).emit(message, expirationDate);
    }

    /**
     * Emit boolean.
     * @param busName  the bus name
     * @param producer the producer
     * @param content  the content
     * @return the boolean
     */
    public boolean emit(final String busName, Account producer, String content) {
        return busRepository.findOne(busName).emit(producer, content);
    }

    /**
     * Emit boolean.
     * @param busName        the bus name
     * @param producer       the producer
     * @param content        the content
     * @param expirationDate the expiration date
     * @return the boolean
     */
    public boolean emit(final String busName, Account producer, String content, final Date expirationDate) {
        return busRepository.findOne(busName).emit(producer, content, expirationDate);
    }

    /**
     * Emit at boolean.
     * @param busName the bus name
     * @param boxName the box name
     * @param message the message
     * @return the boolean
     */
    public boolean emitAt(final String busName, final String boxName, final String message) {
        return busRepository.findOne(busName).emitAt(boxName, message);
    }

    /**
     * Emit at boolean.
     * @param busName        the bus name
     * @param boxName        the box name
     * @param message        the message
     * @param expirationDate the expiration date
     * @return the boolean
     */
    public boolean emitAt(final String busName, final String boxName, final String message, final Date expirationDate) {
        return busRepository.findOne(busName).emitAt(boxName, message, expirationDate);
    }

    /**
     * Emit at boolean.
     * @param busName  the bus name
     * @param boxName  the box name
     * @param producer the producer
     * @param content  the content
     * @return the boolean
     */
    public boolean emitAt(final String busName, final String boxName, Account producer, String content) {
        return busRepository.findOne(busName).emitAt(boxName, producer, content);
    }

    /**
     * Emit at boolean.
     * @param busName        the bus name
     * @param boxName        the box name
     * @param producer       the producer
     * @param content        the content
     * @param expirationDate the expiration date
     * @return the boolean
     */
    public boolean emitAt(final String busName, final String boxName, Account producer, String content, final Date
        expirationDate) {
        return busRepository.findOne(busName).emitAt(boxName, producer, content, expirationDate);
    }

    /**
     * Find all linked hash map.
     * @return the linked hash map
     */
    @Transactional
    public LinkedHashMap<String, Bus> findAll() {
        LinkedHashMap<String, Bus> map = new LinkedHashMap<>();
        for (Bus b : busRepository.findAll()) {
            map.put(b.getName(), b);
        }
        return map;
    }

    /**
     * Gets bus boxes.
     * @param name the name
     * @return the bus boxes
     */
    @Transactional
    public List<Box> getBusBoxes(String name) {
        return new ArrayList<>(busRepository.findOne(name).getBoxMap().values());
    }

    /**
     * Initialize.
     */
    @PostConstruct
    protected void initialize() {
        save(new Bus("Nice"));
        save(new Bus("Nice Circulation"));
    }

    /**
     * Save bus.
     * @param bus the bus
     * @return the bus
     */
    @Transactional
    public Bus save(Bus bus) {
        busRepository.save(bus);
        return bus;
    }
}
