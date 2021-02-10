package uk.co.joewillmott.interpreter.inbuiltfunctions;

import uk.co.joewillmott.ast.AST;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.semanticanalyser.symbol.VariableSymbol;

import java.util.ArrayList;

public class ToBoolFunction extends InbuiltFunction {
    public ToBoolFunction() {
        super("tobool");

        this.addParameter(new VariableSymbol("a"));
        this.setCustomFunction(ToBoolFunction::run);
    }

    public static Boolean run(ArrayList<AST> arguments, CallStack callStack) throws UndefinedVariableException {
        Object argumentValue = callStack.findVar("a");

        return Boolean.parseBoolean(argumentValue.toString());
    }
}