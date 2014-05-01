package com.PPU.DB.workLogic;

import com.PPU.DB.tables.CorrectionsMZ;
import com.PPU.DB.tables.ValuesParametrForProject;

import java.util.List;

/**
 * Created by user on 23.04.14.
 */
public class WorkWithValuesParametrForProject extends WorkWithTable {

    public static String COLUMN_ID = "Id";
    public static String COLUMN_ID_PARAMETR = "IdParametr";
    public static String COLUMN_ID_VALUE = "Value";
    public static String COLUMN_DATE_REC_VALUE = "DateRecValue";
    public static String COLUMN_ID_PROJECT = "idProject";


    public WorkWithValuesParametrForProject()
    {
        super();
    }

    @Override
    public List<ValuesParametrForProject> findAndGetAllRow(String fields, String fieldValue)
    {
        return ppuDao.findValuesParametrForProject(fields, fieldValue);
    }

    @Override
    public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
        if (obj == null)
        {
            throw new IllegalAccessException("Не было передано параметра в ValuesParametrForProject");
        }
        else
            return ClassInvokeCall.callMethod(obj, "get"+columnName);
    }

    @Override
    public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
        if (obj == null)
        {
            throw new IllegalAccessException("Не было передано параметра в ValuesParametrForProject");
        }
        else
            return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
    }

    @Override
    public void addEntity(Object obj) throws Exception {
        if (obj instanceof ValuesParametrForProject)
            ppuDao.saveValuesParametrForProject((ValuesParametrForProject) obj);
        else
            throw new Exception("В метод ValuesParametrForProject.addEntity передан неверный параметр");
    }

    @Override
    public void changeEntity(Object obj) throws Exception {
        if (obj instanceof ValuesParametrForProject)
            ppuDao.updateValuesParametrForProject((ValuesParametrForProject) obj);
        else
            throw new Exception("В метод ValuesParametrForProject.addEntity передан неверный параметр");
    }

    @Override
    public void deleteEntity(Object obj) throws Exception {
        if (obj instanceof ValuesParametrForProject)
            ppuDao.deleteValuesParametrForProject((ValuesParametrForProject) obj);
        else
            throw new Exception("В метод ValuesParametrForProject.addEntity передан неверный параметр");
    }

    @Override
    public Object getEntity(int id) {
        return ppuDao.getValuesParametrForProject(id);
    }

    @Override
    public List getListRows() {
        return findAndGetAllRow("", "");
    }

    @Override
    public Object getEmptyEntity() {
        return new ValuesParametrForProject();
    }
}
