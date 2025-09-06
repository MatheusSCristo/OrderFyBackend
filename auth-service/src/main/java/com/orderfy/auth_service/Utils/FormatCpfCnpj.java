package com.orderfy.auth_service.Utils;

public class FormatCpfCnpj {

    public static String formatCnpj(String cnpj) {
        if (cnpj == null) {
            return null;
        }
        return cnpj.replaceAll("\\D", "");
    }
}
