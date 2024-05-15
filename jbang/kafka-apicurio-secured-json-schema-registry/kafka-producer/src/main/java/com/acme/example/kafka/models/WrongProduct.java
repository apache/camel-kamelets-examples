package com.acme.example.kafka.models;

public class WrongProduct {

    private int element;
    private String text;
    private String description;
    /**
     * Constructor.
     */
    public WrongProduct() {
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "WrongProduct{" +
                "element=" + element +
                ", text='" + text + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
