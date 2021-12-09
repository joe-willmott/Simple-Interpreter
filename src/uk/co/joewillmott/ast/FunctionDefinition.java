package uk.co.joewillmott.ast;

import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.interpreter.symbols.FunctionSymbol;
import uk.co.joewillmott.interpreter.symbols.VariableSymbol;

import java.util.ArrayList;

public class FunctionDefinition extends AST {
    private final String name;
    private final ArrayList<AST> parameters;
    private final Block block;

    public FunctionDefinition(String name, ArrayList<AST> parameters, Block block) {
        super(null, null, null);
        this.name = name;
        this.parameters = parameters;
        this.block = block;
    }

    public String getName() {
        return name;
    }

    @Override
    public Object evaluate(CallStack callStack) {
        FunctionSymbol functionSymbol = new FunctionSymbol(this.name, block);

        for (AST parameter : this.parameters) {
            String parameterName = parameter.getValue();

            functionSymbol.addParameter(new VariableSymbol(parameterName));
        }

        callStack.peek().put(this.name, functionSymbol);

        return null;
    }
}
