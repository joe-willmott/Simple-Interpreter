package uk.co.joewillmott.ast;

import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.lexer.Token;
import uk.co.joewillmott.semanticanalyser.ScopedSymbolTable;

public class StringConstant extends AST {
    public StringConstant(Token token) {
        super(null, token, null);
    }

    @Override
    public String evaluate(CallStack callStack) {
        return this.getToken().getValue();
    }

    @Override
    public void visit(ScopedSymbolTable symbolTable) {
    }
}
