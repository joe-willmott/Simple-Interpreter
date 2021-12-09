package uk.co.joewillmott.ast;

import uk.co.joewillmott.exceptions.InvalidFunctionCall;
import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedFunctionException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;

import java.util.ArrayList;

public class List extends AST {
    private final ArrayList<Object> contents = new ArrayList<>();

    public List() {
        super(null, null, null);
    }

    @Override
    public ArrayList<Object> evaluate(CallStack callStack) throws UndefinedVariableException, InvalidTypeException, InvalidFunctionCall, UndefinedFunctionException {
        return contents;
    }
}
