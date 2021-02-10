package uk.co.joewillmott.interpreter.inbuiltfunctions;

import uk.co.joewillmott.ast.AST;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.semanticanalyser.symbol.VariableSymbol;

import java.util.ArrayList;

public class ToIntFunction extends InbuiltFunction {
    public ToIntFunction() {
        super("toint");

        this.addParameter(new VariableSymbol("a"));
        this.setCustomFunction(ToIntFunction::run);
    }

    public static Integer run(ArrayList<AST> arguments, CallStack callStack) throws UndefinedVariableException {
        Object argumentValue = callStack.findVar("a");

        if (argumentValue instanceof Float) {
            return ((Float) argumentValue).intValue();
        }

        return Integer.parseInt(argumentValue.toString());
    }
}
