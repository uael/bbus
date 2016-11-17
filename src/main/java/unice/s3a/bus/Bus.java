package unice.s3a.bus;

import unice.s3a.account.Account;
import unice.s3a.box.Box;
import unice.s3a.message.Message;

import javax.persistence.*;
import java.util.*;

/**
 * The type Bus.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "BUS")
public class Bus implements java.io.Serializable {
    @Id private String name;
    @ElementCollection(targetClass = Account.class, fetch = FetchType.EAGER)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "SUBSCRIBERS", joinColumns = @JoinColumn(name = "BUS_ID"),
        inverseJoinColumns = @JoinColumn(name = "ACCOUNT_ID"))
    private List<Account> subscribers = new ArrayList<>();
    @ElementCollection(targetClass = Box.class, fetch = FetchType.EAGER)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "BOXES", joinColumns = @JoinColumn(name = "BUS_ID"),
        inverseJoinColumns = @JoinColumn(name = "BOX_ID"))
    @MapKey(name = "name")
    private Map<String, Box> boxMap = new HashMap<>();

    /**
     * Instantiates a new Bus.
     */
    public Bus() { }

    /**
     * Instantiates a new unice.s3a.bus.Bus.
     * @param name the name
     */
    public Bus(String name) {
        this();
        this.name = name;
    }

    /**
     * Add box box.
     * @param name the name
     * @return the box
     */
    public Box addBox(String name) {
        Box box = new Box(name);
        this.boxMap.put(name, box);
        return box;
    }

    /**
     * Emit boolean.
     * @param message the message
     * @return the boolean
     */
    public boolean emit(final String message) {
        return this.boxMap.get("Default").emit(message);
    }

    /**
     * Emit boolean.
     * @param message        the message
     * @param expirationDate the expiration date
     * @return the boolean
     */
    public boolean emit(final String message, final Date expirationDate) {
        return this.boxMap.get("Default").emit(message, expirationDate);
    }

    /**
     * Emit boolean.
     * @param producer the producer
     * @param content  the content
     * @return the boolean
     */
    public boolean emit(Account producer, String content) {
        return this.boxMap.get("Default").emit(producer, content);
    }

    /**
     * Emit boolean.
     * @param producer       the producer
     * @param content        the content
     * @param expirationDate the expiration date
     * @return the boolean
     */
    public boolean emit(Account producer, String content, final Date expirationDate) {
        return this.boxMap.get("Default").emit(producer, content, expirationDate);
    }

    /**
     * Emit at boolean.
     * @param boxName the box name
     * @param message the message
     * @return the boolean
     */
    public boolean emitAt(final String boxName, final String message) {
        return this.boxMap.get(boxName).emit(message);
    }

    /**
     * Emit at boolean.
     * @param boxName        the box name
     * @param message        the message
     * @param expirationDate the expiration date
     * @return the boolean
     */
    public boolean emitAt(final String boxName, final String message, final Date expirationDate) {
        return this.boxMap.get(boxName).emit(message, expirationDate);
    }

    /**
     * Emit at boolean.
     * @param boxName  the box name
     * @param producer the producer
     * @param content  the content
     * @return the boolean
     */
    public boolean emitAt(final String boxName, Account producer, String content) {
        return this.boxMap.get(boxName).emit(producer, content);
    }

    /**
     * Emit at boolean.
     * @param boxName        the box name
     * @param producer       the producer
     * @param content        the content
     * @param expirationDate the expiration date
     * @return the boolean
     */
    public boolean emitAt(final String boxName, Account producer, String content, final Date expirationDate) {
        return this.boxMap.get(boxName).emit(producer, content, expirationDate);
    }

    /**
     * Gets box map.
     * @return the box map
     */
    public Map<String, Box> getBoxMap() {
        return boxMap;
    }

    /**
     * Sets box map.
     * @param boxMap the box map
     */
    public void setBoxMap(final Map<String, Box> boxMap) {
        this.boxMap = boxMap;
    }

    /**
     * Gets all messages.
     * @return the all messages
     */
    public List<Message> getBoxMessages() {
        return this.boxMap.get("Default").getMessages();
    }

    /**
     * Gets all messages.
     * @param boxName the box name
     * @return the all messages
     */
    public List<Message> getBoxMessages(String boxName) {
        if (!this.boxMap.containsKey(boxName)) {
            throw new IllegalArgumentException("Box "+boxName+" does not exists.");
        }
        List<Message> result = new ArrayList<>();
        result.addAll(this.boxMap.get(boxName).getMessages());
        if (!Objects.equals(boxName, "Default")) {
            result.addAll(this.boxMap.get("Default").getMessages());
        }
        return result;
    }

    /**
     * Gets boxes.
     * @return the boxes
     */
    public List<Box> getBoxes() {
        return new ArrayList<>(this.boxMap.values());
    }

    /**
     * Gets all messages.
     * @return the all messages
     */
    public List<Message> getMessages() {
        List<Message> result = new ArrayList<>();
        for (Box box : this.boxMap.values()) {
            result.addAll(box.getMessages());
        }
        return result;
    }

    /**
     * Gets name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     * @param name the name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets subscribers.
     * @return the subscribers
     */
    public List<Account> getSubscribers() {
        return subscribers;
    }

    /**
     * Sets subscribers.
     * @param subscribers the subscribers
     */
    public void setSubscribers(final List<Account> subscribers) {
        this.subscribers = subscribers;
    }

    /**
     * Remove messages boolean.
     * @param boxName the box name
     * @return the boolean
     */
    public boolean removeMessages(String boxName) {
        return this.boxMap.remove(boxName) != null;
    }

    @Override
    public String toString() {
        return name;
    }
}
