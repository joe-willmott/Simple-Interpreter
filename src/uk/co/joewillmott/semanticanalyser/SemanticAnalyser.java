package uk.co.joewillmott.semanticanalyser;

import uk.co.joewillmott.ast.Block;
import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedFunctionException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;

// Need to add type checking
public class SemanticAnalyser {
    public void run(Block tree) throws UndefinedVariableException, InvalidTypeException, UndefinedFunctionException {
        tree.visit(null);
    }
}
