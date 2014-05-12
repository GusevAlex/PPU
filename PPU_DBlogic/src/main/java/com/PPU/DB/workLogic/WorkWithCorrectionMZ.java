package com.PPU.DB.workLogic;

import com.PPU.DB.tables.CorrectionsMZ;

import java.util.List;

/**
 * Created by user on 23.04.14.
 */
public class WorkWithCorrectionMZ extends WorkWithTable {


    public static String COLUMN_ID = "Id";
    public static String COLUMN_ID_MZ = "IdMZ";
    public static String COLUMN_CORRECTION_DATE = "CorrectionDate";
    public static String COLUMN_PARAMETR = "IdParametr";
    public static String COLUMN_VALUE_BEFORE = "ValueBefore";
    public static String COLUMN_VALUE_AFTER = "ValueAfter";

    public WorkWithCorrectionMZ()
    {
        super();
    }

    @Override
    public List<CorrectionsMZ> findAndGetAllRow(String fields, String fieldValue)
    {
        return ppuDao.findCorrectionsMz(fields, fieldValue);
    }

    @Override
    public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
        if (obj == null)
        {
            throw new IllegalAccessException("Не было передано параметра в CorrectionsMZ");
        }
        else
            return ClassInvokeCall.callMethod(obj, "get"+columnName);
    }

    @Override
    public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
        if (obj == null)
        {
            throw new IllegalAccessException("Не было передано параметра в CorrectionsMZ");
        }
        else
            return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
    }

    @Override
    public void addEntity(Object obj) throws Exception {
        if (obj instanceof CorrectionsMZ)
            ppuDao.saveCorrectionsMz((CorrectionsMZ) obj);
        else
            throw new Exception("В метод CorrectionsMZ.addEntity передан неверный параметр");
    }

    @Override
    public void changeEntity(Object obj) throws Exception {
        if (obj instanceof CorrectionsMZ)
            ppuDao.updateCorrectionsMz((CorrectionsMZ) obj);
        else
            throw new Exception("В метод CorrectionsMZ.addEntity передан неверный параметр");
    }

    @Override
    public void deleteEntity(Object obj) throws Exception {
        if (obj instanceof CorrectionsMZ)
            ppuDao.deleteCorrectionsMz((CorrectionsMZ) obj);
        else
            throw new Exception("В метод CorrectionsMZ.addEntity передан неверный параметр");
    }

    @Override
    public Object getEntity(int id) {
        return ppuDao.getCorrectionsMz(id);
    }

    @Override
    public List getListRows() {
        return findAndGetAllRow("", "");
    }

    @Override
    public Object getEmptyEntity() {
        return new CorrectionsMZ();
    }

}
