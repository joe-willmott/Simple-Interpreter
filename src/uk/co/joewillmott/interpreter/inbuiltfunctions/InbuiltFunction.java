package uk.co.joewillmott.interpreter.inbuiltfunctions;

import uk.co.joewillmott.ast.Block;
import uk.co.joewillmott.interpreter.symbols.FunctionSymbol;

public class InbuiltFunction extends FunctionSymbol {
    public InbuiltFunction(String name) {
        super(name, new Block());
    }
}
