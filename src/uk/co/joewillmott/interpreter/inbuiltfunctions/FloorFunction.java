package uk.co.joewillmott.interpreter.inbuiltfunctions;

import uk.co.joewillmott.ast.AST;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.semanticanalyser.symbol.VariableSymbol;

import java.util.ArrayList;

public class FloorFunction extends InbuiltFunction {
    public FloorFunction() {
        super("floor");

        this.addParameter(new VariableSymbol("a"));
        this.setCustomFunction(FloorFunction::run);
    }

    public static int run(ArrayList<AST> arguments, CallStack callStack) throws UndefinedVariableException {
        Object argumentValue = callStack.findVar("a");

        return Math.round((Float) argumentValue);
    }
}
