package com.snippet.retrievers;

import java.util.ArrayList;

import com.snippet.Snippet;
import com.snippet.SnippetRetriever;

public class Search implements SnippetRetriever {
  private String searchTerm;
  private static int MAX_DISTANCE = 2;

  public Search(String searchTerm) {
    this.searchTerm = searchTerm;
  }

  // Implement SnippetRetriever
  public ArrayList<Snippet> retrieve(ArrayList<Snippet> snippets) {
    ArrayList<Snippet> searchResults = new ArrayList<>();

    if (snippets == null || searchTerm == null) {
      return searchResults;
    }

    for (Snippet snippet : snippets) {
      if (snippet != null && isMatch(snippet.getName(), searchTerm)) {
        searchResults.add(snippet);
      }
    }

    return searchResults;
  }

  // Check if two strings are close enough to be a match based on MAX_DISTANCE
  private boolean isMatch(String name, String term) {
    if (name == null)
      return false;
    name = name.toLowerCase();
    term = term.toLowerCase();

    int distance = levenshteinDistance(name, term);
    return distance <= MAX_DISTANCE;
  }
 
  // Use Levenshtein Distance algorithm for search
  private int levenshteinDistance(String s1, String s2) {
    int[][] dp = new int[s1.length() + 1][s2.length() + 1];

    for (int i = 0; i <= s1.length(); i++) {
      dp[i][0] = i;
    }
    for (int j = 0; j <= s2.length(); j++) {
      dp[0][j] = j;
    }

    for (int i = 1; i <= s1.length(); i++) {
      for (int j = 1; j <= s2.length(); j++) {
        int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
        dp[i][j] = Math.min(Math.min(
            dp[i - 1][j] + 1, // deletion
            dp[i][j - 1] + 1), // insertion
            dp[i - 1][j - 1] + cost); // substitution
      }
    }

    return dp[s1.length()][s2.length()];
  }
}