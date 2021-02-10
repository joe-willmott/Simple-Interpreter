package uk.co.joewillmott.interpreter.inbuiltfunctions;

import uk.co.joewillmott.ast.AST;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.semanticanalyser.symbol.VariableSymbol;

import java.util.ArrayList;

public class ToStringFunction extends InbuiltFunction {
    public ToStringFunction() {
        super("tostring");

        this.addParameter(new VariableSymbol("a"));
        this.setCustomFunction(ToStringFunction::run);
    }

    public static String run(ArrayList<AST> arguments, CallStack callStack) throws UndefinedVariableException {
        Object argumentValue = callStack.findVar("a");

        return argumentValue.toString();
    }
}
