package uk.co.joewillmott.ast;

import uk.co.joewillmott.exceptions.InvalidFunctionCall;
import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedFunctionException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MethodCall extends AST {
    private final FunctionCall functionCall;

    public MethodCall(Variable left, FunctionCall functionCall) {
        super(left, null, null);
        this.functionCall = functionCall;
    }

    @Override
    public Object evaluate(CallStack callStack) throws UndefinedVariableException, InvalidTypeException, InvalidFunctionCall, UndefinedFunctionException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object evauatedExpression = this.getLeft().evaluate(callStack);

        ArrayList<AST> arguments = functionCall.getArguments();

        Object[] evaluatedArguments = new Object[arguments.size()];
        Class<?>[] classes = new Class[arguments.size()];

        for (int i = 0; i < arguments.size(); i++) {
            Object result = arguments.get(i).evaluate(callStack);
            evaluatedArguments[i] = result;

            if (result.getClass() == Long.class) {
                classes[i] = long.class;
            } else if (result.getClass() == Double.class) {
                classes[i] = double.class;
            } else if (result.getClass() == Integer.class) {
                classes[i] = int.class;
            } else {
                classes[i] = Object.class;
            }
        }

        Method method;
        if (evauatedExpression instanceof Class<?>) {
            method = ((Class<?>) evauatedExpression).getMethod(functionCall.getName(), classes);
        } else {
            method = evauatedExpression.getClass().getMethod(functionCall.getName(), classes);
        }

        return method.invoke(evauatedExpression, evaluatedArguments);
    }
}
