package uk.co.joewillmott.ast;

import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedFunctionException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.semanticanalyser.symbol.FunctionSymbol;
import uk.co.joewillmott.semanticanalyser.ScopedSymbolTable;
import uk.co.joewillmott.semanticanalyser.symbol.Symbol;
import uk.co.joewillmott.semanticanalyser.symbol.VariableSymbol;

import java.util.ArrayList;

public class FunctionDefinition extends AST {
    private String name;
    private ArrayList<AST> parameters;
    private Block block;

    public FunctionDefinition(String name, ArrayList<AST> parameters, Block block) {
        super(null, null, null);
        this.name = name;
        this.parameters = parameters;
        this.block = block;
    }

    public String getName() {
        return name;
    }

    public ArrayList<AST> getParameters() {
        return parameters;
    }

    public void addParameter(AST parameter) {
        parameters.add(parameter);
    }

    public AST getBlock() {
        return block;
    }

    @Override
    public Object evaluate(CallStack callStack) {
        return null;
    }

    @Override
    public void visit(ScopedSymbolTable symbolTable) throws UndefinedVariableException, InvalidTypeException, UndefinedFunctionException {
        Symbol functionSymbol = new FunctionSymbol(this.name, this.block);

        symbolTable.insert(functionSymbol);

        ScopedSymbolTable functionScope = new ScopedSymbolTable(this.name, symbolTable.getScopeLevel() + 1, true, symbolTable, symbolTable.getGlobalScope());

        for (AST parameter : this.parameters) {
            String parameterName = parameter.getValue();
            Symbol parameterSymbol = new VariableSymbol(parameterName);

            functionScope.put(parameterName, parameterSymbol);

            functionScope.put(parameterName, parameterSymbol);
        }

        this.block.visit(functionScope);
    }
}
