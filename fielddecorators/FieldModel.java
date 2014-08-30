/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hw2.java.library.database.fielddecorators;

/**
 * Base class for field decorators
 */
public abstract class FieldModel {

    private final String field;

    public FieldModel(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
