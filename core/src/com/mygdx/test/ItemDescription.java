package com.mygdx.test;

public class ItemDescription {

    String key;
    String title;
    String formula;
    String description;

    public ItemDescription(String key, String title, String formula, String description) {
        this.key = key;
        this.title = title;
        this.formula = formula;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
