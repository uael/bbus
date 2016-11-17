package unice.s3a.message;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import unice.s3a.box.Box;
import unice.s3a.bus.Bus;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * The type Message emit form.
 */
public class MessageEmitForm {
    private static final String NOT_EMPTY_MESSAGE = "{notEmpty.message}";
    @NotNull(message = NOT_EMPTY_MESSAGE) private Bus bus;
    @NotNull(message = NOT_EMPTY_MESSAGE) private Box box;
    @NotEmpty(message = NOT_EMPTY_MESSAGE) private String content;
    @DateTimeFormat(pattern = "dd-MMM-YYYY") private Date date;

    /**
     * Create message message.
     * @return the message
     */
    public Message createMessage() {
        return new Message(content, date);
    }

    /**
     * Gets box.
     * @return the box
     */
    public Box getBox() {
        return box;
    }

    /**
     * Sets box.
     * @param box the box
     */
    public void setBox(final Box box) {
        this.box = box;
    }

    /**
     * Gets bus.
     * @return the bus
     */
    public Bus getBus() {
        return bus;
    }

    /**
     * Sets bus.
     * @param bus the bus
     */
    public void setBus(final Bus bus) {
        this.bus = bus;
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
     * Gets date.
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date.
     * @param date the date
     */
    public void setDate(final Date date) {
        this.date = date;
    }
}
