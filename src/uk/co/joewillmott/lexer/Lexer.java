package uk.co.joewillmott.lexer;

import uk.co.joewillmott.exceptions.UnableToPeekException;

import java.util.HashMap;

public class Lexer {
    private final String text;
    private int line = 0;
    private int col = 0;
    private int pos;
    private char currentChar;
    private boolean finished = false;
    private final HashMap<String, Token> RESERVED_KEYWORDS = new HashMap<>() {{
        put("true", new Token(TokenType.BOOL, "true", line, col));
        put("false", new Token(TokenType.BOOL, "false", line, col));
        put("fun", new Token(TokenType.FUN, "fun", line, col));
        put("if", new Token(TokenType.IF, "if", line, col));
        put("else", new Token(TokenType.ELSE, "else", line, col));
        put("while", new Token(TokenType.WHILE, "while", line, col));
        put("return", new Token(TokenType.RETURN, "return", line, col));
        put("null", new Token(TokenType.NULL, "null", line, col));
        put("import", new Token(TokenType.IMPORT, "import", line, col));
    }};
    private final HashMap<Character, Token> CHARACTERS = new HashMap<>() {{
        put('+', new Token(TokenType.ADD, "+", line, col));
        put('-', new Token(TokenType.SUB, "-", line, col));
        put('*', new Token(TokenType.MUL, "*", line, col));
        put('/', new Token(TokenType.DIV, "/", line, col));
        put('>', new Token(TokenType.GREATER_THAN, ">", line, col));
        put('<', new Token(TokenType.LESS_THAN, "<", line, col));
        put('~', new Token(TokenType.NEGATE, "~", line, col));
        put(',', new Token(TokenType.COMMA, ",", line, col));
        put('(', new Token(TokenType.LPAREN, "(", line, col));
        put(')', new Token(TokenType.RPAREN, ")", line, col));
        put('=', new Token(TokenType.ASSIGN, "=", line, col));
        put('{', new Token(TokenType.LBRACE, "{", line, col));
        put('}', new Token(TokenType.RBRACE, "}", line, col));
        put(';', new Token(TokenType.SEMI, ";", line, col));
        put('\"', new Token(TokenType.DBL_QUOTE, "\"", line, col));
        put('[', new Token(TokenType.LBRACE_SQUARE, "[", line, col));
        put(']', new Token(TokenType.RBRACE_SQUARE, "]", line, col));
        put('.', new Token(TokenType.FULL_STOP, ".", line, col));
    }};

    public Lexer(String text) {
        this.text = text;
        this.pos = 0;
        this.currentChar = this.text.charAt(this.pos);
    }

    private void skipWhitespace() {
        while (!finished && Character.isWhitespace(this.currentChar)) {
            this.advance();

            if (this.currentChar == '\n') {
                break;
            }
        }
    }

    private void advance() {
        this.pos++;
        this.col++;

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

            while (!this.finished && !Character.isWhitespace(this.currentChar) && Character.isDigit(this.currentChar)) {
                result.append(this.currentChar);
                this.advance();
            }

            return new Token(TokenType.Double, result.toString(), line, col);
        }

        return new Token(TokenType.INT, result.toString(), line, col);
    }

    private Token id() {
        StringBuilder result = new StringBuilder();

        while (!this.finished && !Character.isWhitespace(this.currentChar) && Character.isAlphabetic(this.currentChar)) {
            result.append(this.currentChar);
            this.advance();

            while (!this.finished && !Character.isWhitespace(this.currentChar) && (Character.isAlphabetic(this.currentChar) || Character.isDigit(this.currentChar))) {
                result.append(this.currentChar);
                this.advance();
            }
        }

        String id = result.toString();
        Token token = RESERVED_KEYWORDS.get(id);

        if (token == null) {
            token = new Token(TokenType.ID, id, line, col);
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

        return new Token(TokenType.STRING, returnValue.toString(), line, col);
    }

    public Token getNextToken() throws UnableToPeekException {
        while (!this.finished) {
            if (this.currentChar == '\"') {
                return this.string();
            }

            if (this.currentChar == '\n') {
                Token token = new Token(TokenType.NEWLINE, "\n", line, col);

                this.advance();
                this.line++;
                this.col = 0;

                return token;
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
                return new Token(TokenType.INT_DIV, "//", line, col);
            }

            if (this.currentChar == '=' && this.peek() == '=') {
                this.advance();
                this.advance();
                return new Token(TokenType.EQUALITY, "==", line, col);
            }

            if (this.currentChar == '<' && this.peek() == '>') {
                this.advance();
                this.advance();
                return new Token(TokenType.INV_EQUALITY, "<>", line, col);
            }

            if (this.currentChar == '.' && this.peek() == '.') {
                this.advance();
                this.advance();
                return new Token(TokenType.CONCAT, "..", line, col);
            }

            if (this.currentChar == '&' && this.peek() == '&') {
                this.advance();
                this.advance();
                return new Token(TokenType.AND, "&&", line, col);
            }

            if (this.currentChar == '|' && this.peek() == '|') {
                this.advance();
                this.advance();
                return new Token(TokenType.OR, "||", line, col);
            }

            Token token = CHARACTERS.get(this.currentChar);

            if (token == null) {
                throw new IllegalStateException("Unexpected value: " + this.currentChar);
            }

            this.advance();

            return token;
        }

        return new Token(TokenType.EOF, "EOF", line, col);
    }

    public char getCurrentChar() {
        return this.currentChar;
    }
}
