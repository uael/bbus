package unice.s3a.controller;

import org.hibernate.validator.constraints.NotBlank;

/**
 * The type Box create form.
 */
public class BoxCreateForm {
    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
    @NotBlank(message = NOT_BLANK_MESSAGE)
    private String name;
    @NotBlank(message = NOT_BLANK_MESSAGE)
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
