/*
 * Copyright (C) 2007 - 2014 Hyperweb2 All rights reserved.
 * GNU General Public License version 3; see www.hyperweb2.com/terms/
 */
package hw2.java.library.database;

import hw2.java.library.database.fielddecorators.FieldModel;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;

abstract public class EntityModel {

    private final String tableName;

    private final LinkedHashMap<String, FieldModel> decList = new LinkedHashMap<>();

    public EntityModel(String tableName) {
        this.tableName = tableName;

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

    public String getTableName() {
        return tableName;
    }

    public String[] getFieldsName() {
        return decList.keySet().toArray(new String[decList.size()]);
    }

    /**
     * Pass a clone of the list, to avoid external changes
     *
     * @return
     */
    public LinkedHashMap<String, FieldModel> getFields() {
        return (LinkedHashMap<String, FieldModel>) decList.clone();
    }
}
