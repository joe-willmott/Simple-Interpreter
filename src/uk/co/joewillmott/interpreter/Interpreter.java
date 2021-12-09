package uk.co.joewillmott.interpreter;

import uk.co.joewillmott.ast.Program;
import uk.co.joewillmott.exceptions.*;
import uk.co.joewillmott.interpreter.inbuiltfunctions.*;
import uk.co.joewillmott.lexer.Lexer;
import uk.co.joewillmott.parser.Parser;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Interpreter {
    private final String program;
    private final CallStack callStack;

    private final Map<String, InbuiltFunction> inBuiltFunctions = new HashMap<>() {{
        put("print", new PrintFunction());
        put("println", new PrintlnFunction());
        put("floor", new FloorFunction());
        put("ceil", new CeilFunction());
        put("error", new ErrorFunction());
        put("toint", new ToIntFunction());
        put("toDouble", new ToDoubleFunction());
        put("tostring", new ToStringFunction());
        put("tobool", new ToBoolFunction());
        put("type", new TypeFunction());
        put("input", new InputFunction());
        put("isnull", new IsNullFunction());
    }};

    public Interpreter(String program) {
        this.program = program;
        this.callStack = new CallStack();

        // noinspection ConstantConditions
        ActivationRecord activationRecord = this.callStack.get(0);
        activationRecord.putAll(inBuiltFunctions);
        activationRecord.put("null", null);
        activationRecord.put("Math", Math.class);
    }

    public void evaluate() throws UnableToPeekException, InvalidSyntaxException, UndefinedVariableException, UndefinedFunctionException, InvalidTypeException, InvalidFunctionCall, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Lexer lexer = new Lexer(this.program);
        Parser parser = new Parser(lexer);
        Program program = parser.parse();

        ReturnValue returnValue = program.evaluate(this.callStack);

        if (returnValue != null) {
            System.out.println(returnValue.getValue());
        }
    }
}
