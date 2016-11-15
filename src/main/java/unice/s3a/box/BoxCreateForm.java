package unice.s3a.box;

import org.hibernate.validator.constraints.NotBlank;
import unice.s3a.bus.Bus;

import javax.validation.constraints.NotNull;

public class BoxCreateForm {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

    @NotBlank(message = NOT_BLANK_MESSAGE)
	private String name;

    public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotNull(message = NOT_BLANK_MESSAGE)
	private Bus bus;

	public Bus getBus() {
		return bus;
	}

	public void setBus(final Bus bus) {
		this.bus = bus;
	}

	public Box createBox() {
        return this.getBus().addBox(this.getName());
	}
}
