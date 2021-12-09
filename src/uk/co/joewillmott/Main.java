package uk.co.joewillmott;

import uk.co.joewillmott.exceptions.*;
import uk.co.joewillmott.interpreter.Interpreter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws UnableToPeekException, InvalidSyntaxException, UndefinedVariableException, InvalidTypeException, IOException, UndefinedFunctionException, InvalidFunctionCall, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (args.length == 0) {
            throw new IllegalArgumentException("First argument should be the path to the file to execute.");
        }

        String content = Files.readString(Paths.get(args[0]));

        Interpreter interpreter = new Interpreter(content);
        interpreter.evaluate();
    }
}
