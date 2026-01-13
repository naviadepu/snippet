package com.snippet.retrievers;

import java.util.ArrayList;

import com.snippet.Snippet;
import com.snippet.SnippetRetriever;

public class LanguageFilter implements SnippetRetriever {
    private String language;

    public LanguageFilter(String language) {
        this.language = language;
    }

    public ArrayList<Snippet> retrieve(ArrayList<Snippet> snippets) {
        ArrayList<Snippet> filteredSnippets = new ArrayList<>();

        if (snippets == null || language == null) {
            return filteredSnippets;
        }

        for (Snippet snippet : snippets) {
            // Just check if the language is matching (non case sensitive)
            String snippetLanguage = snippet.getLanguage().toLowerCase();
            String filterLanguage = language.toLowerCase();

            if (snippetLanguage.equals(filterLanguage)) {
                filteredSnippets.add(snippet);
            }
        }

        return filteredSnippets;
    }
}
