package unice.s3a.box;

import unice.s3a.account.Account;
import unice.s3a.message.Message;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Box.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "BOX")
public class Box implements java.io.Serializable {
    @Id private String name;
    @ElementCollection(targetClass = Message.class, fetch = FetchType.EAGER)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "MESSAGES", joinColumns = @JoinColumn(name = "BOX_ID"),
        inverseJoinColumns = @JoinColumn(name = "MESSAGE_ID"))
    private List<Message> messages = new ArrayList<>();

    /**
     * Instantiates a new Box.
     */
    public Box() {}

    /**
     * Instantiates a new Box.
     * @param name the name
     */
    public Box(final String name) {
        this.name = name;
    }

    /**
     * Emit boolean.
     * @param message the message
     * @return the boolean
     */
    public boolean emit(final String message) {
        return this.messages.add(new Message(message));
    }

    /**
     * Emit boolean.
     * @param message        the message
     * @param expirationDate the expiration date
     * @return the boolean
     */
    public boolean emit(final String message, final Date expirationDate) {
        return this.messages.add(new Message(message, expirationDate));
    }

    /**
     * Emit boolean.
     * @param producer the producer
     * @param content  the content
     * @return the boolean
     */
    public boolean emit(Account producer, String content) {
        return this.messages.add(new Message(producer, content));
    }

    /**
     * Emit boolean.
     * @param producer       the producer
     * @param content        the content
     * @param expirationDate the expiration date
     * @return the boolean
     */
    public boolean emit(Account producer, String content, final Date expirationDate) {
        return this.messages.add(new Message(producer, content, expirationDate));
    }

    /**
     * Gets messages.
     * @return the messages
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Sets messages.
     * @param messages the messages
     */
    public void setMessages(final ArrayList<Message> messages) {
        this.messages = messages;
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
}
