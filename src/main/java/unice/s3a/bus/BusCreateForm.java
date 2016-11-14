package unice.s3a.bus;

import org.hibernate.validator.constraints.NotBlank;

public class BusCreateForm {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

    @NotBlank(message = NOT_BLANK_MESSAGE)
	private String name;

    public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Bus createBus() {
        return new Bus(getName());
	}
}
