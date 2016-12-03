package unice.s3a.bus;

import org.hibernate.validator.constraints.NotBlank;

/**
 * The type Bus delete form.
 */
public class BusDeleteForm {
    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
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
}
