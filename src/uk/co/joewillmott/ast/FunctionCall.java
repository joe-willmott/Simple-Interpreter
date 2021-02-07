package uk.co.joewillmott.ast;

import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedFunctionException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.ARType;
import uk.co.joewillmott.interpreter.ActivationRecord;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.semanticanalyser.ScopedSymbolTable;
import uk.co.joewillmott.semanticanalyser.symbol.FunctionSymbol;
import uk.co.joewillmott.semanticanalyser.symbol.Symbol;
import uk.co.joewillmott.semanticanalyser.symbol.VariableSymbol;

import java.util.ArrayList;

public class FunctionCall extends AST {
    private String name;
    private ArrayList<AST> arguments;
    private FunctionSymbol symbol;

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
    public Object evaluate(CallStack callStack) throws UndefinedVariableException, InvalidTypeException {
        ActivationRecord activationRecord = new ActivationRecord(this.name, ARType.FUNCTION, 0);

        ArrayList<VariableSymbol> parameters = this.symbol.getParameters();

        for (int i = 0; i < Math.min(parameters.size(), this.arguments.size()); i++) {
            activationRecord.put(parameters.get(i).getName(), this.arguments.get(i).evaluate(callStack));
        }

        callStack.push(activationRecord);

        if (this.symbol.getCustomFunction() != null) {
            this.symbol.getCustomFunction().run(arguments, callStack);
        } else {
            this.symbol.getBlock().evaluate(callStack);
        }

        callStack.pop();

        return null;
    }

    @Override
    public void visit(ScopedSymbolTable symbolTable) throws UndefinedVariableException, InvalidTypeException, UndefinedFunctionException {
        for (AST argument : arguments) {
            argument.visit(symbolTable);
        }

        FunctionSymbol symbol;
        try {
            symbol = (FunctionSymbol) symbolTable.lookup(this.getName(), false);

            if (symbol == null) {
                throw new UndefinedFunctionException(this.getName());
            }
        } catch (ClassCastException e) {
            throw new UndefinedFunctionException(this.getName());
        }

        this.symbol = symbol;
    }
}
