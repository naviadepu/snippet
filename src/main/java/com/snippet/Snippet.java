package com.snippet;

public class Snippet {
    private String name;
    private String code;
    private String lang;
    private String tags;

    // ANSI color codes
    private static String RESET = "\u001B[0m";
    private static String RED = "\u001B[31m";
    private static String GREEN = "\u001B[32m";
    private static String YELLOW = "\u001B[33m";
    private static String BLUE = "\u001B[34m";

    public Snippet(String name, String code, String lang, String tags) {
        this.name = name;
        this.code = code;
        this.lang = lang;
        this.tags = tags;
    }

    public String getLanguage() {
        return lang;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getTags() {
        return tags;
    }

    public String toString() {
        return RED + "Name: " + RESET + name + "\n" +
                GREEN + "Language: " + RESET + lang + "\n" +
                YELLOW + "Tags: " + RESET + tags + "\n" +
                BLUE + "Code:\n" + RESET + code;
    }
}