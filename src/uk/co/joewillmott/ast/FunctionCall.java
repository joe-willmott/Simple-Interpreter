package uk.co.joewillmott.ast;

import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedFunctionException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.semanticanalyser.ScopedSymbolTable;
import uk.co.joewillmott.semanticanalyser.symbol.Symbol;

import java.util.ArrayList;

public class FunctionCall extends AST {
    private String name;
    private ArrayList<AST> arguments;
    private Symbol symbol;

    public FunctionCall(String name, ArrayList<AST> arguments) {
        super(null, null, null);
        this.name = name;
        this.arguments = arguments;
    }

    public String getName() {
        return name;
    }

    public ArrayList<AST> getArguments() {
        return arguments;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public Object evaluate(CallStack callStack) {
        return null;
    }

    @Override
    public void visit(ScopedSymbolTable symbolTable) throws UndefinedVariableException, InvalidTypeException, UndefinedFunctionException {
        for (AST argument : arguments) {
            argument.visit(symbolTable);
        }

        Symbol symbol = symbolTable.lookup(this.getName(), false);

        if (symbol == null) {
            throw new UndefinedFunctionException(this.getName());
        }

        this.symbol = symbol;
    }
}
