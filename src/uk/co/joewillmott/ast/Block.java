package uk.co.joewillmott.ast;

import uk.co.joewillmott.exceptions.InvalidFunctionCall;
import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedFunctionException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.interpreter.ReturnValue;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Block extends AST {
    private final ArrayList<AST> statements;

    public Block() {
        super(null, null, null);

        this.statements = new ArrayList<>();
    }

    public void addStatement(AST statement) {
        this.statements.add(statement);
    }

    @Override
    public Object evaluate(CallStack callStack) throws UndefinedVariableException, InvalidTypeException, InvalidFunctionCall, UndefinedFunctionException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        for (AST statement : this.statements) {
            Object result = statement.evaluate(callStack);

            if (result instanceof ReturnValue) {
                return result;
            }
        }

        return null;
    }
}
