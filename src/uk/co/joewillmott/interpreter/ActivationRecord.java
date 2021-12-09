package uk.co.joewillmott.interpreter;

import java.util.HashMap;

public class ActivationRecord extends HashMap<String, Object> {
    private final String name;
    private final ARType type;

    public ActivationRecord(String name, ARType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public ARType getType() {
        return type;
    }

    public enum ARType {
        GLOBALS,
        FUNCTION
    }
}
