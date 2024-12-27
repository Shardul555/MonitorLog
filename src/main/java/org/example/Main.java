package org.example;

public class Main {
    public static void main(String[] args) {
        new Thread(new TickClass("src/main/resources/RandomTextFile", 100, "")).start();
    }
}