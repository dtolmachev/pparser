package ru.tolmachev;

/**
 * Created by sunlight on 01.09.14.
 */
public class ParsingException extends Exception {
    public ParsingException() {
    }

    public ParsingException(String message) {
        super(message);
    }

    public ParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParsingException(Throwable cause) {
        super(cause);
    }
}
