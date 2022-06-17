package machine;

import java.util.Scanner;

enum CoffeeMachineStates {
        CHOOSING_ACTION, CHOOSING_COFFEE_VARIANT
}

public class CoffeeMachine {
    static int money = 550;
    static int currentWater = 400;
    static int currentMilk = 540;
    static int currentBeans = 120;
    static int disposableCups = 9;

    public static void main(String[] args) {
        String action = "";
        Scanner scanner = new Scanner(System.in);
        while (!action.equals("exit")) {
            System.out.println("Write action (buy, fill, take, exit): ");
            action = scanner.next();
            switch (action) {
                case "buy":
                    buyCoffee();
                    break;
                case "fill":
                    addSupplies();
                    break;
                case "take":
                    System.out.printf("I gave you $%d\n", money);
                    money = 0;
                    break;
                case "remaining":
                    printMachineSupplies();
                    break;
            }
        }
    }

    private static void buyCoffee() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String buyType = scanner.next();
        switch (buyType) {
            case "1":
                makeEspresso();
                break;
            case "2":
                makeLatte();
                break;
            case "3":
                makeCappuccino();
                break;
        }
    }

    private static void addSupplies() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water you want to add:");
        currentWater += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        currentMilk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        currentBeans += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add:");
        disposableCups += scanner.nextInt();
    }

    private static boolean hasEnoughResources(int requiredWater, int requiredMilk, int requiredBeans) {
        if (currentWater < requiredWater) {
            System.out.println("Sorry, not enough water!");
            return false;
        }

        if (currentWater < requiredMilk) {
            System.out.println("Sorry, not enough milk!");
            return false;
        }

        if (currentBeans < requiredBeans) {
            System.out.println("Sorry, not enough beans!");
            return false;
        }

        if (disposableCups < 1) {
            System.out.println("Sorry, not enough cups!");
            return false;
        }

        return true;
    }

    private static void makeEspresso() {
        if (!hasEnoughResources(250, 0, 16)) {
            return;
        }

        currentWater -= 250;
        currentBeans -= 16;
        money += 4;
        disposableCups -= 1;
        System.out.println("I have enough resources, making you a coffee!");
    }

    private static void makeLatte() {
        if (!hasEnoughResources(350, 75, 20)) {
            return;
        }

        currentWater -= 350;
        currentMilk -= 75;
        currentBeans -= 20;
        money += 7;
        disposableCups -= 1;
        System.out.println("I have enough resources, making you a coffee!");
    }

    private static void makeCappuccino() {
        if (!hasEnoughResources(200, 100, 12)) {
            return;
        }

        currentWater -= 200;
        currentMilk -= 100;
        currentBeans -= 12;
        money += 6;
        disposableCups -= 1;
        System.out.println("I have enough resources, making you a coffee!");
    }

    private static void printMachineSupplies() {
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", currentWater);
        System.out.printf("%d ml of milk\n", currentMilk);
        System.out.printf("%d g of coffee beans\n", currentBeans);
        System.out.printf("%d disposable cups\n", disposableCups);
        System.out.printf("$%d of money\n", money);
        System.out.println();
    }
}
