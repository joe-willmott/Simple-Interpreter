package uk.co.joewillmott.ast;

import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.lexer.Token;
import uk.co.joewillmott.lexer.TokenType;
import uk.co.joewillmott.semanticanalyser.ScopedSymbolTable;

public class NumberConstant extends AST {
    public NumberConstant(Token token) {
        super(null, token, null);
    }

    @Override
    public Number evaluate(CallStack callStack) {
        String value = this.getToken().getValue();

        if (this.getToken().getType() == TokenType.FLOAT) {
            return Float.parseFloat(value);
        } else {
            return Integer.parseInt(value);
        }

        // For some odd reason this didn't work but ^ did. :shrug:
//        return (this.getToken().getType() == TokenType.FLOAT) ? Float.parseFloat(value) : Integer.parseInt(value);
    }

    @Override
    public void visit(ScopedSymbolTable symbolTable) {
    }
}
