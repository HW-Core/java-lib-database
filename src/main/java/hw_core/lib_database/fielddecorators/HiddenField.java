/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package hw_core.lib_database.fielddecorators;

import hw_core.lib_database.EntityModel;
import hw_core.lib_database.FieldModel;

/**
 *
 * @author giuseppe
 */
public class HiddenField extends FieldModel {

    public HiddenField(String field, EntityModel model) {
        super(field, model);
    }

}
