package com.snippet.storage;

import com.snippet.Snippet;
import java.util.ArrayList;

public interface StorageProvider {
    // Returns the id of the added snippet
    String addSnippet(String username, Snippet snippet);
    // Get snippets for a user
    ArrayList<Snippet> getUserSnippets(String username);
    // Delete a snippet for a user
    boolean deleteSnippet(String username, String id);
} 