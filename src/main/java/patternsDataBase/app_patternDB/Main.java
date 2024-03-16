package patternsDataBase.app_patternDB;

import patternsDataBase.patternDB.ConnectionPattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello from PatternDB");
        ConnectionPattern.connect();
    }
}
