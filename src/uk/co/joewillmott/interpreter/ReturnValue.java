package uk.co.joewillmott.interpreter;

public class ReturnValue {
    private final Object value;

    public ReturnValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
