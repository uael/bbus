package unice.s3a.bus;

import unice.s3a.account.Account;
import unice.s3a.box.Box;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Bus.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "BUS")
public class Bus implements java.io.Serializable {
    @Id
    private String name;

    @ElementCollection(targetClass = Account.class, fetch = FetchType.EAGER)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "SUBSCRIBERS",
        joinColumns = @JoinColumn(name = "BUS_ID", unique=false),
        inverseJoinColumns = @JoinColumn(name = "ACCOUNT_ID", unique=false)
    )
    private List<Account> subscribers = new ArrayList<>();

    @ElementCollection(targetClass = Box.class, fetch = FetchType.EAGER)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "BOXES",
        joinColumns = @JoinColumn(name = "BUS_ID"),
        inverseJoinColumns = @JoinColumn(name = "BOX_ID")
    )
    @MapKey(name = "name")
    private Map<String, Box> boxes = new HashMap<>();

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
     * Gets boxes.
     * @return the boxes
     */
    public Map<String, Box> getBoxes() {
        return boxes;
    }

    /**
     * Sets boxes.
     * @param boxes the boxes
     */
    public void setBoxes(final Map<String, Box> boxes) {
        this.boxes = boxes;
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
     * Is subscribed boolean.
     * @param account the account
     * @return the boolean
     */
    public boolean isSubscribed(Account account) {
        boolean r = this.getSubscribers().contains(account);
        return r;
    }

    /**
     * Subscribe boolean.
     * @param account the account
     * @return the boolean
     */
    public boolean subscribe(Account account) {
        return !isSubscribed(account) && this.getSubscribers().add(account);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) { return true; }
        if (!(o instanceof Bus)) { return false; }
        Bus bus = (Bus) o;
        return getName().equals(bus.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
