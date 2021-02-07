package uk.co.joewillmott.ast;

import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedFunctionException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.semanticanalyser.ScopedSymbolTable;

public class Program extends AST {
    private Block block;

    public Program(Block block) {
        super(null, null, null);
        this.block = block;
    }

    @Override
    public Object evaluate(CallStack callStack) throws UndefinedVariableException, InvalidTypeException {
        this.block.evaluate(callStack);

        return null;
    }

    @Override
    public void visit(ScopedSymbolTable symbolTable) throws UndefinedVariableException, InvalidTypeException, UndefinedFunctionException {
        this.block.visit(symbolTable);
    }
}
