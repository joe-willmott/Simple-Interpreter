package uk.co.joewillmott.interpreter.inbuiltfunctions;

import uk.co.joewillmott.ast.AST;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.semanticanalyser.symbol.VariableSymbol;

import java.util.ArrayList;

public class ErrorFunction extends InbuiltFunction {
    public ErrorFunction() {
        super("error");

        this.addParameter(new VariableSymbol("a"));
        this.setCustomFunction(ErrorFunction::run);
    }

    public static Object run(ArrayList<AST> arguments, CallStack callStack) throws UndefinedVariableException {
        Object argumentValue = callStack.findVar("a");

        throw new RuntimeException(argumentValue.toString());
    }
}
