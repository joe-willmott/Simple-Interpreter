package uk.co.joewillmott.ast;

import uk.co.joewillmott.exceptions.InvalidFunctionCall;
import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedFunctionException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.lexer.Token;
import uk.co.joewillmott.lexer.TokenType;

import java.lang.reflect.InvocationTargetException;

public class UnaryOperation extends AST {
    public UnaryOperation(Token token, AST right) {
        super(null, token, right);
    }

    @Override
    public Object evaluate(CallStack callStack) throws UndefinedVariableException, InvalidTypeException, InvalidFunctionCall, UndefinedFunctionException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        switch (this.getToken().getType()) {
            case SUB:
                return new BinaryOperation(new NumberConstant(new Token(TokenType.INT, "-1")), new Token(TokenType.MUL, "*"), this.getRight()).evaluate(callStack);
            case ADD:
                return this.getRight().evaluate(callStack);
            case NEGATE:
                return !(Boolean) this.getRight().evaluate(callStack);
        }

        return null;
    }
}
