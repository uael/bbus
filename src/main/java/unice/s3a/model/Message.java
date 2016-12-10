package unice.s3a.model;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * The type unice.s3a.model.Message.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "MESSAGE")
public class Message implements java.io.Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
    private Instant expirationDate;
    @ManyToOne
    private Account producer;
    private Instant created;

    /**
     * Instantiates a new Message.
     */
    public Message() { }

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
    public Message(final String content, final LocalDateTime expirationDate) {
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
    public Message(final Account producer, final String content, final LocalDateTime expirationDate) {
        this.producer = producer;
        this.content = content;
        this.created = Instant.now();
        if (expirationDate == null) {
            this.expirationDate = this.created.plusSeconds(60*30);
        } else {
            this.expirationDate = expirationDate.toInstant(ZoneOffset.UTC);
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
    public Instant getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets expiration date.
     * @param expirationDate the expiration date
     */
    public void setExpirationDate(final Instant expirationDate) {
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
        return this.expirationDate.isAfter(Instant.now());
    }

    /**
     * Gets header.
     * @return the header
     */
    public String getHeader() {
        return this.created.toString()+(this.producer != null ? " @"+this.producer.getEmail() : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return id != null
            ? id.equals(message.id)
            : (message.content != null
                ? message.content.equals(this.content)
                : this.content == null);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
