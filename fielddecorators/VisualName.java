/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hw2.modules.java.src.library.database.fielddecorators;

import hw2.modules.java.src.library.database.EntityModel;
import hw2.modules.java.src.library.database.FieldModel;

public class VisualName extends FieldModel {

    private String name;

    public VisualName(String field, String name, EntityModel model) {
        super(field, model);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
