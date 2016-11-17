package unice.s3a.support.web;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static unice.s3a.support.web.Message.MESSAGE_ATTRIBUTE;

/**
 * The type Message helper.
 */
public final class MessageHelper {
    private MessageHelper() {
    }

    private static void addAttribute(RedirectAttributes ra, String message, Message.Type type, Object... args) {
        ra.addFlashAttribute(MESSAGE_ATTRIBUTE, new Message(message, type, args));
    }

    private static void addAttribute(Model model, String message, Message.Type type, Object... args) {
        model.addAttribute(MESSAGE_ATTRIBUTE, new Message(message, type, args));
    }

    /**
     * Add error attribute.
     * @param ra      the ra
     * @param message the message
     * @param args    the args
     */
    public static void addErrorAttribute(RedirectAttributes ra, String message, Object... args) {
        addAttribute(ra, message, Message.Type.DANGER, args);
    }

    /**
     * Add error attribute.
     * @param model   the model
     * @param message the message
     * @param args    the args
     */
    public static void addErrorAttribute(Model model, String message, Object... args) {
        addAttribute(model, message, Message.Type.DANGER, args);
    }

    /**
     * Add info attribute.
     * @param ra      the ra
     * @param message the message
     * @param args    the args
     */
    public static void addInfoAttribute(RedirectAttributes ra, String message, Object... args) {
        addAttribute(ra, message, Message.Type.INFO, args);
    }

    /**
     * Add info attribute.
     * @param model   the model
     * @param message the message
     * @param args    the args
     */
    public static void addInfoAttribute(Model model, String message, Object... args) {
        addAttribute(model, message, Message.Type.INFO, args);
    }

    /**
     * Add success attribute.
     * @param ra      the ra
     * @param message the message
     * @param args    the args
     */
    public static void addSuccessAttribute(RedirectAttributes ra, String message, Object... args) {
        addAttribute(ra, message, Message.Type.SUCCESS, args);
    }

    /**
     * Add success attribute.
     * @param model   the model
     * @param message the message
     * @param args    the args
     */
    public static void addSuccessAttribute(Model model, String message, Object... args) {
        addAttribute(model, message, Message.Type.SUCCESS, args);
    }

    /**
     * Add warning attribute.
     * @param ra      the ra
     * @param message the message
     * @param args    the args
     */
    public static void addWarningAttribute(RedirectAttributes ra, String message, Object... args) {
        addAttribute(ra, message, Message.Type.WARNING, args);
    }

    /**
     * Add warning attribute.
     * @param model   the model
     * @param message the message
     * @param args    the args
     */
    public static void addWarningAttribute(Model model, String message, Object... args) {
        addAttribute(model, message, Message.Type.WARNING, args);
    }
}
