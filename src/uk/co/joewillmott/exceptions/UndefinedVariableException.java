package uk.co.joewillmott.exceptions;

public class UndefinedVariableException extends Throwable {
    public UndefinedVariableException(String varName) {
        super(varName);
    }
}
