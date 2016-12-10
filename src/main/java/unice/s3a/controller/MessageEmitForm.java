package unice.s3a.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * The type Message emit form.
 */
public class MessageEmitForm {
    private static final String NOT_EMPTY_MESSAGE = "{notBlank.message}";
    @NotBlank(message = NOT_EMPTY_MESSAGE)
    private String bus;
    @NotBlank(message = NOT_EMPTY_MESSAGE)
    private String box;
    @NotBlank(message = NOT_EMPTY_MESSAGE)
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd H:m:s")
    private LocalDateTime date;

    /**
     * Gets box.
     * @return the box
     */
    public String getBox() {
        return box;
    }

    /**
     * Sets box.
     * @param box the box
     */
    public void setBox(final String box) {
        this.box = box;
    }

    /**
     * Gets bus.
     * @return the bus
     */
    public String getBus() {
        return bus;
    }

    /**
     * Sets bus.
     * @param bus the bus
     */
    public void setBus(final String bus) {
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
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets date.
     * @param date the date
     */
    public void setDate(final LocalDateTime date) {
        this.date = date;
    }
}
