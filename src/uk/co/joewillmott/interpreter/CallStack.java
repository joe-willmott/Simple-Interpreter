package uk.co.joewillmott.interpreter;

import uk.co.joewillmott.exceptions.UndefinedVariableException;

import java.util.Stack;

public class CallStack extends Stack<ActivationRecord> {
    public CallStack() {
        this.push(new ActivationRecord("GLOBALS", ARType.GLOBALS, 0));
    }

    public void setGlobal(String varName, Object value) {
        this.get(0).put(varName, value);
    }

    public Object findVar(String varName) throws UndefinedVariableException {
        Object varValue = this.peek().get(varName);

        if (varValue != null) {
            return varValue;
        }

        for (int i = this.size() - 2; i > 0; i--) {
            if (this.get(i).getType() == ARType.FUNCTION) {
                break;
            }

            varValue = this.get(i).get(varName);

            if (varValue != null) {
                return varValue;
            }
        }

        ActivationRecord globalScope = this.get(0);
        varValue = globalScope.get(varName);

        if (varValue != null) {
            return varValue;
        } else {
            throw new UndefinedVariableException(varName);
        }
    }
}
