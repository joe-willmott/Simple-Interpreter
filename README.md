# Interpreter

A fun little interpreter I made for a made up language.

# Syntax

## Number
`number = [+-]?[0-9]+(.[0-9]+)?`

## String
`string = "[A-z]"` (all double quotes **"** must be escaped with a backslash **\\**)

## Variable
`identifier = [A-Za-z]+`

## Variable Assignment
`assignment = variable, '=', identifier |  expression | function_call, ';'`

## Expression
`expression = number | string | identifier | operator, ';'`

## Function Definition
`function_def = 'fun', identifier, '(', identifier?(,identifier)*, ')', '{' assignment*, '}', ';'`

## Function Call
`function_call = identifier, '(', identifier?(,identifier)*, ')', ';'`

## While Loop
`while_loop = 'while', '(', expression, ')', '{' assignment*, '}', ';'`