package uk.co.joewillmott.ast;

import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedFunctionException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.lexer.Token;
import uk.co.joewillmott.semanticanalyser.ScopedSymbolTable;

public abstract class AST {
    private AST left;
    private Token token;
    private AST right;

    public AST(AST left, Token token, AST right) {
        this.left = left;
        this.token = token;
        this.right = right;
    }

    public Token getToken() {
        return token;
    }

    public String getValue() {
        return token.getValue();
    }

    public AST getLeft() {
        return left;
    }

    public AST getRight() {
        return right;
    }

    public abstract Object evaluate(CallStack callStack) throws UndefinedVariableException, InvalidTypeException;

    public abstract void visit(ScopedSymbolTable symbolTable) throws UndefinedVariableException, InvalidTypeException, UndefinedFunctionException;
}
