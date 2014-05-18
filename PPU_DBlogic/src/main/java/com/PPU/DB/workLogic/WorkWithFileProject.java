package com.PPU.DB.workLogic;

import com.PPU.DB.tables.FileProject;

import java.util.List;

/**
 * Created by user on 23.04.14.
 */
public class WorkWithFileProject extends WorkWithTable {

    public static String COLUMN_ID = "Id";
    public static String COLUMN_ID_FILE= "File";
    public static String COLUMN_ID_Project = "IdProject";

    public WorkWithFileProject()
    {
        super();
    }

    @Override
    public List<FileProject> findAndGetAllRow(String fields, String fieldValue)
    {
        return ppuDao.findFileProject(fields, fieldValue);
    }

    @Override
    public Object getColumnValue(Object obj, String columnName) throws IllegalAccessException {
        if (obj == null)
        {
            throw new IllegalAccessException("Не было передано параметра в FileProject");
        }
        else
            return ClassInvokeCall.callMethod(obj, "get"+columnName);
    }

    @Override
    public Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException {
        if (obj == null)
        {
            throw new IllegalAccessException("Не было передано параметра в FileProject");
        }
        else
            return ClassInvokeCall.callMethod(obj, "set"+columnName, listValue);
    }

    @Override
    public void addEntity(Object obj) throws Exception {
        if (obj instanceof FileProject)
            ppuDao.saveFileProject((FileProject) obj);
        else
            throw new Exception("В метод DefaultParametrsServiceProject.addEntity передан неверный параметр");
    }

    @Override
    public void changeEntity(Object obj) throws Exception {
        if (obj instanceof FileProject)
            ppuDao.updateFileProject((FileProject) obj);
        else
            throw new Exception("В метод FileProject.addEntity передан неверный параметр");
    }

    @Override
    public void deleteEntity(Object obj) throws Exception {
        if (obj instanceof FileProject)
            ppuDao.deleteFileProject((FileProject) obj);
        else
            throw new Exception("В метод FileProject.addEntity передан неверный параметр");
    }

    @Override
    public Object getEntity(int id) {
        return ppuDao.getFileProject(id);
    }

    @Override
    public List getListRows() {
        return findAndGetAllRow("", "");
    }

    @Override
    public Object getEmptyEntity() {
        return new FileProject();
    }
}
