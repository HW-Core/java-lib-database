/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package hw2.modules.java.src.library.database.fielddecorators;

import hw2.modules.java.src.library.common.Permissions;
import hw2.modules.java.src.library.database.EntityModel;

/**
 *
 * @author giuseppe
 */
public class RestrictedField extends VisualName {

    Permissions permissions;

    public RestrictedField(String field, String name, EntityModel model, Permissions perms) {
        super(field, name, model);
        this.permissions = perms;
    }

    public Permissions getPermissions() {
        return permissions;
    }
}
