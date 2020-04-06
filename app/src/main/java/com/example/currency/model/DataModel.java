package com.example.currency.model;

public class DataModel {

    int image;
    String name,code;
    double value;

    public DataModel(int image, String name, String code, double value) {
        this.image = image;
        this.name = name;
        this.code = code;
        this.value = value;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
