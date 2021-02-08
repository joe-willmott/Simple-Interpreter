package uk.co.joewillmott.interpreter.inbuiltfunctions;

import uk.co.joewillmott.ast.AST;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.semanticanalyser.symbol.VariableSymbol;

import java.util.ArrayList;

public class PrintlnFunction extends InbuiltFunction {
    public PrintlnFunction() {
        super("println");

        this.addParameter(new VariableSymbol("a"));
        this.setCustomFunction(PrintlnFunction::run);
    }

    public static Object run(ArrayList<AST> arguments, CallStack callStack) throws UndefinedVariableException {
        Object argumentValue = callStack.findVar("a");

        System.out.println(argumentValue);

        return null;
    }
}
