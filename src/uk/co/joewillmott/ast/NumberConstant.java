package uk.co.joewillmott.ast;

import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.lexer.Token;
import uk.co.joewillmott.lexer.TokenType;

public class NumberConstant extends AST {
    public NumberConstant(Token token) {
        super(null, token, null);
    }

    @Override
    public Number evaluate(CallStack callStack) {
        String value = this.getToken().getValue();

        if (this.getToken().getType() == TokenType.Double) {
            return Double.parseDouble(value);
        } else {
            return Long.parseLong(value);
        }

        // For some odd reason this didn't work but ^ did. :shrug:
//        return (this.getToken().getType() == TokenType.Double) ? Double.parseDouble(value) : Long.parseLong(value);
    }
}
