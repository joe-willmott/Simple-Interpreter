package uk.co.joewillmott.lexer;

public class Token {
    private final TokenType type;
    private final String value;

    private final int line;
    private final int col;

    public Token(TokenType type, String value, int line, int col) {
        this.type = type;
        this.value = value;
        this.line = line;
        this.col = col;
    }

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
        this.line = -1;
        this.col = -1;
    }

    public TokenType getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public int getLine() {
        return line;
    }

    public int getCol() {
        return col;
    }

    public String toString() {
        return String.format("Token [%s] [%s]", this.type, this.value);
    }
}
