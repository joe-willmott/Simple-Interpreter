package uk.co.joewillmott.interpreter;

import uk.co.joewillmott.ast.Program;
import uk.co.joewillmott.exceptions.*;
import uk.co.joewillmott.interpreter.inbuiltfunctions.*;
import uk.co.joewillmott.lexer.Lexer;
import uk.co.joewillmott.parser.Parser;
import uk.co.joewillmott.semanticanalyser.SemanticAnalyser;

import java.util.HashMap;
import java.util.Map;

public class Interpreter {
    private String program;
    private CallStack callStack;

    private Map<String, InbuiltFunction> inBuiltFunctions = new HashMap<>() {{
        put("print", new PrintFunction());
        put("println", new PrintlnFunction());
        put("floor", new FloorFunction());
        put("error", new ErrorFunction());
    }};

    public Interpreter(String program) {
        this.program = program;
        this.callStack = new CallStack();
    }

    public void evaluate() throws UnableToPeekException, InvalidSyntaxException, UndefinedVariableException, UndefinedFunctionException, InvalidTypeException {
        Lexer lexer = new Lexer(this.program);
        Parser parser = new Parser(lexer);
        Program program = parser.parse();

        SemanticAnalyser semanticAnalyser = new SemanticAnalyser();
        semanticAnalyser.evaluate(program, this.inBuiltFunctions);

        program.evaluate(this.callStack);
    }
}
