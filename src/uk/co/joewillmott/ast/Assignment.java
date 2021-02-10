package uk.co.joewillmott.ast;

import uk.co.joewillmott.exceptions.InvalidFunctionCall;
import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedFunctionException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.ActivationRecord;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.lexer.Token;
import uk.co.joewillmott.semanticanalyser.ScopedSymbolTable;
import uk.co.joewillmott.semanticanalyser.symbol.Symbol;
import uk.co.joewillmott.semanticanalyser.symbol.VariableSymbol;

public class Assignment extends AST {
    public Assignment(AST left, Token token, AST right) {
        super(left, token, right);
    }

    @Override
    public Object evaluate(CallStack callStack) throws UndefinedVariableException, InvalidTypeException, InvalidFunctionCall {
        String varName = this.getLeft().getValue();

        Object varValue = this.getRight().evaluate(callStack);

        ActivationRecord activationRecord = callStack.peek();

        activationRecord.put(varName, varValue);

        return null;
    }

    @Override
    public void visit(ScopedSymbolTable symbolTable) throws UndefinedVariableException, InvalidTypeException, UndefinedFunctionException {
        String varName = this.getLeft().getValue();

        Symbol variable = symbolTable.lookup(varName, false);

        if (variable == null) {
            symbolTable.put(varName, new VariableSymbol(varName));
        }

        this.getRight().visit(symbolTable);
    }
}
