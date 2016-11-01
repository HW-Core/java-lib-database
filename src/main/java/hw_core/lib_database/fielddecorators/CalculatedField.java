/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package hw_core.lib_database.fielddecorators;

import hw_core.lib_database.EntityModel;

/**
 *
 * @author giuseppe
 */
public class CalculatedField extends VisualName {

    private final String expression;

    public CalculatedField(String field, String name, EntityModel model, String expression) {
        super(field, name, model);
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

}
