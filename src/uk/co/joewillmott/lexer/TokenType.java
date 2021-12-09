package uk.co.joewillmott.lexer;

public enum TokenType {
    ADD("ADD"),
    SUB("SUB"),
    MUL("MUL"),
    DIV("DIV"),
    INT_DIV("INT DIV"),

    BOOL("BOOL"),
    INT("INT"),
    Double("Double"),
    STRING("STRING"),

    GREATER_THAN("GREATER THAN"),
    LESS_THAN("LESS THAN"),
    EQUALITY("EQUAL"),
    INV_EQUALITY("NOT EQUAL"),

    OR("OR"),
    AND("AND"),
    NEGATE("NEGATE"),

    LPAREN("LPARAM"),
    RPAREN("RPAREN"),
    LBRACE("LBRACE"),
    RBRACE("RBRACE"),
    LBRACE_SQUARE("LBRACE_SQUARE"),
    RBRACE_SQUARE("RBRACE_SQUARE"),

    EOF("EOF"),
    ID("ID"),
    ASSIGN("="),
    COMMA(","),
    FUN("FUN"),
    SEMI(";"),
    IF("IF"),
    ELSE("ELSE"),
    WHILE("WHILE"),
    RETURN("RETURN"),
    NULL("NULL"),
    CONCAT("CONCAT"),
    DBL_QUOTE("\""),
    NEWLINE("\n"),
    IMPORT("IMPORT"),
    FULL_STOP(".");

    public final String label;

    TokenType(String label) {
        this.label = label;
    }
}
