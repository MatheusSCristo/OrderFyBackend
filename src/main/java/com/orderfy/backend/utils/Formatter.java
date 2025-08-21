package com.orderfy.backend.utils;

public class Formatter {
    public static String formatCpfCnpj(String value){
        return value.replaceAll("\\D", "");
    }
}
