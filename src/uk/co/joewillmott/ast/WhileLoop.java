package uk.co.joewillmott.ast;

import uk.co.joewillmott.exceptions.InvalidFunctionCall;
import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedFunctionException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.interpreter.ReturnValue;

import java.lang.reflect.InvocationTargetException;

public class WhileLoop extends AST {
    private final AST condition;

    public WhileLoop(AST left, AST condition) {
        super(left, null, null);
        this.condition = condition;
    }

    @Override
    public ReturnValue evaluate(CallStack callStack) throws UndefinedVariableException, InvalidTypeException, InvalidFunctionCall, UndefinedFunctionException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object returnValue;

        while ((boolean) this.condition.evaluate(callStack)) {
            returnValue = this.getLeft().evaluate(callStack);

            if (returnValue != null) {
                return new ReturnValue(returnValue);
            }
        }

        return null;
    }
}
