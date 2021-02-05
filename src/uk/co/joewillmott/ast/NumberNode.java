package uk.co.joewillmott.ast;

import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.lexer.Token;
import uk.co.joewillmott.semanticanalyser.ScopedSymbolTable;

public class NumberNode extends AST {
    public NumberNode(Token token) {
        super(null, token, null);
    }

    @Override
    public Float evaluate(CallStack callStack) {
        return Float.valueOf(this.getToken().getValue());
    }

    @Override
    public void visit(ScopedSymbolTable symbolTable) {
    }
}