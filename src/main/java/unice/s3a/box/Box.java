package unice.s3a.box;

import unice.s3a.account.Account;
import unice.s3a.message.Message;

import java.util.ArrayList;
import java.util.Date;

/**
 * The type Box.
 */
public class Box extends ArrayList<Message> {
    /**
     * Emit boolean.
     * @param message the message
     * @return the boolean
     */
    public boolean emit(final String message) {
        return super.add(new Message(message));
    }

    /**
     * Emit boolean.
     * @param message        the message
     * @param expirationDate the expiration date
     * @return the boolean
     */
    public boolean emit(final String message, final Date expirationDate) {
        return super.add(new Message(message, expirationDate));
    }

    /**
     * Emit boolean.
     * @param producer the producer
     * @param content  the content
     * @return the boolean
     */
    public boolean emit(Account producer, String content) {
        return super.add(new Message(producer, content));
    }

    /**
     * Emit boolean.
     * @param producer       the producer
     * @param content        the content
     * @param expirationDate the expiration date
     * @return the boolean
     */
    public boolean emit(Account producer, String content, final Date expirationDate) {
        return super.add(new Message(producer, content, expirationDate));
    }
}
