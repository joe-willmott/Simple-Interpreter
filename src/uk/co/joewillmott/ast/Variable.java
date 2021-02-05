package uk.co.joewillmott.ast;

import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.lexer.Token;
import uk.co.joewillmott.semanticanalyser.ScopedSymbolTable;

public class Variable extends AST {
    public Variable(Token token) {
        super(null, token, null);
    }

    @Override
    public Object evaluate(CallStack callStack) throws UndefinedVariableException {
        String varName = this.getValue();

        return callStack.findVar(varName);
    }

    @Override
    public void visit(ScopedSymbolTable symbolTable) throws UndefinedVariableException {
        if (symbolTable.lookup(this.getValue(), false) == null) {
            throw new UndefinedVariableException(this.getValue());
        }
    }
}
