/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */

package Hw2.Java.library.database;

import java.lang.reflect.Field;
import Hw2.Java.library.database.fielddecorators.FieldModel;
import java.util.HashMap;

abstract public class RecordModel {

    private final HashMap<String, FieldModel> decList = new HashMap<>();

    public RecordModel() {
        for (Field e : getClass().getFields()) {
            try {
                FieldModel model = (FieldModel) e.get(this);
                if (model instanceof FieldModel) {
                    this.defineField(model);
                }
            } catch (IllegalAccessException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void defineField(FieldModel model) {
        decList.put(model.field, model);
    }

    public FieldModel getModel(int key) {
        return decList.get(key);
    }

    /**
     * Pass a clone of the list, to avoid external changes
     *
     * @return
     */
    public HashMap<String, FieldModel> getFields() {
        return (HashMap<String, FieldModel>) decList.clone();
    }
}
