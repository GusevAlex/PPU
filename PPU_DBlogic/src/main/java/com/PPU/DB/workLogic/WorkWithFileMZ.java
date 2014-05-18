package com.PPU.DB.workLogic;

import com.PPU.DB.tables.FileMZ;

import java.util.List;

/**
 * Created by user on 23.04.14.
 */
public class WorkWithFileMZ extends WorkWithTable {

    public static String COLUMN_ID = "Id";
    public static String COLUMN_ID_FILE= "File";
    public static String COLUMN_ID_MZ = "IdMZ";

    public WorkWithFileMZ()
    {
        super();
    }

    @Override
    public List<FileMZ> findAndGetAllRow(String fields, String fieldValue)
    {
        return ppuDao.findFileMZ(fields, fieldValue);
    }

    @Override
    public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
        if (obj == null)
        {
            throw new IllegalAccessException("Не было передано параметра в FileMZ");
        }
        else
            return ClassInvokeCall.callMethod(obj, "get"+columnName);
    }

    @Override
    public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
        if (obj == null)
        {
            throw new IllegalAccessException("Не было передано параметра в FileMZ");
        }
        else
            return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
    }

    @Override
    public void addEntity(Object obj) throws Exception {
        if (obj instanceof FileMZ)
            ppuDao.saveFileMZ((FileMZ) obj);
        else
            throw new Exception("В метод DefaultParametrsServiceMz.addEntity передан неверный параметр");
    }

    @Override
    public void changeEntity(Object obj) throws Exception {
        if (obj instanceof FileMZ)
            ppuDao.updateFileMZ((FileMZ) obj);
        else
            throw new Exception("В метод FileMZ.addEntity передан неверный параметр");
    }

    @Override
    public void deleteEntity(Object obj) throws Exception {
        if (obj instanceof FileMZ)
            ppuDao.deleteFileMZ((FileMZ) obj);
        else
            throw new Exception("В метод FileMZ.addEntity передан неверный параметр");
    }

    @Override
    public Object getEntity(int id) {
        return ppuDao.getFileMZ(id);
    }

    @Override
    public List getListRows() {
        return findAndGetAllRow("", "");
    }

    @Override
    public Object getEmptyEntity() {
        return new FileMZ();
    }
}
