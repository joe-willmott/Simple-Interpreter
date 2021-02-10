package uk.co.joewillmott.interpreter.inbuiltfunctions;

import uk.co.joewillmott.ast.AST;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.semanticanalyser.symbol.VariableSymbol;

import java.util.ArrayList;
import java.util.Scanner;

public class InputFunction extends InbuiltFunction {
    public InputFunction() {
        super("input");

        this.addParameter(new VariableSymbol("a"));
        this.setCustomFunction(InputFunction::run);
    }

    public static String run(ArrayList<AST> arguments, CallStack callStack) throws UndefinedVariableException {
        Object argumentValue = callStack.findVar("a");

        Scanner scanner = new Scanner(System.in);
        System.out.println(argumentValue);

        return scanner.nextLine();
    }
}
