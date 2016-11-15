package unice.s3a.message;

import org.hibernate.validator.constraints.NotEmpty;
import unice.s3a.account.Account;
import unice.s3a.bus.Bus;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class MessageCreateForm {

	private static final String NOT_EMPTY_MESSAGE = "{notEmpty.message}";
	private static final String NOT_NULL_ACCOUNT = "{notNull.message}";

    @NotEmpty(message = NOT_EMPTY_MESSAGE)
	private String content;

    private Date expirationDate;

	@NotNull(message = NOT_NULL_ACCOUNT)
	private Account producer;

	public static String getNotNullAccount() {
		return NOT_NULL_ACCOUNT;
	}

	public Account getProducer() {
		return producer;
	}

	public String getContent() {
		return content;
	}

	public Date getDate(){
    	return expirationDate;
	}

	public void setName(final String content) {
		this.content = content;
	}

	public void setProducer(Account producer) {
		this.producer = producer;
	}

	public void setDate(final Date date){
		this.expirationDate = date;
	}

	public Message createMessage() {
        return new Message(getProducer(), getContent(), getDate());
	}
}
