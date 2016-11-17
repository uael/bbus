package unice.s3a.box;

import unice.s3a.message.Message;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "NAME")
    private String name;

    @ElementCollection(targetClass = Message.class, fetch = FetchType.EAGER)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "MESSAGES",
        joinColumns = @JoinColumn(name = "BOX_ID"),
        inverseJoinColumns = @JoinColumn(name = "MESSAGE_ID")
    )
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) { return true; }
        if (!(o instanceof Box)) { return false; }
        Box box = (Box) o;
        return getId().equals(box.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
