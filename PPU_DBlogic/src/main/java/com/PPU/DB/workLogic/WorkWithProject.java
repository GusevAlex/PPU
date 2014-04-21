package com.PPU.DB.workLogic;

import java.util.List;

/**
 * Created by Alex on 21.04.2014.
 */
public class WorkWithProject extends WorkWithTable  {

    @Override
    public List getListRows() {
        return null;
    }

    @Override
    public List findAndGetAllRow(String fields, String fieldValue) {
        return null;
    }

    @Override
    public void setRows(Object obj) {

    }

    @Override
    public void setRowById(int id) {

    }

    @Override
    public Object getColumnValue(String columnName) throws IllegalAccessException {
        return null;
    }

    @Override
    public Object setColumnValueFromList(String columnName, Object... listValue) throws IllegalAccessException {
        return null;
    }

    @Override
    public void addEntity(Object obj) throws Exception {

    }

    @Override
    public void changeEntity(Object obj) throws Exception {

    }

    @Override
    public void deleteEntity(Object obj) throws Exception {

    }

    @Override
    public Object getEntity(int id) {
        return null;
    }
}
