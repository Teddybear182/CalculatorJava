package org.example;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("put your arithmetical expression");
        String input = scanner.nextLine();

        double result = Calculator.calculateResult(input);
        System.out.printf("Result: %s", result);
    }
}
