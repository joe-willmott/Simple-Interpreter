package uk.co.joewillmott.interpreter.inbuiltfunctions;

import uk.co.joewillmott.ast.AST;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.semanticanalyser.symbol.VariableSymbol;

import java.util.ArrayList;

public class ToFloatFunction extends InbuiltFunction {
    public ToFloatFunction() {
        super("tofloat");

        this.addParameter(new VariableSymbol("a"));
        this.setCustomFunction(ToFloatFunction::run);
    }

    public static Float run(ArrayList<AST> arguments, CallStack callStack) throws UndefinedVariableException {
        Object argumentValue = callStack.findVar("a");

        return Float.parseFloat(argumentValue.toString());
    }
}
