package uk.co.joewillmott.ast;

import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.lexer.Token;

public class Variable extends AST {
    public Variable(Token token) {
        super(null, token, null);
    }

    @Override
    public Object evaluate(CallStack callStack) throws UndefinedVariableException {
        String varName = this.getValue();

        return callStack.findVar(varName);
    }
}
