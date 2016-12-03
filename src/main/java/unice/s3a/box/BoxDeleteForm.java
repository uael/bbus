package unice.s3a.box;

import org.hibernate.validator.constraints.NotBlank;

/**
 * The type Box delete form.
 */
public class BoxDeleteForm {
    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
    @NotBlank(message = NOT_BLANK_MESSAGE)
    private String box;
    @NotBlank(message = NOT_BLANK_MESSAGE)
    private String bus;

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
}
