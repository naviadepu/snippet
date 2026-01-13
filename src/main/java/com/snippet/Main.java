package com.snippet;

import java.util.ArrayList;
import java.util.Scanner;

import com.snippet.retrievers.LanguageFilter;
import com.snippet.retrievers.Search;
import com.snippet.storage.FileStorage;
import com.snippet.storage.StorageProvider;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // interactivity helper class
        Interact interact = new Interact(scanner);

        // Initialize storage and manager
        StorageProvider storage = new FileStorage("./snippets");
        String username = 
        interact.promptInput("Enter your username: ");
        SnippetManager manager = new SnippetManager(storage, username);

        // User Interface
        System.out.println("Welcome to the Code Snippet Organizer!");
        while (true) {
            String[] options = {
                    "Add Snippet",
                    "Search Snippets by Name",
                    "Filter by Language",
                    "View All Snippets",
                    "Delete Snippet",
                    "Exit"
            };

            int choice = interact.promptMenu(options);

            switch (choice) {
                // Add Snippet
                case 1:
                    String name = interact.promptInput("Enter snippet name: ");
                    String code = interact.promptMultilineInput("Enter code: ");
                    String lang = interact.promptInput("Enter language: ");
                    String tags = interact.promptInput("Enter tags: ");
                    manager.addSnippet(new Snippet(name, code, lang, tags));
                    break;
                
                // Search
                case 2:
                    String searchTerm = interact.promptInput("Enter search term: ");
                    Search search = new Search(searchTerm);
                    ArrayList<Snippet> searchResults = manager.retrieve(search);
                    displaySnippets(searchResults);
                    break;
                
                // Filter
                case 3:
                    String language = interact.promptInput("Enter language to filter: ");
                    LanguageFilter filter = new LanguageFilter(language);
                    ArrayList<Snippet> filteredResults = manager.retrieve(filter);
                    displaySnippets(filteredResults);
                    break;
                
                // View All
                case 4:
                    displaySnippets(manager.getAllSnippets());
                    break;

                // Delete
                case 5:
                    String snippetName = interact.promptInput("Enter snippet name to delete: ");
                    if (manager.deleteSnippet(snippetName)) {
                        System.out.println("Snippet deleted successfully.");
                    } else {
                        System.out.println("Failed to delete snippet.");
                    }
                    break;

                // Exit
                case 6:
                    System.out.println("Goodbye!");
                    return;
            }
        }
    }

    private static void displaySnippets(ArrayList<Snippet> snippets) {
        if (snippets.isEmpty()) {
            System.out.println("No snippets found.");
            return;
        }

        for (int i = 0; i < snippets.size(); i++) {
            System.out.printf("%d.%n", i + 1);
            System.out.println(snippets.get(i).toString());
            System.out.println();
        }
    }
}
