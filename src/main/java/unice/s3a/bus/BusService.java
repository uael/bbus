package unice.s3a.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unice.s3a.account.Account;
import unice.s3a.box.Box;
import unice.s3a.box.BoxService;
import unice.s3a.message.Message;

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
    private final BoxService boxService;
    private final BusRepository busRepository;

    /**
     * Instantiates a new Bus service.
     * @param busRepository the bus repository
     * @param boxService    the box service
     */
    @Autowired
    public BusService(final BusRepository busRepository, final BoxService boxService) {
        this.busRepository = busRepository;
        this.boxService = boxService;
    }

    /**
     * Add box box.
     * @param busName the bus name
     * @param b   the b
     * @return the box
     */
    @Transactional
    public Box addBox(String busName, Box b) {
        Bus bus = this.findOne(busName);
        bus.getBoxes().put(b.getName(), b);
        save(bus);
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
     * Delete bus.
     * @param bus the bus
     * @return the bus
     */
    @Transactional
    public Bus delete(String bus) {
        return delete(busRepository.findOne(bus));
    }

    /**
     * Delete box.
     * @param box the box
     * @return the box
     */
    @Transactional
    public Box delete(Box box) {
        Bus bus = null;
        for (Bus _bus : busRepository.findAll()) {
            if (_bus.getBoxes().containsValue(box)) {
                bus = _bus;
                break;
            }
        }
        if (bus != null) {
            bus.getBoxes().remove(box.getName());
            save(bus);
        }
        return box;
    }

    /**
     * Emit message.
     * @param bus     the bus
     * @param message the message
     * @return the message
     */
    @Transactional
    public Message emit(final String bus, final String message) {
        return boxService.emit(this.findOne(bus).getBoxes().get("Default").getId(), message);
    }

    /**
     * Emit message.
     * @param bus            the bus
     * @param message        the message
     * @param expirationDate the expiration date
     * @return the message
     */
    @Transactional
    public Message emit(final String bus, final String message, final Date expirationDate) {
        return boxService.emit(this.findOne(bus).getBoxes().get("Default").getId(), message, expirationDate);
    }

    /**
     * Emit message.
     * @param bus      the bus
     * @param producer the producer
     * @param content  the content
     * @return the message
     */
    @Transactional
    public Message emit(final String bus, Account producer, String content) {
        return boxService.emit(this.findOne(bus).getBoxes().get("Default").getId(), producer, content);
    }

    /**
     * Emit message.
     * @param bus            the bus
     * @param producer       the producer
     * @param content        the content
     * @param expirationDate the expiration date
     * @return the message
     */
    @Transactional
    public Message emit(final String bus, Account producer, String content, final Date expirationDate) {
        return boxService.emit(this.findOne(bus).getBoxes().get("Default").getId(), producer, content, expirationDate);
    }

    /**
     * Emit message.
     * @param box     the box
     * @param message the message
     * @return the message
     */
    @Transactional
    public Message emit(final String bus, final String box, final String message) {
        return boxService.emit(this.findOne(bus).getBoxes().get(box).getId(), message);
    }

    /**
     * Emit message.
     * @param box            the box
     * @param message        the message
     * @param expirationDate the expiration date
     * @return the message
     */
    @Transactional
    public Message emit(final String bus, final String box, final String message, final Date expirationDate) {
        return boxService.emit(this.findOne(bus).getBoxes().get(box).getId(), message, expirationDate);
    }

    /**
     * Emit message.
     * @param box      the box
     * @param producer the producer
     * @param content  the content
     * @return the message
     */
    @Transactional
    public Message emit(final String bus, final String box, Account producer, String content) {
        return boxService.emit(this.findOne(bus).getBoxes().get(box).getId(), producer, content);
    }

    /**
     * Emit message.
     * @param box            the box
     * @param producer       the producer
     * @param content        the content
     * @param expirationDate the expiration date
     * @return the message
     */
    @Transactional
    public Message emit(final String bus, final String box, Account producer, String content, final Date expirationDate) {
        return boxService.emit(this.findOne(bus).getBoxes().get(box).getId(), producer, content, expirationDate);
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
        return new ArrayList<>(busRepository.findOne(name).getBoxes().values());
    }

    /**
     * Has boolean.
     * @param name the name
     * @return the boolean
     */
    @Transactional
    public boolean has(String name) {
        return busRepository.findOne(name) != null;
    }

    /**
     * Has boolean.
     * @param bus  the bus
     * @param name the name
     * @return the boolean
     */
    public boolean hasBox(Bus bus, String name) {
        return bus.getBoxes().containsKey(name);
    }

    /**
     * Initialize.
     */
    @PostConstruct
    protected void initialize() {
        Bus nice = new Bus("Nice");
        save(nice);
        addBox("Nice", boxService.save("City"));
        addBox("Nice", boxService.save("Market"));

        Bus niceCirculation = new Bus("Nice Circulation");
        save(niceCirculation);
        addBox("Nice Circulation", boxService.save("Embouteillage"));
        addBox("Nice Circulation", boxService.save("Rond-point"));
    }

    /**
     * Find one bus.
     * @param name the name
     * @return the bus
     */
    @Transactional
    public Bus findOne(String name) {
        return busRepository.findOne(name);
    }

    /**
     * Save bus.
     * @param bus the bus
     * @return the bus
     */
    @Transactional
    public Bus save(Bus bus) {
        if (!bus.getBoxes().containsKey("Default")) {
            bus.getBoxes().put("Default", boxService.save("Default"));
        }
        busRepository.save(bus);
        return bus;
    }

    /**
     * Save bus.
     * @param name the name
     * @return the bus
     */
    public Bus save(String name) {
        return save(new Bus(name));
    }
}
