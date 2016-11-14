package unice.s3a.bus;

import unice.s3a.account.Account;
import unice.s3a.box.Box;
import unice.s3a.box.BoxMap;
import unice.s3a.message.Message;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("serial")
@Entity
@Table(name = "bus")
public class Bus implements java.io.Serializable {
    @Id
    private String name;
    private ArrayList<String> subscribers;
    private BoxMap boxMap;

    /**
     * Instantiates a new Bus.
     */
    public Bus() {
        this.subscribers = new ArrayList<>();
        this.boxMap = new BoxMap();
    }

    /**
     * Instantiates a new unice.s3a.bus.Bus.
     * @param name the name
     */
    public Bus(String name) {
        this();
        this.name = name;
    }

    /**
     * Emit boolean.
     * @param message the message
     * @return the boolean
     */
    public boolean emit(final String message) {
        return this.boxMap.getOrAdd("Default").emit(message);
    }

    /**
     * Emit boolean.
     * @param message        the message
     * @param expirationDate the expiration date
     * @return the boolean
     */
    public boolean emit(final String message, final Date expirationDate) {
        return this.boxMap.getOrAdd("Default").emit(message, expirationDate);
    }

    /**
     * Emit boolean.
     * @param producer the producer
     * @param content  the content
     * @return the boolean
     */
    public boolean emit(Account producer, String content) {
        return this.boxMap.getOrAdd("Default").emit(producer, content);
    }

    /**
     * Emit boolean.
     * @param producer       the producer
     * @param content        the content
     * @param expirationDate the expiration date
     * @return the boolean
     */
    public boolean emit(Account producer, String content, final Date expirationDate) {
        return this.boxMap.getOrAdd("Default").emit(producer, content, expirationDate);
    }

    /**
     * Emit at boolean.
     * @param boxName the box name
     * @param message the message
     * @return the boolean
     */
    public boolean emitAt(final String boxName, final String message) {
        return this.boxMap.getOrAdd(boxName).emit(message);
    }

    /**
     * Emit at boolean.
     * @param boxName        the box name
     * @param message        the message
     * @param expirationDate the expiration date
     * @return the boolean
     */
    public boolean emitAt(final String boxName, final String message, final Date expirationDate) {
        return this.boxMap.getOrAdd(boxName).emit(message, expirationDate);
    }

    /**
     * Emit at boolean.
     * @param boxName  the box name
     * @param producer the producer
     * @param content  the content
     * @return the boolean
     */
    public boolean emitAt(final String boxName, Account producer, String content) {
        return this.boxMap.getOrAdd(boxName).emit(producer, content);
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
        return this.boxMap.getOrAdd(boxName).emit(producer, content, expirationDate);
    }

    /**
     * Gets box map.
     * @return the box map
     */
    public BoxMap getBoxMap() {
        return boxMap;
    }

    /**
     * Gets all messages.
     * @return the all messages
     */
    public List<Message> getBoxMessages() {
        return this.boxMap.get("Default");
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
        result.addAll(this.boxMap.get(boxName));
        if (!Objects.equals(boxName, "Default")) {
            result.addAll(this.boxMap.get("Default"));
        }
        return result;
    }

    /**
     * Gets subscribers.
     * @return the subscribers
     */
    public ArrayList<String> getSubscribers() {
        return subscribers;
    }

    /**
     * Gets all messages.
     * @return the all messages
     */
    public List<Message> getMessages() {
        List<Message> result = new ArrayList<>();
        for (Box box : this.boxMap.values()) {
            result.addAll(box);
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
     * Remove messages boolean.
     * @param boxName the box name
     * @return the boolean
     */
    public boolean removeMessages(String boxName) {
        return this.boxMap.remove(boxName) != null;
    }
}
