package uk.co.joewillmott.ast;

import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.lexer.Token;
import uk.co.joewillmott.semanticanalyser.ScopedSymbolTable;

public class UnaryOperation extends AST {
    public UnaryOperation(Token token, AST right) {
        super(null, token, right);
    }

    @Override
    public Object evaluate(CallStack callStack) throws UndefinedVariableException, InvalidTypeException {
        switch (this.getToken().getType()) {
            case SUB:
                return -(Integer) this.getRight().evaluate(callStack);
            case NEGATE:
                return !(Boolean) this.getRight().evaluate(callStack);
        }

        return null;
    }

    @Override
    public void visit(ScopedSymbolTable symbolTable) {
    }
}
