package unice.s3a.support.web;

/**
 * A message to be displayed in web context. Depending on the type, different style will be applied.
 */
public class Message implements java.io.Serializable {
    /**
     * Name of the flash attribute.
     */
    public static final String MESSAGE_ATTRIBUTE = "message";
    private final Object[] args;
    private final String message;
    private final Type type;

    /**
     * Instantiates a new Message.
     * @param message the message
     * @param type    the type
     */
    public Message(String message, Type type) {
        this.message = message;
        this.type = type;
        this.args = null;
    }

    /**
     * Instantiates a new Message.
     * @param message the message
     * @param type    the type
     * @param args    the args
     */
    public Message(String message, Type type, Object... args) {
        this.message = message;
        this.type = type;
        this.args = args;
    }

    /**
     * Get args object [ ].
     * @return the object [ ]
     */
    public Object[] getArgs() {
        return args;
    }

    /**
     * Gets message.
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets type.
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * The type of the message to be displayed. The type is used to show message in a different style.
     */
    public static enum Type {
        /**
         * Danger type.
         */
        DANGER, /**
         * Warning type.
         */
        WARNING, /**
         * Info type.
         */
        INFO, /**
         * Success type.
         */
        SUCCESS;
    }
}
