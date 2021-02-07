package uk.co.joewillmott.interpreter;

import uk.co.joewillmott.ast.Program;
import uk.co.joewillmott.exceptions.*;
import uk.co.joewillmott.lexer.Lexer;
import uk.co.joewillmott.parser.Parser;
import uk.co.joewillmott.semanticanalyser.SemanticAnalyser;

public class Interpreter {
    private String program;
    private CallStack callStack;

    public Interpreter(String program) {
        this.program = program;
        this.callStack = new CallStack();
    }

    public void evaluate() throws UnableToPeekException, InvalidSyntaxException, UndefinedVariableException, UndefinedFunctionException, InvalidTypeException {
        Lexer lexer = new Lexer(this.program);
        Parser parser = new Parser(lexer);
        Program program = parser.parse();

        SemanticAnalyser semanticAnalyser = new SemanticAnalyser();
        semanticAnalyser.evaluate(program);

        program.evaluate(this.callStack);
    }
}
