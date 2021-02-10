package uk.co.joewillmott.ast;

import uk.co.joewillmott.exceptions.InvalidFunctionCall;
import uk.co.joewillmott.exceptions.InvalidTypeException;
import uk.co.joewillmott.exceptions.UndefinedFunctionException;
import uk.co.joewillmott.exceptions.UndefinedVariableException;
import uk.co.joewillmott.interpreter.CallStack;
import uk.co.joewillmott.lexer.Token;
import uk.co.joewillmott.semanticanalyser.ScopedSymbolTable;

public class BinaryOperation extends AST {
    public BinaryOperation(AST left, Token token, AST right) {
        super(left, token, right);
    }

    private void error(Object a, Object b) throws InvalidTypeException {
        throw new InvalidTypeException(String.format("Invalid types %s and %s for operation %s.", a.getClass().getName(), b.getClass().getName(), this.getToken().getValue()));
    }

    private boolean isBoolean(Object a) {
        return a instanceof Boolean;
    }

    private boolean isNumber(Object a) {
        return a instanceof Float || a instanceof Integer;
    }

    private boolean isString(Object a) {
        return a instanceof String;
    }

    private void checkBooleans(Object a, Object b) throws InvalidTypeException {
        if (!isBoolean(a) || !isBoolean(b)) {
            this.error(a, b);
        }
    }

    private void checkNumbers(Object a, Object b) throws InvalidTypeException {
        if (!isNumber(a) || !isNumber(b)) {
            this.error(a, b);
        }
    }

    private Number add(Object a, Object b) throws InvalidTypeException {
        checkNumbers(a, b);

        if (a instanceof Float || b instanceof Float) {
            return Float.parseFloat(a.toString()) + Float.parseFloat(b.toString());
        } else {
            return Integer.parseInt(a.toString()) + Integer.parseInt(b.toString());
        }
    }

    public Number sub(Object a, Object b) throws InvalidTypeException {
        checkNumbers(a, b);

        if (a instanceof Float || b instanceof Float) {
            return Float.parseFloat(a.toString()) - Float.parseFloat(b.toString());
        } else {
            return Integer.parseInt(a.toString()) - Integer.parseInt(b.toString());
        }
    }

    public Number mul(Object a, Object b) throws InvalidTypeException {
        checkNumbers(a, b);

        if (a instanceof Float || b instanceof Float) {
            return Float.parseFloat(a.toString()) * Float.parseFloat(b.toString());
        } else {
            return Integer.parseInt(a.toString()) * Integer.parseInt(b.toString());
        }
    }

    public Float div(Object a, Object b) throws InvalidTypeException {
        checkNumbers(a, b);
        return Float.parseFloat(a.toString()) / Float.parseFloat(b.toString());
    }

    public Integer intDiv(Object a, Object b) throws InvalidTypeException {
        checkNumbers(a, b);
        return Math.round(div(a, b));
    }

    public Boolean or(Object a, Object b) throws InvalidTypeException {
        checkBooleans(a, b);
        return Boolean.parseBoolean(a.toString()) || Boolean.parseBoolean(b.toString());
    }

    public Boolean and(Object a, Object b) throws InvalidTypeException {
        checkBooleans(a, b);
        return Boolean.parseBoolean(a.toString()) && Boolean.parseBoolean(b.toString());
    }

    public Boolean greaterThan(Object a, Object b) throws InvalidTypeException {
        checkNumbers(a, b);
        return Float.parseFloat(a.toString()) > Float.parseFloat(b.toString());
    }

    public Boolean lessThan(Object a, Object b) throws InvalidTypeException {
        checkNumbers(a, b);
        return Float.parseFloat(a.toString()) < Float.parseFloat(b.toString());
    }

    private Boolean equal(Object a, Object b) throws InvalidTypeException {
        if (isNumber(a) && isNumber(b)) {
            return Float.compare(Float.parseFloat(a.toString()), Float.parseFloat(b.toString())) == 0;
        }

        if (isBoolean(a) && isBoolean(b)) {
            return Boolean.compare((Boolean) a, (Boolean) b) == 0;
        }

        if (isString(a) && isString(b)) {
            return a.equals(b);
        }

        this.error(a, b);
        return null;
    }

    @Override
    public Object evaluate(CallStack callStack) throws UndefinedVariableException, InvalidTypeException, InvalidFunctionCall {
        Object a = this.getLeft().evaluate(callStack);
        Object b = this.getRight().evaluate(callStack);

        switch (this.getToken().getType()) {
            case ADD:
                return this.add(a, b);
            case SUB:
                return this.sub(a, b);
            case MUL:
                return this.mul(a, b);
            case DIV:
                return this.div(a, b);
            case INT_DIV:
                return this.intDiv(a, b);
            case OR:
                return this.or(a, b);
            case AND:
                return this.and(a, b);
            case GREATER_THAN:
                return this.greaterThan(a, b);
            case LESS_THAN:
                return this.lessThan(a, b);
            case EQUALITY:
                return this.equal(a, b);
            case INV_EQUALITY:
                //noinspection ConstantConditions
                return !this.equal(a, b);
            case CONCAT:
                return String.format("%s%s", this.getLeft().evaluate(callStack), this.getRight().evaluate(callStack));
        }

        return null;
    }

    @Override
    public void visit(ScopedSymbolTable symbolTable) throws UndefinedVariableException, InvalidTypeException, UndefinedFunctionException {
        this.getLeft().visit(symbolTable);
        this.getRight().visit(symbolTable);
    }
}
