package unice.s3a.box;

import org.hibernate.validator.constraints.NotBlank;
import unice.s3a.bus.Bus;

import javax.validation.constraints.NotNull;

/**
 * The type Box create form.
 */
public class BoxCreateForm {
    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
    @NotBlank(message = NOT_BLANK_MESSAGE) private String name;
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
