package org.example.model;

/**
 * Customer Categories.
 */
public enum CustomerCategory {

    NONE, GENERAL, KIDS, SENIOR_CITIZEN, SUSPENDED;

    public String getValue() {
        return this.toString();
    }
}
