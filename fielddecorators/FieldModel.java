/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */

package Hw2.Java.library.database.fielddecorators;

/**
 * Base class for field decorators
 */
public abstract class FieldModel {
    public final String field;
    
    public FieldModel(String field) {
        this.field=field;
    }
}
