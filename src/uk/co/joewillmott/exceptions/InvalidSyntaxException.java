package uk.co.joewillmott.exceptions;

public class InvalidSyntaxException extends Throwable {
    public InvalidSyntaxException(String format) {
        super(format);
    }
}
