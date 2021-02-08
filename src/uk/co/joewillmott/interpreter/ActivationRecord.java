package uk.co.joewillmott.interpreter;

import java.util.HashMap;

public class ActivationRecord extends HashMap<String, Object> {
    private String name;
    private ARType type;
    private int nesting_level;

    public ActivationRecord(String name, ARType type, int nesting_level) {
        this.name = name;
        this.type = type;
        this.nesting_level = nesting_level;
    }

    public String getName() {
        return name;
    }

    public ARType getType() {
        return type;
    }
}
