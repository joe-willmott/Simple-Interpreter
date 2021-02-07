package uk.co.joewillmott.interpreter.inbuiltfunctions;

import uk.co.joewillmott.ast.AST;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.semanticanalyser.symbol.VariableSymbol;

import java.util.ArrayList;

public class Print extends InbuiltFunction {
    public Print() {
        super("print");

        this.addParameter(new VariableSymbol("a"));
        this.setCustomFunction(Print::run);
    }

    public static Object run(ArrayList<AST> arguments, CallStack callStack) throws UndefinedVariableException {
        Object argumentValue = callStack.findVar("a");

        System.out.print(argumentValue);

        return null;
    }
}
