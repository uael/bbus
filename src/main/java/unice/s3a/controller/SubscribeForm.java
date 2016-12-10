package unice.s3a.controller;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The type Subscribe form.
 */
public class SubscribeForm {
    private static final String NOT_BLANK_MESSAGE = "{notNull.message}";
    @NotEmpty(message = NOT_BLANK_MESSAGE)
    private String bus;

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
}
