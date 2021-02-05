package uk.co.joewillmott.semanticanalyser.symbol;

import uk.co.joewillmott.ast.Block;

public class FunctionSymbol extends Symbol {
    private Block block;

    public FunctionSymbol(String name, Block block) {
        super(name);
        this.block = block;
    }
}
