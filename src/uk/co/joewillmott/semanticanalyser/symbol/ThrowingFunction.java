package uk.co.joewillmott.semanticanalyser.symbol;

import uk.co.joewillmott.ast.AST;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;

import java.util.ArrayList;

public interface ThrowingFunction<ReturnType> {
    ReturnType run(ArrayList<AST> arguments, CallStack callStack) throws UndefinedVariableException;
}
