package uk.co.joewillmott.ast;

import uk.co.joewillmott.interpreter.CallStack;

public class NoOperation extends AST {
    public NoOperation() {
        super(null, null, null);
    }

    @Override
    public Object evaluate(CallStack callStack) {
        return null;
    }
}
