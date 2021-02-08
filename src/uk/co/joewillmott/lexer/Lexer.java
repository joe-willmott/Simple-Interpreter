package uk.co.joewillmott.lexer;

import uk.co.joewillmott.exceptions.UnableToPeekException;

import java.util.HashMap;

public class Lexer {
    private final HashMap<String, Token> RESERVED_KEYWORDS = new HashMap<>() {{
        put("true", new Token(TokenType.BOOL, "true"));
        put("false", new Token(TokenType.BOOL, "false"));
        put("fun", new Token(TokenType.FUN, "fun"));
        put("if", new Token(TokenType.IF, "if"));
        put("else", new Token(TokenType.ELSE, "else"));
        put("while", new Token(TokenType.WHILE, "while"));
        put("return", new Token(TokenType.RETURN, "return"));
    }};

    private final HashMap<Character, Token> CHARACTERS = new HashMap<>() {{
        put('+', new Token(TokenType.ADD, "+"));
        put('-', new Token(TokenType.SUB, "-"));
        put('*', new Token(TokenType.MUL, "*"));
        put('/', new Token(TokenType.DIV, "/"));
        put('>', new Token(TokenType.GREATER_THAN, ">"));
        put('<', new Token(TokenType.LESS_THAN, "<"));
        put('~', new Token(TokenType.NEGATE, "~"));
        put(',', new Token(TokenType.COMMA, ","));
        put('(', new Token(TokenType.LPAREN, "("));
        put(')', new Token(TokenType.RPAREN, ")"));
        put('=', new Token(TokenType.ASSIGN, "="));
        put('{', new Token(TokenType.LBRACE, "{"));
        put('}', new Token(TokenType.RBRACE, "}"));
        put(';', new Token(TokenType.SEMI, ";"));
        put('\"', new Token(TokenType.DBL_QUOTE, "\""));
    }};

    private String text;
    private int pos;
    private char currentChar;
    private boolean finished = false;

    public Lexer(String text) {
        this.text = text;
        this.pos = 0;
        this.currentChar = this.text.charAt(this.pos);
    }

    private void skipWhitespace() {
        while (!finished && this.currentChar == ' ') {
            this.advance();
        }
    }

    private void advance() {
        this.pos++;

        if (this.pos > text.length() - 1) {
            this.finished = true;
        } else {
            this.currentChar = this.text.charAt(this.pos);
        }
    }

    private char peek() throws UnableToPeekException {
        int peek_pos = this.pos + 1;

        if (peek_pos > text.length() - 1) {
            throw new UnableToPeekException("");
        } else {
            return this.text.charAt(peek_pos);
        }
    }

    private Token number() {
        StringBuilder result = new StringBuilder();

        while (!this.finished && !Character.isWhitespace(this.currentChar) && Character.isDigit(this.currentChar)) {
            result.append(this.currentChar);
            this.advance();
        }

        if (this.currentChar == '.') {
            result.append(this.currentChar);
            this.advance();

            return new Token(TokenType.FLOAT, result.toString());
        }

        return new Token(TokenType.INT, result.toString());
    }

    private Token id() {
        StringBuilder result = new StringBuilder();

        while (!this.finished && !Character.isWhitespace(this.currentChar) && Character.isAlphabetic(this.currentChar)) {
            result.append(this.currentChar);
            this.advance();
        }

        String id = result.toString();
        Token token = RESERVED_KEYWORDS.get(id);

        if (token == null) {
            token = new Token(TokenType.ID, id);
        }

        return token;
    }

    private Token string() {
        StringBuilder returnValue = new StringBuilder();

        this.advance();
        while (!this.finished && this.currentChar != '\"') {
            if (this.currentChar == '\\') {
                this.advance();
            }
            returnValue.append(this.currentChar);
            this.advance();
        }

        this.advance();

        return new Token(TokenType.STRING, returnValue.toString());
    }

    public Token getNextToken() throws UnableToPeekException {
        while (!this.finished) {
            if (this.currentChar == '\"') {
                return this.string();
            }

            if (Character.isWhitespace(this.currentChar)) {
                this.skipWhitespace();
                continue;
            }

            if (Character.isAlphabetic(this.currentChar)) {
                return this.id();
            }

            if (Character.isDigit(this.currentChar)) {
                return this.number();
            }

            if (this.currentChar == '/' && this.peek() == '/') {
                this.advance();
                this.advance();
                return new Token(TokenType.INT_DIV, "//");
            }

            if (this.currentChar == '.' && this.peek() == '.') {
                this.advance();
                this.advance();
                return new Token(TokenType.CONCAT, "..");
            }

            if (this.currentChar == '&' && this.peek() == '&') {
                this.advance();
                this.advance();
                return new Token(TokenType.AND, "&&");
            }

            if (this.currentChar == '|' && this.peek() == '|') {
                this.advance();
                this.advance();
                return new Token(TokenType.OR, "||");
            }

            Token token = CHARACTERS.get(this.currentChar);

            if (token == null) {
                throw new IllegalStateException("Unexpected value: " + this.currentChar);
            }

            this.advance();

            return token;
        }

        return new Token(TokenType.EOF, "EOF");
    }

    public char getCurrentChar() {
        return this.currentChar;
    }
}
