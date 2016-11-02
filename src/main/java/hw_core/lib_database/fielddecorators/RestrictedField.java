/*
 *  * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved
 *  * GNU General Public License version 3; see http://www.hyperweb2.com/terms/
 */
package hw_core.lib_database.fielddecorators;

import hw_core.lib_common.Permissions;
import hw_core.lib_database.EntityModel;

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
