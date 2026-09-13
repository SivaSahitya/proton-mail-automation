package com.protonmail.utils;

public class ConfigReader {

    public static String getProtonUsername() {
        return System.getenv("PROTON_USERNAME");
    }

    public static String getProtonPassword() {
        return System.getenv("PROTON_PASSWORD");
    }
    
    public static String getProtonReceiverUsername() {
        return System.getenv("PROTON_RECEIVER_USERNAME");
    }
}