package uk.co.joewillmott.interpreter.inbuiltfunctions;

import uk.co.joewillmott.ast.AST;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.interpreter.ReturnValue;
import uk.co.joewillmott.interpreter.symbols.VariableSymbol;

import java.util.ArrayList;

public class TypeFunction extends InbuiltFunction {
    public TypeFunction() {
        super("type");

        this.addParameter(new VariableSymbol("a"));
        this.setCustomFunction(TypeFunction::run);
    }

    public static ReturnValue run(ArrayList<AST> arguments, CallStack callStack) throws UndefinedVariableException {
        Object argumentValue = callStack.findVar("a");

        String className = argumentValue.getClass().getSimpleName();

        return new ReturnValue((className.equals("Long")) ? "int" : className.toLowerCase());
    }
}
