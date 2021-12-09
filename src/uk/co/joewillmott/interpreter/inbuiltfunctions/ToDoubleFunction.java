package uk.co.joewillmott.interpreter.inbuiltfunctions;

import uk.co.joewillmott.ast.AST;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.interpreter.ReturnValue;
import uk.co.joewillmott.interpreter.symbols.VariableSymbol;

import java.util.ArrayList;

public class ToDoubleFunction extends InbuiltFunction {
    public ToDoubleFunction() {
        super("todouble");

        this.addParameter(new VariableSymbol("a"));
        this.setCustomFunction(ToDoubleFunction::run);
    }

    public static ReturnValue run(ArrayList<AST> arguments, CallStack callStack) throws UndefinedVariableException {
        Object argumentValue = callStack.findVar("a");

        return new ReturnValue(Double.parseDouble(argumentValue.toString()));
    }
}
