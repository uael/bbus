package unice.s3a.bus;

import javax.validation.constraints.NotNull;

/**
 * The type Bus delete form.
 */
public class BusDeleteForm {
    private static final String NOT_BLANK_MESSAGE = "{notNull.message}";
    @NotNull(message = NOT_BLANK_MESSAGE) private Bus bus;

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
}
