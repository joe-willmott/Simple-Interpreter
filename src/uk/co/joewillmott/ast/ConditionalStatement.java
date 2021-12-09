package uk.co.joewillmott.ast;

import uk.co.joewillmott.exceptions.InvalidFunctionCall;
import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedFunctionException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.interpreter.ReturnValue;

import java.lang.reflect.InvocationTargetException;

public class ConditionalStatement extends AST {
    private final AST condition;

    public ConditionalStatement(Block left, AST condition, Block right) {
        super(left, null, right);
        this.condition = condition;
    }

    @Override
    public ReturnValue evaluate(CallStack callStack) throws UndefinedVariableException, InvalidTypeException, InvalidFunctionCall, UndefinedFunctionException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        boolean condition = (boolean) this.condition.evaluate(callStack);

        Object returnValue;
        if (condition) {
            returnValue = this.getLeft().evaluate(callStack);
        } else {
            returnValue = this.getRight().evaluate(callStack);
        }

        return (returnValue instanceof ReturnValue) ? (ReturnValue) returnValue : null;
    }
}
