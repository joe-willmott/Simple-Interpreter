package uk.co.joewillmott.lexer;

public enum TokenType {
    ADD("ADD"),
    SUB("SUB"),
    MUL("MUL"),
    DIV("DIV"),
    INT_DIV("INT_DIV"),

    BOOL("BOOL"),
    INT("INT"),
    FLOAT("FLOAT"),
    STRING("STRING"),

    GREATER_THAN("GREATER_THAN"),
    LESS_THAN("LESS_THAN"),

    OR("OR"),
    AND("AND"),
    NEGATE("NEGATE"),

    LPAREN("LPARAM"),
    RPAREN("RPAREN"),
    LBRACE("LBRACE"),
    RBRACE("RBRACE"),

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
    CONCAT("CONCAT"),
    DBL_QUOTE("\"");

    public final String label;

    TokenType(String label) {
        this.label = label;
    }
}
