/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package hw2.modules.java.src.library.database.fielddecorators;

import hw2.modules.java.src.library.database.EntityModel;
import hw2.modules.java.src.library.database.FieldModel;

/**
 *
 * @author giuseppe
 */
public class HiddenField extends FieldModel {

    public HiddenField(String field, EntityModel model) {
        super(field, model);
    }

}
