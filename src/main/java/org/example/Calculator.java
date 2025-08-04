package org.example;

import java.util.*;
import java.util.regex.*;

public class Calculator {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Put your arithmetical expression");
        String input = scanner.nextLine();
        double result = Calculator.calculateResult(input);
        System.out.printf("Result: %s%n", result);
    }

    private static ArrayList<Token> tokenizeInput(String input) {

        ArrayList<Token> tokens = new ArrayList<Token>();
        String[] parts = input.split("\\s+");
        int pos = 0;
        while(pos<parts.length){
            String part = parts[pos];
            if(part.equals("+")){
                tokens.add(new Token(TokenType.OPERATOR, "+"));
            }
            else if(part.equals("-")){
                tokens.add(new Token(TokenType.OPERATOR, "-"));
            }
            else if(part.equals("*")){
                tokens.add(new Token(TokenType.OPERATOR, "*"));
            }
            else if(part.equals("/")){
                tokens.add(new Token(TokenType.OPERATOR, "/"));
            }
            else if(part.equals("%")){
                tokens.add(new Token(TokenType.OPERATOR, "%"));
            }
            else if (isNumber(parts[pos])){
                tokens.add(new Token(TokenType.NUMBER, parts[pos]));
            }
            else{
                throw new RuntimeException("Unexpected token: " + part);
            }
            pos++;
        }

        return tokens;
    }

    private static boolean isNumber(String input){
        try{
            Double.parseDouble(input);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    private static double calculateReversePolishNotation(ArrayList<Token> tokens) {
        Stack<Double> stack = new Stack<Double>();
        for(Token token : tokens){
            if (token.type==TokenType.NUMBER){
                stack.push(Double.parseDouble(token.value));
            }
            else if (token.type==TokenType.OPERATOR){
                double b = stack.pop();
                double a = stack.pop();
                String operator = token.value;
                double result = switch (operator) {
                    case "+" -> a + b;
                    case "-" -> a - b;
                    case "/" -> a / b;
                    case "*" -> a * b;
                    case "%" -> a % b;
                    default ->
                            throw new RuntimeException(String.format("Unexpected operator found while evaluating %s", operator));
                };
                stack.push(result);
            }
        }
        return stack.peek();
    }

    public static double calculateResult(String input) {
        ArrayList<Token> tokens = tokenizeInput(input);
        return calculateReversePolishNotation(tokens);
    }
}
