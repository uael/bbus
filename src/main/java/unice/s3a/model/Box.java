package unice.s3a.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Box.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "BOX")
public class Box implements java.io.Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL, CascadeType.REMOVE})
    @JoinTable(
        name = "MESSAGES",
        joinColumns = @JoinColumn(name = "BOX_ID"),
        inverseJoinColumns = @JoinColumn(name = "MESSAGE_ID")
    )
    private Set<Message> messages = new HashSet<>();

    /**
     * Instantiates a new Box.
     */
    public Box() {}

    /**
     * Instantiates a new Box.
     * @param name the name
     */
    public Box(final String name, Message ...messages) {
        this.name = name;
        for (Message message : messages) {
            this.messages.add(message);
        }
    }

    /**
     * Gets id.
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     * @param id the id
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets messages.
     * @return the messages
     */
    public Set<Message> getMessages() {
        return messages;
    }

    /**
     * Sets messages.
     * @param messages the messages
     */
    public void setMessages(final Set<Message> messages) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return id != null ? id.equals(box.id) : box.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
