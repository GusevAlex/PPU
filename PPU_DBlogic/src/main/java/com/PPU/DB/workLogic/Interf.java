package com.PPU.DB.workLogic;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 20.05.14
 * Time: 20:26
 * To change this template use File | Settings | File Templates.
 */
public interface Interf {
    public  List getListRows();

    public  List findAndGetAllRow(String fields, String fieldValue);

    public  Object getColumnValue(Object obj, String columnName)  throws IllegalAccessException;

    public  Object setColumnValueFromList(Object obj, String columnName, Object ... listValue) throws IllegalAccessException;

    public  void addEntity(Object obj) throws Exception;
    public  void changeEntity(Object obj) throws Exception;
    public  void deleteEntity(Object obj) throws Exception;
    public  Object getEntity(int id);
}
