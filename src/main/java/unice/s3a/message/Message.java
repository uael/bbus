package unice.s3a.message;

import unice.s3a.account.Account;

import javax.persistence.*;
import java.util.Date;

/**
 * The type unice.s3a.message.Message.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "MESSAGE")
public class Message implements java.io.Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "EXPIRATION_DATE")
    private Date expirationDate;

    @JoinColumn(name = "ACCOUNT_ID")
    private Account producer;

    /**
     * Instantiates a new Message.
     * @param content the content
     */
    public Message(final String content) {
        this(null, content, null);
    }

    /**
     * Instantiates a new Message.
     * @param content        the content
     * @param expirationDate the expiration date
     */
    public Message(final String content, final Date expirationDate) {
        this(null, content, expirationDate);
    }

    /**
     * Instantiates a new Message.
     * @param producer the producer
     * @param content  the content
     */
    public Message(final Account producer, final String content) {
        this(producer, content, null);
    }

    /**
     * Instantiates a new Message.
     * @param producer       the producer
     * @param content        the content
     * @param expirationDate the expiration date
     */
    public Message(final Account producer, final String content, final Date expirationDate) {
        this.producer = producer;
        this.content = content;
        if (expirationDate == null) {
            this.expirationDate = new Date();
            this.expirationDate.setMinutes(this.expirationDate.getMinutes()+30);
        } else {
            this.expirationDate = expirationDate;
        }
    }

    /**
     * Gets content.
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets content.
     * @param content the content
     */
    public void setContent(final String content) {
        this.content = content;
    }

    /**
     * Gets expiration date.
     * @return the expiration date
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets expiration date.
     * @param expirationDate the expiration date
     */
    public void setExpirationDate(final Date expirationDate) {
        this.expirationDate = expirationDate;
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
     * Gets producer.
     * @return the producer
     */
    public Account getProducer() {
        return producer;
    }

    /**
     * Sets producer.
     * @param producer the producer
     */
    public void setProducer(final Account producer) {
        this.producer = producer;
    }

    /**
     * Is active boolean.
     * @return the boolean
     */
    public boolean isActive() {
        return this.expirationDate.after(new Date());
    }
}
