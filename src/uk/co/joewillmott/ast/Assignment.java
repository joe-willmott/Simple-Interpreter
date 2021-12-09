package uk.co.joewillmott.ast;

import uk.co.joewillmott.exceptions.InvalidFunctionCall;
import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedFunctionException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.ActivationRecord;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.lexer.Token;

import java.lang.reflect.InvocationTargetException;

public class Assignment extends AST {
    public Assignment(AST left, Token token, AST right) {
        super(left, token, right);
    }

    @Override
    public Object evaluate(CallStack callStack) throws UndefinedVariableException, InvalidTypeException, InvalidFunctionCall, UndefinedFunctionException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String varName = this.getLeft().getValue();

        Object varValue = this.getRight().evaluate(callStack);

        ActivationRecord activationRecord = callStack.peek();

        activationRecord.put(varName, varValue);

        return null;
    }
}
