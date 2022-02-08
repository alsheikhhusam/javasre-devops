package com.example.loggingapp.dto;

public class ValueResponse {
    private String key;
    private int value;

    public ValueResponse() {}

    public ValueResponse(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ValueResponse{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
