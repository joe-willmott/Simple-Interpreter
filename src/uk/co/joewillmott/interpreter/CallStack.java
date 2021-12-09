package uk.co.joewillmott.interpreter;

import uk.co.joewillmott.exceptions.UndefinedVariableException;

import java.util.Stack;

public class CallStack extends Stack<ActivationRecord> {
    public CallStack() {
        this.push(new ActivationRecord("GLOBALS", ActivationRecord.ARType.GLOBALS));
    }

    public Object findVar(String varName) throws UndefinedVariableException {
        ActivationRecord topScope = this.peek();

        if (topScope.containsKey(varName)) {
            return topScope.get(varName);
        }

        for (int i = this.size() - 2; i > 0; i--) {
            if (this.get(i).getType() == ActivationRecord.ARType.FUNCTION) {
                break;
            }

            ActivationRecord scope = this.get(i);

            if (scope.containsKey(varName)) {
                return scope.get(varName);
            }
        }

        ActivationRecord globalScope = this.get(0);

        if (!globalScope.containsKey(varName)) {
            throw new UndefinedVariableException(String.format("Undefined variable: %s", varName));
        }

        return globalScope.get(varName);
    }
}
