package uk.co.joewillmott.exceptions;

public class UndefinedFunctionException extends Throwable {
    public UndefinedFunctionException(String name) {
        super(name);
    }
}
