package com.snippet;

import java.util.ArrayList;

import com.snippet.storage.StorageProvider;

public class SnippetManager {
    private StorageProvider storage;
    private String currentUser;

    public SnippetManager(StorageProvider storage, String username) {
        this.storage = storage;
        this.currentUser = username;
    }

    public void addSnippet(Snippet snippet) {
        storage.addSnippet(currentUser, snippet);
    }

    public boolean deleteSnippet(String id) {
        return storage.deleteSnippet(currentUser, id);
    }

    public ArrayList<Snippet> retrieve(SnippetRetriever retriever) {
        ArrayList<Snippet> userSnippets = storage.getUserSnippets(currentUser);
        return retriever.retrieve(userSnippets);
    }

    public ArrayList<Snippet> getAllSnippets() {
        return storage.getUserSnippets(currentUser);
    }
}