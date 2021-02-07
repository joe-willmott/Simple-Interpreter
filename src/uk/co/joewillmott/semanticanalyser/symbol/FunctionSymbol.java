package uk.co.joewillmott.semanticanalyser.symbol;

import uk.co.joewillmott.ast.Block;

import java.util.ArrayList;

public class FunctionSymbol extends Symbol {
    private Block block;
    private ArrayList<VariableSymbol> parameters = new ArrayList<>();
    private ThrowingFunction<Object> customFunction;

    public FunctionSymbol(String name, Block block) {
        super(name);
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }

    public ArrayList<VariableSymbol> getParameters() {
        return parameters;
    }

    public void addParameter(VariableSymbol argument) {
        parameters.add(argument);
    }

    public ThrowingFunction<Object> getCustomFunction() {
        return customFunction;
    }

    public void setCustomFunction(ThrowingFunction<Object> customFunction) {
        this.customFunction = customFunction;
    }
}
