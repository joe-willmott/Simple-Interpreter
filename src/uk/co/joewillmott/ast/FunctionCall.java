package uk.co.joewillmott.ast;

import uk.co.joewillmott.exceptions.InvalidFunctionCall;
import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedFunctionException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.ActivationRecord;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.interpreter.ReturnValue;
import uk.co.joewillmott.interpreter.symbols.FunctionSymbol;
import uk.co.joewillmott.interpreter.symbols.VariableSymbol;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class FunctionCall extends AST {
    private final String name;
    private final ArrayList<AST> arguments;

    public FunctionCall(String name, ArrayList<AST> arguments) {
        super(null, null, null);
        this.name = name;
        this.arguments = arguments;
    }

    public String getName() {
        return name;
    }

    public ArrayList<AST> getArguments() {
        return arguments;
    }

    @Override
    public Object evaluate(CallStack callStack) throws UndefinedVariableException, InvalidTypeException, InvalidFunctionCall, UndefinedFunctionException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ActivationRecord activationRecord = new ActivationRecord(this.name, ActivationRecord.ARType.FUNCTION);

        Object result = callStack.findVar(this.name);

        if (!(result instanceof FunctionSymbol)) {
            throw new UndefinedFunctionException(String.format("%s is not a callable function.", this.name));
        }

        FunctionSymbol functionSymbol = (FunctionSymbol) result;

        ArrayList<VariableSymbol> parameters = functionSymbol.getParameters();

        if (parameters.size() > this.arguments.size()) {
            throw new InvalidFunctionCall(String.format("Function %s expects %d arguments. Got %d instead.", this.getName(), parameters.size(), this.arguments.size()));
        }

        for (int i = 0; i < parameters.size(); i++) {
            activationRecord.put(parameters.get(i).getName(), this.arguments.get(i).evaluate(callStack));
        }

        callStack.push(activationRecord);

        Object returnValue;

        if (functionSymbol.getCustomFunction() != null) {
            returnValue = functionSymbol.getCustomFunction().run(arguments, callStack);
        } else {
            returnValue = functionSymbol.getBlock().evaluate(callStack);
        }

        callStack.pop();

        return (returnValue instanceof ReturnValue) ? ((ReturnValue) returnValue).getValue() : null;
    }
}
