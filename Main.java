package com.example.mypackage;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        while (true) {
            String input = scanner.nextLine();
            coffeeMachine.setInput(input);
        }
    }
}

