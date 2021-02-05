package uk.co.joewillmott;

import uk.co.joewillmott.ast.Block;
import uk.co.joewillmott.exceptions.*;
import uk.co.joewillmott.interpreter.Interpreter;
import uk.co.joewillmott.lexer.Lexer;
import uk.co.joewillmott.parser.Parser;
import uk.co.joewillmott.semanticanalyser.SemanticAnalyser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws UnableToPeekException, InvalidSyntaxException, UndefinedVariableException, InvalidTypeException, IOException, UndefinedFunctionException {
        String content = new String(Files.readAllBytes(Paths.get("C:\\Users\\Joe\\Desktop\\Interpreter\\src\\uk\\co\\joewillmott\\prog.txt")));
        content = content.replaceAll("[\\r\\n]", " ");

        Lexer lexer = new Lexer(content);
        Parser parser = new Parser(lexer);
        Block result = parser.parse();
        SemanticAnalyser semanticAnalyser = new SemanticAnalyser();
        semanticAnalyser.run(result);
        Interpreter interpreter = new Interpreter(result);

        System.out.println(interpreter.visit(result));
    }
}
