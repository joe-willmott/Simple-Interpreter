package uk.co.joewillmott.ast;

import uk.co.joewillmott.exceptions.InvalidFunctionCall;
import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedFunctionException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.interpreter.ReturnValue;

import java.lang.reflect.InvocationTargetException;

public class Program extends AST {
    private final Block block;

    public Program(Block block) {
        super(null, null, null);
        this.block = block;
    }

    @Override
    public ReturnValue evaluate(CallStack callStack) throws UndefinedVariableException, InvalidTypeException, InvalidFunctionCall, UndefinedFunctionException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return (ReturnValue) this.block.evaluate(callStack);
    }
}
