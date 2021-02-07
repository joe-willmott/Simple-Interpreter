package uk.co.joewillmott.semanticanalyser;

import uk.co.joewillmott.ast.Program;
import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedFunctionException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.inbuiltfunctions.Floor;
import uk.co.joewillmott.interpreter.inbuiltfunctions.Print;
import uk.co.joewillmott.interpreter.inbuiltfunctions.Println;

public class SemanticAnalyser {
    public void evaluate(Program program) throws UndefinedVariableException, InvalidTypeException, UndefinedFunctionException {
        ScopedSymbolTable scopedSymbolTable = new ScopedSymbolTable("GLOBAL", 0, false, null, null);

        scopedSymbolTable.put("print", new Print());
        scopedSymbolTable.put("println", new Println());
        scopedSymbolTable.put("floor", new Floor());

        program.visit(scopedSymbolTable);
    }
}
