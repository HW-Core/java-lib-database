/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package hw_core.lib_database.fielddecorators;

import hw_core.lib_database.EntityModel;
import hw_core.lib_database.FieldModel;

public class RelField extends HiddenField {

    private final FieldModel joinField;

    public RelField(String field, EntityModel model, FieldModel joinField) {
        super(field, model);
        this.joinField = joinField;
    }

    public FieldModel getJoinField() {
        return joinField;
    }
}
