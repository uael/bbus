package unice.s3a.box;

import unice.s3a.bus.Bus;

import javax.validation.constraints.NotNull;

public class BoxDeleteForm {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

    @NotNull(message = NOT_BLANK_MESSAGE)
	private Box box;

    public Box getBox() {
		return box;
	}

	public void setBox(final Box box) {
		this.box = box;
	}

	@NotNull(message = NOT_BLANK_MESSAGE)
	private Bus bus;

	public Bus getBus() {
		return bus;
	}

	public void setBus(final Bus bus) {
		this.bus = bus;
	}
}
