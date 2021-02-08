package uk.co.joewillmott.ast;

import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedFunctionException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.lexer.Token;
import uk.co.joewillmott.semanticanalyser.ScopedSymbolTable;

public class BinaryOperation extends AST {
    public BinaryOperation(AST left, Token token, AST right) {
        super(left, token, right);
    }

    @Override
    public Object evaluate(CallStack callStack) throws UndefinedVariableException, InvalidTypeException {
        switch (this.getToken().getType()) {
            case ADD:
                return (float) this.getLeft().evaluate(callStack) + (float) this.getRight().evaluate(callStack);
            case SUB:
                return (float) this.getLeft().evaluate(callStack) - (float) this.getRight().evaluate(callStack);
            case MUL:
                return (float) this.getLeft().evaluate(callStack) * (float) this.getRight().evaluate(callStack);
            case DIV:
                return (float) this.getLeft().evaluate(callStack) / (float) this.getRight().evaluate(callStack);
            case INT_DIV:
                return (int) Math.floor((float) this.getLeft().evaluate(callStack) / (float) this.getRight().evaluate(callStack));
            case OR:
                return (boolean) this.getLeft().evaluate(callStack) || (boolean) this.getRight().evaluate(callStack);
            case AND:
                return (boolean) this.getLeft().evaluate(callStack) && (boolean) this.getRight().evaluate(callStack);
            case GREATER_THAN:
                return (float) this.getLeft().evaluate(callStack) > (float) this.getRight().evaluate(callStack);
            case LESS_THAN:
                return (float) this.getLeft().evaluate(callStack) < (float) this.getRight().evaluate(callStack);
            case CONCAT:
                return String.format("%s%s", this.getLeft().evaluate(callStack), this.getRight().evaluate(callStack));
        }

        return null;
    }

    @Override
    public void visit(ScopedSymbolTable symbolTable) throws UndefinedVariableException, InvalidTypeException, UndefinedFunctionException {
        this.getLeft().visit(symbolTable);
        this.getRight().visit(symbolTable);
    }
}
