package uk.co.joewillmott.ast;

import uk.co.joewillmott.exceptions.InvalidFunctionCall;
import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedFunctionException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.lexer.Token;

import java.lang.reflect.InvocationTargetException;

public abstract class AST {
    private final AST left;
    private final Token token;
    private final AST right;

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

    public abstract Object evaluate(CallStack callStack) throws UndefinedVariableException, InvalidTypeException, InvalidFunctionCall, UndefinedFunctionException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
