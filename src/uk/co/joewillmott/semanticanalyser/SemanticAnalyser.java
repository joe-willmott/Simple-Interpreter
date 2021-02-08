package uk.co.joewillmott.semanticanalyser;

import uk.co.joewillmott.ast.Program;
import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedFunctionException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.inbuiltfunctions.InbuiltFunction;

import java.util.Map;

public class SemanticAnalyser {
    public void evaluate(Program program, Map<String, InbuiltFunction> inbuiltFunctions) throws UndefinedVariableException, InvalidTypeException, UndefinedFunctionException {
        ScopedSymbolTable scopedSymbolTable = new ScopedSymbolTable("GLOBAL", 0, false, null, null);

        scopedSymbolTable.putAll(inbuiltFunctions);

        program.visit(scopedSymbolTable);
    }
}
