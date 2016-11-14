package unice.s3a.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unice.s3a.account.Account;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.LinkedHashMap;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BusService {

    @Autowired
    private BusRepository busRepository;

    @PostConstruct
    protected void initialize() {
        save(new Bus("Nice"));
        save(new Bus("Nice Circulation"));
    }

    @Transactional
    public Bus save(Bus bus) {
        busRepository.save(bus);
        return bus;
    }

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

    public LinkedHashMap<String, Bus> findAll() {
        LinkedHashMap<String, Bus> map = new LinkedHashMap<>();
        for (Bus b : busRepository.findAll()) {
            map.put(b.getName(), b);
        }

        return map;
    }
}
