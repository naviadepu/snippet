package com.snippet;

import java.util.Scanner;

// Helper class for interacting and prompting in the terminal
public class Interact {
    private Scanner scanner;

    public Interact(Scanner scanner) {
        this.scanner = scanner;
    }

    public String promptInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int promptNumber(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public boolean promptConfirmation(String prompt) {
        while (true) {
            System.out.print(prompt + " (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            }
            System.out.println("Please enter 'y' or 'n'");
        }
    }

    public String promptMultilineInput(String prompt) {
        System.out.println(prompt);
        System.out.println("(type ':q' on a new line to finish):");
        StringBuilder input = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).equals(":q")) {
            input.append(line).append("\n");
        }
        return input.toString().trim();
    }

    public int promptMenu(String[] options) {
        while (true) {
            System.out.println("\nPlease select an option:");
            for (int i = 0; i < options.length; i++) {
                System.out.printf("%d. %s%n", i + 1, options[i]);
            }
            int selection = promptNumber("Enter your choice (1-" + options.length + "): ");
            if (selection >= 1 && selection <= options.length) {
                return selection;
            }
            System.out.println("Invalid selection. Please try again.");
        }
    }
}
