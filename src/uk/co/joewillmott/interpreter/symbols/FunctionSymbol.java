package uk.co.joewillmott.interpreter.symbols;

import uk.co.joewillmott.ast.Block;
import uk.co.joewillmott.interpreter.ReturnValue;

import java.util.ArrayList;

public class FunctionSymbol extends Symbol {
    private final Block block;
    private final ArrayList<VariableSymbol> parameters = new ArrayList<>();
    private ThrowingFunction<ReturnValue> customFunction;

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

    public ThrowingFunction<ReturnValue> getCustomFunction() {
        return customFunction;
    }

    public void setCustomFunction(ThrowingFunction<ReturnValue> customFunction) {
        this.customFunction = customFunction;
    }
}
