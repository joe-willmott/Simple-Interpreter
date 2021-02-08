package uk.co.joewillmott.ast;

import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedFunctionException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.semanticanalyser.ScopedSymbolTable;

public class ConditionalStatement extends AST {
    private AST condition;

    public ConditionalStatement(Block left, AST condition, Block right) {
        super(left, null, right);
        this.condition = condition;
    }

    @Override
    public Object evaluate(CallStack callStack) throws UndefinedVariableException, InvalidTypeException {
        boolean condition = (boolean) this.condition.evaluate(callStack);

        if (condition) {
            return this.getLeft().evaluate(callStack);
        } else {
            return this.getRight().evaluate(callStack);
        }
    }

    @Override
    public void visit(ScopedSymbolTable symbolTable) throws UndefinedVariableException, InvalidTypeException, UndefinedFunctionException {
        this.condition.visit(symbolTable);
        this.getLeft().visit(symbolTable);
        this.getRight().visit(symbolTable);
    }
}
