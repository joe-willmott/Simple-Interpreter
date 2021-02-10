package uk.co.joewillmott.ast;

import uk.co.joewillmott.exceptions.InvalidFunctionCall;
import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedFunctionException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.semanticanalyser.ScopedSymbolTable;

public class WhileLoop extends AST {
    private AST condition;

    public WhileLoop(AST left, AST condition) {
        super(left, null, null);
        this.condition = condition;
    }

    @Override
    public Object evaluate(CallStack callStack) throws UndefinedVariableException, InvalidTypeException, InvalidFunctionCall {
        Object returnValue = null;

        while ((boolean) this.condition.evaluate(callStack)) {
            returnValue = this.getLeft().evaluate(callStack);
        }

        return returnValue;
    }

    @Override
    public void visit(ScopedSymbolTable symbolTable) throws UndefinedVariableException, InvalidTypeException, UndefinedFunctionException {
        this.condition.visit(symbolTable);
        this.getLeft().visit(symbolTable);
    }
}
