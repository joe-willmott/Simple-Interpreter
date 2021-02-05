package uk.co.joewillmott.ast;

import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.lexer.Token;
import uk.co.joewillmott.semanticanalyser.ScopedSymbolTable;

public class BooleanNode extends AST {
    public BooleanNode(Token token) {
        super(null, token, null);
    }

    @Override
    public Boolean evaluate(CallStack callStack) {
        return Boolean.valueOf(this.getToken().getValue());
    }

    @Override
    public void visit(ScopedSymbolTable symbolTable) {
    }
}

