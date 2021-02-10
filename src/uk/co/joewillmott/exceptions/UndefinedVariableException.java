package uk.co.joewillmott.exceptions;

public class UndefinedVariableException extends Throwable {
    public UndefinedVariableException(String name) {
        super(name);
    }
}
