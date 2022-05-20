package net.planetarium;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {
    private static Scanner scanner = new Scanner(System.in);
    private static final String terminalDefault = "> ";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static final String ANSI_CLEAR = "\033[H\033[2J";

    public static void clearScreen() {
        System.out.print(ANSI_CLEAR);
        System.out.flush();
    }

    public static void closeProgram() {
        System.out.println("\nClosing program...");
        System.exit(0);
    }

    private static boolean isInputValid(String[] acceptedInput, String userInput) {
        for (String s : acceptedInput)
            if (s.equals(userInput)) return true;

        return false;
    }

    public static String stringInput() {
        resetScanner();

        System.out.print(terminalDefault);

        String input;
        do {
            input = scanner.nextLine();
        } while (input.equals(""));

        return input;
    }

    public static String stringInput(String[] acceptedInput) {
        resetScanner();

        boolean wrongInput = false;

        String input;
        do {
            if (wrongInput) {
                printError("you entered an invalid input...");
                wrongInput = false;
            }

            System.out.print(terminalDefault);
            input = scanner.nextLine();

            wrongInput = true;
        } while (!isInputValid(acceptedInput, input) || input.equals(""));

        return input;
    }

    public static int intInput() {
        resetScanner();

        int input = 0;
        boolean wrongInput = false;

        do {
            try {
                System.out.print(terminalDefault);
                input = Integer.parseInt(scanner.next());
                wrongInput = false;
            } catch (Exception e) {
                printError("the input entered is not a number...");
                wrongInput = true;
            }
        } while (wrongInput);

        return input;
    }

    public static double doubleInput() {
        resetScanner();

        double input = 0;
        boolean wrongInput = false;

        do {
            try {
                System.out.print(terminalDefault);
                input = scanner.nextDouble();
                wrongInput = false;
            } catch (Exception e) {
                printError("the input entered is not a number...");
                wrongInput = true;
            }
        } while (wrongInput);

        return input;
    }

    public static void printError(String errorMessage) {
        System.out.println(ANSI_RED + "ERROR" + ANSI_RESET + ": " + errorMessage);
    }

    public static void printSuccess(String successMessage) {
        System.out.println(ANSI_GREEN  + successMessage + ANSI_RESET);
    }

    private static void resetScanner() {
        scanner = new Scanner(System.in);
    }
}
