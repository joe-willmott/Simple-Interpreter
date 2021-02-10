package uk.co.joewillmott.parser;

import uk.co.joewillmott.ast.*;
import uk.co.joewillmott.exceptions.InvalidSyntaxException;
import uk.co.joewillmott.exceptions.UnableToPeekException;
import uk.co.joewillmott.lexer.Lexer;
import uk.co.joewillmott.lexer.Token;
import uk.co.joewillmott.lexer.TokenType;

import java.util.ArrayList;

public class Parser {
    private Lexer lexer;
    private Token currentToken;

    public Parser(Lexer lexer) throws UnableToPeekException {
        this.lexer = lexer;
        this.currentToken = lexer.getNextToken();
    }

    private void chomp(TokenType tokenType) throws UnableToPeekException, InvalidSyntaxException {
        if (this.currentToken.getType() == tokenType) {
            this.currentToken = this.lexer.getNextToken();
        } else {
            throw new InvalidSyntaxException(String.format("UnexpectedToken: %s | ExpectedToken: %s", this.currentToken, tokenType));
        }
    }

    private AST term() throws UnableToPeekException, InvalidSyntaxException {
        AST node = this.factor();

        while (this.currentToken.getType() == TokenType.MUL || this.currentToken.getType() == TokenType.DIV || this.currentToken.getType() == TokenType.INT_DIV || this.currentToken.getType() == TokenType.AND || this.currentToken.getType() == TokenType.OR || this.currentToken.getType() == TokenType.CONCAT) {
            Token token = this.currentToken;
            this.chomp(token.getType());

            node = new BinaryOperation(node, token, this.factor());
        }

        return node;
    }

    private AST factor() throws InvalidSyntaxException, UnableToPeekException {
        Token token = this.currentToken;
        TokenType tokenType = token.getType();

        switch (tokenType) {
            case ADD:
            case SUB:
            case NEGATE:
                this.chomp(tokenType);
                return new UnaryOperation(token, this.factor());
            case INT:
            case FLOAT:
                this.chomp(tokenType);
                return new NumberConstant(token);
            case STRING:
                this.chomp(tokenType);
                return new StringConstant(token);
            case LPAREN:
                this.chomp(tokenType);
                AST node = this.comparisonExpression();
                this.chomp(TokenType.RPAREN);
                return node;
            case BOOL:
                this.chomp(tokenType);
                return new BooleanNode(token);
            default:
                return this.variableOrFunctionCall();
        }
    }

    private AST comparisonExpression() throws UnableToPeekException, InvalidSyntaxException {
        AST node = this.arithmeticExpression();

        while (this.currentToken.getType() == TokenType.GREATER_THAN || this.currentToken.getType() == TokenType.LESS_THAN || this.currentToken.getType() == TokenType.EQUALITY || this.currentToken.getType() == TokenType.INV_EQUALITY) {
            Token token = this.currentToken;
            this.chomp(this.currentToken.getType());

            node = new BinaryOperation(node, token, this.arithmeticExpression());
        }

        return node;
    }

    private AST arithmeticExpression() throws InvalidSyntaxException, UnableToPeekException {
        AST node = this.term();

        while (this.currentToken.getType() == TokenType.ADD || this.currentToken.getType() == TokenType.SUB) {
            Token token = this.currentToken;
            this.chomp(this.currentToken.getType());

            node = new BinaryOperation(node, token, this.term());
        }

        return node;
    }

    private AST variableOrFunctionCall() throws UnableToPeekException, InvalidSyntaxException {
        if (this.currentToken.getType() == TokenType.ID && this.lexer.getCurrentChar() == '(') {
            return this.functionCall();
        }

        return this.variable();
    }

    private Variable variable() throws InvalidSyntaxException, UnableToPeekException {
        Variable node = new Variable(this.currentToken);
        this.chomp(TokenType.ID);
        return node;
    }

    private FunctionDefinition functionDefinition() throws UnableToPeekException, InvalidSyntaxException {
        this.chomp(TokenType.FUN);
        String functionName = this.currentToken.getValue();
        this.chomp(TokenType.ID);

        ArrayList<AST> parameters = new ArrayList<>();

        this.chomp(TokenType.LPAREN);
        if (this.currentToken.getType() == TokenType.ID) {
            parameters.add(new Variable(this.currentToken));

            this.chomp(TokenType.ID);

            while (this.currentToken.getType() == TokenType.COMMA) {
                this.chomp(TokenType.COMMA);
                parameters.add(new Variable(this.currentToken));
                this.chomp(TokenType.ID);
            }
        }
        this.chomp(TokenType.RPAREN);

        this.chomp(TokenType.LBRACE);
        Block block = this.block();
        this.chomp(TokenType.RBRACE);

        return new FunctionDefinition(functionName, parameters, block, false);
    }

    private Assignment assignmentStatement() throws UnableToPeekException, InvalidSyntaxException {
        AST left = this.variable();
        this.chomp(TokenType.ASSIGN);
        return new Assignment(left, this.currentToken, this.comparisonExpression());
    }

    private FunctionCall functionCall() throws InvalidSyntaxException, UnableToPeekException {
        Token token = this.currentToken;
        String functionName = token.getValue();
        this.chomp(TokenType.ID);
        this.chomp(TokenType.LPAREN);

        ArrayList<AST> parameters = new ArrayList<>();
        AST node;

        if (this.currentToken.getType() != TokenType.RPAREN) {
            node = this.comparisonExpression();
            parameters.add(node);
        }

        while (this.currentToken.getType() == TokenType.COMMA) {
            this.chomp(TokenType.COMMA);

            node = this.comparisonExpression();
            parameters.add(node);
        }

        this.chomp(TokenType.RPAREN);

        return new FunctionCall(functionName, parameters);
    }

    private ConditionalStatement conditionalStatement() throws UnableToPeekException, InvalidSyntaxException {
        this.chomp(TokenType.IF);
        this.chomp(TokenType.LPAREN);
        AST condition = this.comparisonExpression();
        this.chomp(TokenType.RPAREN);
        this.chomp(TokenType.LBRACE);
        Block left = this.block();
        this.chomp(TokenType.RBRACE);

        Block right = new Block();
        if (this.currentToken.getType() == TokenType.ELSE) {
            this.chomp(TokenType.ELSE);
            this.chomp(TokenType.LBRACE);
            right = this.block();
            this.chomp(TokenType.RBRACE);
        }

        return new ConditionalStatement(left, condition, right);
    }

    private WhileLoop whileLoop() throws UnableToPeekException, InvalidSyntaxException {
        this.chomp(TokenType.WHILE);
        this.chomp(TokenType.LPAREN);
        AST condition = this.comparisonExpression();
        this.chomp(TokenType.RPAREN);
        this.chomp(TokenType.LBRACE);
        Block left = this.block();
        this.chomp(TokenType.RBRACE);

        return new WhileLoop(left, condition);
    }

    private ReturnStatement returnStatement() throws UnableToPeekException, InvalidSyntaxException {
        this.chomp(TokenType.RETURN);
        AST statement = this.comparisonExpression();

        return new ReturnStatement(statement);
    }

    private AST statement() throws UnableToPeekException, InvalidSyntaxException {
        TokenType tokenType = this.currentToken.getType();

        if (tokenType == TokenType.ID && this.lexer.getCurrentChar() == '(') {
            return this.functionCall();
        }

        switch (tokenType) {
            case IF:
                return this.conditionalStatement();
            case WHILE:
                return this.whileLoop();
            case RETURN:
                return this.returnStatement();
            case ID:
                return this.assignmentStatement();
            case FUN:
                return this.functionDefinition();
            case BOOL:
            case INT:
            case FLOAT:
                return this.comparisonExpression();
            default:
                return new NoOperation();
        }
    }

    private Block block() throws UnableToPeekException, InvalidSyntaxException {
        AST node = this.statement();

        Block block = new Block();
        block.addStatement(node);

        while (this.currentToken.getType() == TokenType.SEMI) {
            this.chomp(TokenType.SEMI);
            block.addStatement(this.statement());
        }

        return block;
    }

    public Program parse() throws InvalidSyntaxException, UnableToPeekException {
        Block block = this.block();

        return new Program(block);
    }
}
