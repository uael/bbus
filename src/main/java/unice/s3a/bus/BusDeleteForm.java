package unice.s3a.bus;

import javax.validation.constraints.NotNull;

public class BusDeleteForm {

	private static final String NOT_BLANK_MESSAGE = "{notNull.message}";

    @NotNull(message = NOT_BLANK_MESSAGE)
	private Bus bus;

    public Bus getBus() {
		return bus;
	}

	public void setBus(final Bus bus) {
		this.bus = bus;
	}
}
