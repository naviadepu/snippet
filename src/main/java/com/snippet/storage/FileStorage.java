package com.snippet.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.snippet.Snippet;

public class FileStorage implements StorageProvider {
    private String baseDirectory;

    public FileStorage(String baseDirectory) {
        this.baseDirectory = baseDirectory;
        new File(baseDirectory).mkdirs();
    }

    public String addSnippet(String username, Snippet snippet) {
        String sanitizedName = sanitizeFileName(username + "_" + snippet.getName());
        File snippetFile = new File(baseDirectory, sanitizedName + ".snip");

        try (PrintWriter writer = new PrintWriter(new FileWriter(snippetFile))) {
            // print all metadata to the file
            writer.println(username);
            writer.println(snippet.getName());
            writer.println(snippet.getLanguage());
            writer.println(snippet.getTags());
            // print the code last
            writer.print(snippet.getCode());
        } catch (IOException e) {
            // catch failure to write file
            System.err.println("Failed to write file");
        }
        return snippet.getName();
    }

    public ArrayList<Snippet> getUserSnippets(String username) {
        ArrayList<Snippet> snippets = new ArrayList<>();
        File dir = new File(baseDirectory);

        File[] files = dir.listFiles((_d, name) -> name.endsWith(".snip"));
        // Theres no snippets at all, return empty list
        if (files == null)
            return snippets;

        for (File file : files) {
            // buffered reader so we can read lines
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String fileUsername = reader.readLine();
                if (!fileUsername.equals(username))
                    continue;


                // first 3 lines are metadata
                String name = reader.readLine();
                String language = reader.readLine();
                String tags = reader.readLine();

                // Read the code itself into a StringBuilder
                StringBuilder code = new StringBuilder();
                String line = reader.readLine();

                // Read the rest of the file and append to code, adding a newline at the end of
                // each line
                while (line != null) {
                    code.append(line).append("\n");
                    line = reader.readLine();
                } 

                // add to snippet arraylist
                snippets.add(new Snippet(name, code.toString(), language, tags));
            } catch (IOException e) {
                System.err.println("Failed to read file");
            }
        }
        return snippets;
    }

    public boolean deleteSnippet(String username, String id) {
        File dir = new File(baseDirectory);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".snip"));
        if (files == null)
            return false;

        for (File file : files) {
            // buffered reader so we can read lines
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                // first two lines are the username and snippet name
                String fileUsername = reader.readLine();
                String fileName = reader.readLine();

                if (fileUsername.equals(username) && fileName.equals(id)) {
                    // delete the file storing the snippet
                    return file.delete();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private String sanitizeFileName(String name) {
        // Replace all non-alphanumeric characters with underscores
        return name.replaceAll("[^a-zA-Z0-9.-]", "_");
    }
}