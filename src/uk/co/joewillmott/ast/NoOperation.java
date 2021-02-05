package uk.co.joewillmott.ast;

import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.semanticanalyser.ScopedSymbolTable;

public class NoOperation extends AST {
    public NoOperation() {
        super(null, null, null);
    }

    @Override
    public Object evaluate(CallStack callStack) {
        return null;
    }

    @Override
    public void visit(ScopedSymbolTable symbolTable) {
    }
}
