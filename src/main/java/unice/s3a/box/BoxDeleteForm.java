package unice.s3a.box;

import unice.s3a.bus.Bus;

import javax.validation.constraints.NotNull;

/**
 * The type Box delete form.
 */
public class BoxDeleteForm {
    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
    @NotNull(message = NOT_BLANK_MESSAGE)
    private Box box;
    @NotNull(message = NOT_BLANK_MESSAGE)
    private Bus bus;

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
}
