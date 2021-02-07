package uk.co.joewillmott;

import uk.co.joewillmott.exceptions.*;
import uk.co.joewillmott.interpreter.Interpreter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws UnableToPeekException, InvalidSyntaxException, UndefinedVariableException, InvalidTypeException, IOException, UndefinedFunctionException {
        String content = new String(Files.readAllBytes(Paths.get("C:\\Users\\Joe\\Desktop\\Interpreter\\src\\uk\\co\\joewillmott\\prog.txt")));
        content = content.replaceAll("[\\r\\n]", " ");

        Interpreter interpreter = new Interpreter(content);
        interpreter.evaluate();
    }
}
