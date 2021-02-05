package uk.co.joewillmott.lexer;

public class Token {
    private TokenType type;
    private String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        return String.format("Token [%s] [%s]", this.type, this.value);
    }
}
