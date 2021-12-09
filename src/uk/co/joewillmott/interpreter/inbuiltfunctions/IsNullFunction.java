package uk.co.joewillmott.interpreter.inbuiltfunctions;

import uk.co.joewillmott.ast.AST;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.interpreter.ReturnValue;
import uk.co.joewillmott.interpreter.symbols.VariableSymbol;

import java.util.ArrayList;

public class IsNullFunction extends InbuiltFunction {
    public IsNullFunction() {
        super("isnull");

        this.addParameter(new VariableSymbol("a"));
        this.setCustomFunction(IsNullFunction::run);
    }

    public static ReturnValue run(ArrayList<AST> arguments, CallStack callStack) throws UndefinedVariableException {
        Object returnValue;

        try {
            returnValue = callStack.findVar("a") == null;
        } catch (UndefinedVariableException ignored) {
            returnValue = true;
        }

        return new ReturnValue(returnValue);
    }
}
