package com.example.currency.model;

public class currenciesDatail {

            String symbol,
            name,
            symbol_native,
            decimal_digits,
            rounding,
            code,
            name_plural;

    public currenciesDatail(String symbol, String name, String symbol_native, String decimal_digits, String rounding, String code, String name_plural) {
        this.symbol = symbol;
        this.name = name;
        this.symbol_native = symbol_native;
        this.decimal_digits = decimal_digits;
        this.rounding = rounding;
        this.code = code;
        this.name_plural = name_plural;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol_native() {
        return symbol_native;
    }

    public void setSymbol_native(String symbol_native) {
        this.symbol_native = symbol_native;
    }

    public String getDecimal_digits() {
        return decimal_digits;
    }

    public void setDecimal_digits(String decimal_digits) {
        this.decimal_digits = decimal_digits;
    }

    public String getRounding() {
        return rounding;
    }

    public void setRounding(String rounding) {
        this.rounding = rounding;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName_plural() {
        return name_plural;
    }

    public void setName_plural(String name_plural) {
        this.name_plural = name_plural;
    }
}
