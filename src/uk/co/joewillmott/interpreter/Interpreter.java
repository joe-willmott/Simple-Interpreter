package uk.co.joewillmott.interpreter;

import uk.co.joewillmott.ast.AST;
import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;

public class Interpreter {
    private AST tree;
    private CallStack callStack;

    public Interpreter(AST tree) {
        this.tree = tree;

        this.callStack = new CallStack();
    }

    public Object visit(AST tree) throws UndefinedVariableException, InvalidTypeException {
        if (tree == null) {
            return null;
        }

        return tree.evaluate(callStack);
    }
}
