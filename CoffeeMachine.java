package com.example.mypackage;

enum State {
    MAIN_MENU, BUY_MENU, FILL_MENU,
}

enum startConditions {
    WATER(400), MILK(540), BEANS(120), CUPS(9), MONEY(550);

    public final int number;

    startConditions(int number) {
        this.number = number;
    }
}

public class CoffeeMachine {
    private String input;
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;
    State currentState;
    private int counter;
    boolean firstTime = true;

    public CoffeeMachine() {
        this.water = startConditions.WATER.number;
        this.milk = startConditions.MILK.number;
        this.beans = startConditions.BEANS.number;
        this.cups = startConditions.CUPS.number;
        this.money = startConditions.MONEY.number;
        this.currentState = State.MAIN_MENU;
    }

    public void setInput(String input) {
        this.input = input;
        if (this.currentState == State.MAIN_MENU) {
            mainFunction();
        } else if (this.currentState == State.BUY_MENU) {
            if (!buy()) {
                System.out.println("Not enough water!");
            }
        } else if (this.currentState == State.FILL_MENU) {
            fill();
        }
    }

    public void mainFunction() {
        if (counter == 1) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            counter = 0;
            return;
        }
        switch (this.input) {
            case "buy":
                this.currentState = State.BUY_MENU;
                setInput("");
                break;
            case "fill":
                this.currentState = State.FILL_MENU;
                setInput("");
                break;
            case "take":
                take();
                break;
            case "remaining":
                printSupplies();
                break;
        }
        counter++;
    }

    public void fill() {
        switch (counter) {
            case 0:
                System.out.println("Write how many ml of water do you want to add:");
                break;
            case 1:
                this.water += Integer.parseInt(this.input);
                System.out.println("Write how many ml of milk do you want to add:");
                break;
            case 2:
                this.milk += Integer.parseInt(this.input);
                System.out.println("Write how many grams of coffee beans do you want to add:");
                break;
            case 3:
                this.beans += Integer.parseInt(this.input);
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                break;
            case 4:
                this.cups += Integer.parseInt(this.input);
                this.currentState = State.MAIN_MENU;
                break;
        }
        if (counter == 4) {
            counter = 1;
            setInput("");
        } else {
            counter++;
        }
    }

    public boolean buy() {
        if (counter == 0) {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
            counter++;
            return true;
        }
        counter = 0;
        this.currentState = State.MAIN_MENU;
        switch (this.input) {
            case "1":
                return substituteAmount(250, 16, 4, 0);
            case "2":
                return substituteAmount(350, 20, 7, 75);
            case "3":
                return substituteAmount(200, 16, 4, 0);
        }
        return false;
    }


    public boolean substituteAmount(int water, int coffeeBeans, int price, int milk) {
        if (this.water < water || this.beans < coffeeBeans || this.milk < milk) {
            return false;
        }
        this.water -= water;
        this.beans -= coffeeBeans;
        this.money += price;
        this.milk -= milk;
        this.cups -= 1;
        return true;
    }

    public void printSupplies() {
        System.out.println("The coffee machine has:\n" +
                water + " of water\n" +
                milk + " of milk\n" +
                beans + " of coffee beans\n" +
                cups + " of disposable cups\n" +
                money + " of money");
    }

    public void take() {
        System.out.println("I gave you $" + this.money);
        this.money = 0;
    }
}


