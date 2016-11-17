package unice.s3a.bus;

import org.hibernate.validator.constraints.NotBlank;

/**
 * The type Bus create form.
 */
public class BusCreateForm {
    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
    @NotBlank(message = NOT_BLANK_MESSAGE)
    private String name;

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
